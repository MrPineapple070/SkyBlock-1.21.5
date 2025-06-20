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

import java.util.List;
import java.util.Optional;

public class CountShapedRecipe implements CraftingRecipe {
    public static class Serializer implements RecipeSerializer<CountShapedRecipe> {
        public static final MapCodec<CountShapedRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter(r -> r.group),
                CraftingRecipeCategory.CODEC.optionalFieldOf("category", CraftingRecipeCategory.MISC).forGetter(r -> r.category),
                RawShapedRecipe.CODEC.forGetter(r -> r.raw),
                ItemStack.MAP_CODEC.fieldOf("output").forGetter(r -> r.output)
        ).apply(instance, CountShapedRecipe::new));

        public static final PacketCodec<RegistryByteBuf, CountShapedRecipe> PACKET_CODEC = PacketCodec.ofStatic(Serializer::write, Serializer::read);

        @Contract("_ -> new")
        private static @NotNull CountShapedRecipe read(final @NotNull RegistryByteBuf buf) {
            final String group = buf.readString();
            final CraftingRecipeCategory category = buf.readEnumConstant(CraftingRecipeCategory.class);
            final RawShapedRecipe input = RawShapedRecipe.PACKET_CODEC.decode(buf);
            final ItemStack output = ItemStack.PACKET_CODEC.decode(buf);

            return new CountShapedRecipe(group, category, input, output);
        }

        private static void write(final @NotNull RegistryByteBuf buf, final @NotNull CountShapedRecipe recipe) {
            buf.writeString(recipe.group);
            CraftingRecipeCategory.PACKET_CODEC.encode(buf, recipe.category);
            RawShapedRecipe.PACKET_CODEC.encode(buf, recipe.raw);
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
    private final CraftingRecipeCategory category;
    private final RawShapedRecipe raw;
    private final ItemStack output;
    private final IngredientPlacement placement;

    public CountShapedRecipe(String group, CraftingRecipeCategory category, RawShapedRecipe raw, ItemStack output) {
        this.group = group;
        this.category = category;
        this.raw = raw;
        this.output = output;
        final List<Optional<Ingredient>> list = this.raw.ingredients().stream()
                .filter(stack -> !stack.isEmpty())
                .map(stack -> Optional.of(Ingredient.ofItem(stack.getItem())))
                .toList();
        this.placement = IngredientPlacement.forMultipleSlots(list);
    }


    @Override
    public boolean matches(final CraftingRecipeInput input, final @NotNull World world) {
        if (world.isClient) return false;
        if (this.raw.size() > input.size()) return false;

        for (int slot = 0; slot < this.raw.size(); ++slot) {
            final ItemStack required = this.raw.getRequired(slot);
            final ItemStack actual = input.getStackInSlot(slot);
            if (!ItemStack.areItemsEqual(actual, required)) return false;
            if (actual.getCount() < required.getCount()) return false;
        }

        return true;
    }

    @Override
    public DefaultedList<ItemStack> getRecipeRemainders(final CraftingRecipeInput input) {
        final DefaultedList<ItemStack> remainders = CraftingRecipe.super.getRecipeRemainders(input);

        for (int slot = 0; slot < input.size(); ++slot) {
            final ItemStack required = this.raw.getRequired(slot);
            final ItemStack actual = input.getStackInSlot(slot);
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
        return this.category;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return this.placement;
    }
}
