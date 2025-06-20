package net.skyblock.init;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.skyblock.SkyBlock;
import net.skyblock.enchantment.effect.ThunderboltEffect;
import net.skyblock.enchantment.effect.ThunderlordEffect;
import org.jetbrains.annotations.NotNull;

public class EnchantmentInit {
    public static final RegistryKey<Enchantment> THUNDERBOLT = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "thunderbolt"));
    public static final RegistryKey<Enchantment> THUNDERLORD = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "thunderlord"));

    public static final MapCodec<ThunderboltEffect> THUNDERBOLT_EFFECT = register("thunderbolt", ThunderboltEffect.CODEC);
    public static final MapCodec<ThunderlordEffect> THUNDERLORD_EFFECT = register("thunderlord", ThunderlordEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(final @NotNull String name, final @NotNull MapCodec<T> codec) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering Enchantment: {}", id);
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, id, codec);
    }

    public static void load() {
        SkyBlock.LOGGER.info("Loading Enchantments...");
    }
}
