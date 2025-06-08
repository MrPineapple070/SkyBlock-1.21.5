package net.skyblock.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.chars.CharArraySet;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public record RawShapedRecipe(int width, int height, List<ItemStack> ingredients, Optional<Data> data) {
    public record Data(Map<Character, ItemStack> key, List<String> pattern) {
        private static final Codec<List<String>> PATTERN_CODEC = Codec.STRING.listOf().comapFlatMap(pattern -> {
            if (pattern.size() > 3) {
                return DataResult.error(() -> "Invalid pattern: too many rows, 3 is maximum");
            }
            if (pattern.isEmpty()) {
                return DataResult.error(() -> "Invalid pattern: empty pattern not allowed");
            }
            int i = pattern.getFirst().length();
            for (final String string : pattern) {
                if (string.length() > 3) {
                    return DataResult.error(() -> "Invalid pattern: too many columns, 3 is maximum");
                }
                if (i == string.length()) continue;
                return DataResult.error(() -> "Invalid pattern: each row must be the same width");
            }
            return DataResult.success(pattern);
        }, Function.identity());

        private static final Codec<Character> KEY_ENTRY_CODEC = Codec.STRING.comapFlatMap(keyEntry -> {
            if (keyEntry.length() != 1) {
                return DataResult.error(() -> "Invalid key entry: '" + keyEntry + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(keyEntry)) {
                return DataResult.error(() -> "Invalid key entry: ' ' is a reserved symbol.");
            }
            return DataResult.success(keyEntry.charAt(0));
        }, String::valueOf);

        public static final MapCodec<Data> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codecs.strictUnboundedMap(KEY_ENTRY_CODEC, ItemStack.CODEC)
                        .fieldOf("key")
                        .forGetter(data -> data.key),
                PATTERN_CODEC
                        .fieldOf("pattern")
                        .forGetter(data -> data.pattern)
        ).apply(instance, Data::new));

        public ItemStack getRequired(final int slot) {
            final int x = slot % this.pattern.getFirst().length();
            final int y = slot / this.pattern.size();
            final char symbol = this.pattern.get(y).charAt(x);
            return this.key.getOrDefault(symbol, ItemStack.EMPTY);
        }
    }

    public static final MapCodec<RawShapedRecipe> CODEC = Data.CODEC.flatXmap(RawShapedRecipe::fromData,
            recipe -> recipe.data
                    .map(DataResult::success)
                    .orElseGet(() -> DataResult.error(() -> "Cannot encode unpacked recipe"))
    );

    public static final PacketCodec<RegistryByteBuf, RawShapedRecipe> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT, recipe -> recipe.width,
            PacketCodecs.VAR_INT, recipe -> recipe.height,
            ItemStack.PACKET_CODEC.collect(PacketCodecs.toList()), recipe -> recipe.ingredients, RawShapedRecipe::create
    );

    @Contract("_, _, _ -> new")
    private static @NotNull RawShapedRecipe create(final int width, final int height, final @NotNull List<ItemStack> itemStacks) {
        return new RawShapedRecipe(width, height, itemStacks, Optional.empty());
    }

    private static DataResult<RawShapedRecipe> fromData(@NotNull Data data) {
        final String[] strings = RawShapedRecipe.removePadding(data.pattern);
        int height = strings.length;
        int width = strings[0].length();
        List<ItemStack> list = new ArrayList<>(width * height);
        CharArraySet charSet = new CharArraySet(data.key.keySet());
        for (final String string : strings) {
            for (int k = 0; k < string.length(); ++k) {
                ItemStack entry;
                char c = string.charAt(k);
                if (c == ' ') {
                    entry = ItemStack.EMPTY;
                } else {
                    ItemStack stack = data.key.get(c);
                    if (stack == null) {
                        return DataResult.error(() -> "Pattern references symbol '" + c + "' but it's not defined in the key");
                    }
                    entry = stack;
                }
                charSet.remove(c);
                list.add(entry);
            }
        }
        if (!charSet.isEmpty()) {
            return DataResult.error(() -> "Key defines symbols that aren't used in pattern: " + charSet);
        }
        return DataResult.success(new RawShapedRecipe(width, height, list, Optional.of(data)));
    }

    private static String @NotNull [] removePadding(final @NotNull List<String> pattern) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;
        for (int m = 0; m < pattern.size(); ++m) {
            final String line = pattern.get(m);
            i = Math.min(i, Math.clamp(line.indexOf(0x20), 0, line.length() - 1));
            int n = Math.clamp(line.lastIndexOf(0x20), 0, line.length() - 1);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }
                ++l;
                continue;
            }
            l = 0;
        }
        if (pattern.size() == l) {
            return new String[0];
        }
        String[] strings = new String[pattern.size() - l - k];
        for (int o = 0; o < strings.length; ++o) {
            strings[o] = pattern.get(o + k).substring(i, j + 1);
        }
        return strings;
    }

    public int size() {
        return this.width * this.height;
    }
}