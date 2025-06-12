package net.skyblock.init;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.skyblock.SkyBlock;
import net.skyblock.recipe.CountShapedRecipe;
import net.skyblock.recipe.CountShapelessRecipe;

public class RecipeInit {
    public static final RecipeSerializer<CountShapedRecipe> COUNT_SHAPED_RECIPE_SERIALIZER = register("count_shaped", new CountShapedRecipe.Serializer());
    public static final RecipeSerializer<CountShapelessRecipe> COUNT_SHAPELESS_RECIPE_SERIALIZER = register("count_shapeless", new CountShapelessRecipe.Serializer());

    private static <R extends Recipe<CraftingRecipeInput>> RecipeSerializer<R> register(final String name, final RecipeSerializer<R> serializer) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering recipe serializer: {}", id);
        return Registry.register(Registries.RECIPE_SERIALIZER, id, serializer);
    }

    private static <R extends Recipe<CraftingRecipeInput>> RecipeType<R> register(final String name) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering recipe type: {}", id);
        return Registry.register(Registries.RECIPE_TYPE, id, new RecipeType<R>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }

    public static void load() {
        SkyBlock.LOGGER.info("Loading recipes");
    }
}
