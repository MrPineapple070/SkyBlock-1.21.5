package net.skyblock.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.skyblock.init.RecipeInit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CountShapedRecipe implements CraftingRecipe {
    public static class Serializer implements RecipeSerializer<CountShapedRecipe> {
        public static final MapCodec<CountShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
                Codec.STRING.listOf().fieldOf("pattern").forGetter(r -> r.pattern),
                ItemStack.MAP_CODEC.codec().listOf().fieldOf("input").forGetter(r -> r.input),
                ItemStack.MAP_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(instance, CountShapedRecipe::new));

        public static final PacketCodec<RegistryByteBuf, CountShapedRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);

        @Contract("_ -> new")
        private static @NotNull CountShapedRecipe read(@NotNull RegistryByteBuf buf) {
            final String group = buf.readString();
            final int patternHeight = buf.readVarInt();
            final int patternWidth = buf.readVarInt();

            final List<String> pattern = new ArrayList<>();
            for (int row = 0; row < patternHeight; ++row) {
                pattern.add(buf.readString());
            }

            final int inputCount = patternHeight * patternWidth;
            final List<ItemStack> input = new ArrayList<>(inputCount);

            for (int i = 0; i < inputCount; ++i) {
                input.add(ItemStack.PACKET_CODEC.decode(buf));
            }

            ItemStack output = ItemStack.PACKET_CODEC.decode(buf);

            return new CountShapedRecipe(group, pattern, input, output);
        }

        private static void write(@NotNull RegistryByteBuf buf, @NotNull CountShapedRecipe recipe) {
            buf.writeString(recipe.group);
            buf.writeVarInt(recipe.pattern.size());
            buf.writeVarInt(recipe.pattern.getFirst().length());

            recipe.pattern.forEach(buf::writeString);
            recipe.input.forEach((stack) -> ItemStack.PACKET_CODEC.encode(buf, stack));

            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }

        @Override
        public MapCodec<CountShapedRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CountShapedRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    private final String group;
    private final List<String> pattern;
    private final List<ItemStack> input;
    private final ItemStack output;

    private final int width;
    private final int height;

    public CountShapedRecipe(final @NotNull String group, final @NotNull List<String> pattern, final @NotNull List<ItemStack> input, final @NotNull ItemStack output) {
        this.group = group;
        this.pattern = pattern;
        this.input = input;
        this.output = output;

        this.width = pattern.getFirst().length();
        this.height = pattern.size();
    }

    @Override
    public boolean matches(final CraftingRecipeInput input, final World world) {
        if (world.isClient) return false;

        for (int row = 0; row < this.height; ++row) {
            final String line = this.pattern.get(row);
            for (int col = 0; col < this.width; ++col) {
                final int slot = col + row * this.width;
                if (slot >= input.size()) return false;
                final char symbol = line.charAt(col);
                final ItemStack required = this.input.get(slot);
                final ItemStack actual = input.getStackInSlot(slot);

                if (symbol == ' ') {
                    if (!actual.isEmpty()) return false;
                } else {
                    if (!ItemStack.areItemsEqual(actual, required)) return false;
                    if (actual.getCount() < required.getCount()) return false;
                }
            }
        }

        return true;
    }

    @Override
    public DefaultedList<ItemStack> getRecipeRemainders(final CraftingRecipeInput input) {
        final DefaultedList<ItemStack> remainders = CraftingRecipe.super.getRecipeRemainders(input);

        for (int slot = 0; slot < input.size(); ++slot) {
            final ItemStack required = this.input.get(slot);
            final ItemStack actual = input.getStackInSlot(slot);
            if (actual.isEmpty()) continue;
            if (!ItemStack.areItemsEqual(actual, required)) continue;
            actual.decrement(required.getCount() - 1);
        }

        return remainders;
    }

    @Override
    public ItemStack craft(final CraftingRecipeInput input, final RegistryWrapper.WrapperLookup registries) {
        return this.output.copy();
    }

    @Override
    public RecipeSerializer<? extends CraftingRecipe> getSerializer() {
        return RecipeInit.COUNT_SHAPED_RECIPE_SERIALIZER;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        final List<Optional<Ingredient>> list = this.input.stream()
                .map(stack -> Optional.of(Ingredient.ofItem(stack.getItem())))
                .toList();
        return IngredientPlacement.forMultipleSlots(list);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
