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
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.skyblock.init.RecipeInit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class CountShapelessRecipe implements CraftingRecipe {
    public static class Serializer implements RecipeSerializer<CountShapelessRecipe> {
        private static final MapCodec<CountShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
                CraftingRecipeCategory.CODEC.optionalFieldOf("category", CraftingRecipeCategory.MISC).forGetter(r -> r.category),
                ItemStack.VALIDATED_CODEC.listOf(1, 9).fieldOf("ingredients").forGetter(r -> r.ingredients),
                ItemStack.VALIDATED_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(instance, CountShapelessRecipe::new));

        private static final PacketCodec<RegistryByteBuf, CountShapelessRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);

        @Contract("_ -> new")
        private static @NotNull CountShapelessRecipe read(final @NotNull RegistryByteBuf buf) {
            final String group = buf.readString();
            final CraftingRecipeCategory category = buf.readEnumConstant(CraftingRecipeCategory.class);
            final List<ItemStack> ingredients = new ArrayList<>(9);

            for (int i = 0; i < 9; i++) {
                final ItemStack stack = ItemStack.PACKET_CODEC.decode(buf);
                ingredients.add(stack);
            }

            final ItemStack output = ItemStack.PACKET_CODEC.decode(buf);
            return new CountShapelessRecipe(group, category, ingredients, output);
        }

        private static void write(final @NotNull RegistryByteBuf buf, final @NotNull CountShapelessRecipe recipe) {
            buf.writeString(recipe.group);
            buf.writeEnumConstant(recipe.category);
            recipe.ingredients.forEach(stack -> ItemStack.PACKET_CODEC.encode(buf, stack));
            ItemStack.PACKET_CODEC.encode(buf, recipe.output);
        }

        @Override
        public MapCodec<CountShapelessRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, CountShapelessRecipe> packetCodec() {
            return PACKET_CODEC;
        }
    }

    private final String group;
    private final CraftingRecipeCategory category;
    private final List<ItemStack> ingredients;
    private final ItemStack output;
    private final IngredientPlacement placement;
    private final Map<Integer, Integer> matched_index;

    public CountShapelessRecipe(final String group, final CraftingRecipeCategory category, final List<ItemStack> ingredients, final ItemStack output) {
        this.group = group;
        this.category = category;
        this.ingredients = ingredients;
        this.output = output;
        final List<Optional<Ingredient>> list = this.ingredients.stream()
                .filter(stack -> !stack.isEmpty())
                .map(stack -> Optional.of(Ingredient.ofItem(stack.getItem())))
                .toList();
        this.placement = IngredientPlacement.forMultipleSlots(list);
        this.matched_index = new HashMap<>(this.ingredients.size());
    }

    @Override
    public boolean matches(final CraftingRecipeInput input, final @NotNull World world) {
        if (world.isClient) return false;
        if (this.ingredients.size() != input.getStackCount()) return false;

        this.find_matches(input.getStacks());

        return this.matched_index.size() == this.ingredients.size();
    }

    @Override
    public DefaultedList<ItemStack> getRecipeRemainders(final CraftingRecipeInput input) {
        final DefaultedList<ItemStack> remainders = CraftingRecipe.super.getRecipeRemainders(input);

        for (final Map.Entry<Integer, Integer> entry : this.matched_index.entrySet()) {
            final ItemStack required = this.ingredients.get(entry.getKey());
            final ItemStack actual = input.getStackInSlot(entry.getValue());
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
        return RecipeInit.COUNT_SHAPELESS_RECIPE_SERIALIZER;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return this.placement;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return this.category;
    }

    private void find_matches(final @NotNull List<ItemStack> stacks) {
        this.matched_index.clear();

        for (int i = 0; i < this.ingredients.size(); i++) {
            for (int j = 0; j < stacks.size(); j++) {
                if (this.matched_index.containsKey(i)) break;
                if (this.matched_index.containsValue(j)) continue;

                final ItemStack required = this.ingredients.get(i);
                final ItemStack actual = stacks.get(j);

                if (!ItemStack.areItemsEqual(required, actual)) continue;
                if (required.getCount() > actual.getCount()) continue;

                this.matched_index.put(i, j);
                break;
            }
        }
    }
}
