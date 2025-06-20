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

public record RawShapedRecipe(int width, int height, @NotNull List<ItemStack> ingredients,
                              @NotNull Optional<Data> data) {
    public record Data(@NotNull Map<Character, ItemStack> key, @NotNull List<String> pattern) {
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

        public static final MapCodec<Data> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(Codecs.strictUnboundedMap(KEY_ENTRY_CODEC, ItemStack.CODEC).fieldOf("key").forGetter(data -> data.key), PATTERN_CODEC.fieldOf("pattern").forGetter(data -> data.pattern)).apply(instance, Data::new));

        public @NotNull ItemStack getRequired(final int slot) {
            final char[] symbols = String.join("", this.pattern).toCharArray();
            return this.key.getOrDefault(symbols[slot], ItemStack.EMPTY);
        }
    }

    public static final MapCodec<RawShapedRecipe> CODEC = Data.CODEC.flatXmap(RawShapedRecipe::fromData, recipe -> recipe.data.map(DataResult::success).orElseGet(() -> DataResult.error(() -> "Cannot encode unpacked recipe")));

    public static final PacketCodec<RegistryByteBuf, RawShapedRecipe> PACKET_CODEC = PacketCodec.tuple(PacketCodecs.VAR_INT, recipe -> recipe.width, PacketCodecs.VAR_INT, recipe -> recipe.height, ItemStack.PACKET_CODEC.collect(PacketCodecs.toList()), recipe -> recipe.ingredients, RawShapedRecipe::create);

    @Contract("_, _, _ -> new")
    private static @NotNull RawShapedRecipe create(final int width, final int height, final @NotNull List<ItemStack> itemStacks) {
        return new RawShapedRecipe(width, height, itemStacks, Optional.empty());
    }

    private static DataResult<RawShapedRecipe> fromData(final @NotNull Data data) {
        final String[] strings = RawShapedRecipe.removePadding(data.pattern);
        final int height = strings.length;
        final int width = strings[0].length();
        final List<ItemStack> list = new ArrayList<>(width * height);
        final CharArraySet charSet = new CharArraySet(data.key.keySet());
        for (final String string : strings) {
            for (final char c : string.toCharArray()) {
                ItemStack entry;
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

        if (!charSet.isEmpty())
            return DataResult.error(() -> "Key defines symbols that aren't used in pattern: " + charSet);

        return DataResult.success(new RawShapedRecipe(width, height, list, Optional.of(data)));
    }

    private static String @NotNull [] removePadding(final @NotNull List<String> pattern) {
        int firstSymbolIndex = Integer.MAX_VALUE;
        int lastSymbolIndex = 0;
        int emptyLinesAtStart = 0;
        int emptyLinesAtEnd = 0;

        for (int lineNumber = 0; lineNumber < pattern.size(); ++lineNumber) {
            final String line = pattern.get(lineNumber);
            firstSymbolIndex = Math.min(firstSymbolIndex, findFirstSymbol(line));
            int lastSymbolIndexInLine = findLastSymbol(line);
            lastSymbolIndex = Math.max(lastSymbolIndex, lastSymbolIndexInLine);
            if (lastSymbolIndexInLine < 0) {
                if (emptyLinesAtStart == lineNumber) {
                    ++emptyLinesAtStart;
                }
                ++emptyLinesAtEnd;
                continue;
            }
            emptyLinesAtEnd = 0;
        }

        if (pattern.size() == emptyLinesAtEnd) {
            return new String[0];
        }

        int finalFirstSymbolIndex = firstSymbolIndex;
        int finalLastSymbolIndex = lastSymbolIndex;
        return pattern.stream()
                .skip(emptyLinesAtStart)
                .limit(pattern.size() - emptyLinesAtEnd - emptyLinesAtStart)
                .map(line -> line.substring(finalFirstSymbolIndex, finalLastSymbolIndex + 1))
                .toArray(String[]::new);
    }

    private static int findFirstSymbol(String line) {
        int i;
        for (i = 0; i < line.length() && line.charAt(i) == ' '; ++i) {
        }
        return i;
    }

    private static int findLastSymbol(String line) {
        int i;
        for (i = line.length() - 1; i >= 0 && line.charAt(i) == ' '; --i) {
        }
        return i;
    }

    public int size() {
        return this.width * this.height;
    }

    public ItemStack getRequired(final int slot) {
        return this.data.map(data -> data.getRequired(slot)).orElse(ItemStack.EMPTY);
    }
}