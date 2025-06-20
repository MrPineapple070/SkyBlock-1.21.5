package net.skyblock.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

public record UltimateJerryEffect(EnchantmentLevelBasedValue level) implements EnchantmentEntityEffect {
    public static final MapCodec<UltimateJerryEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(UltimateJerryEffect::level)
    ).apply(instance, UltimateJerryEffect::new));

    @Override
    public void apply(final @NotNull ServerWorld world, final int level, final @NotNull EnchantmentEffectContext context, final @NotNull Entity target, final @NotNull Vec3d pos) {
        if (!(target instanceof final LivingEntity living)) return;
        if (!(context.owner() instanceof final PlayerEntity player)) return;

        // Jerry effect - small chance to deal massive damage, but usually does nothing
        if (world.random.nextFloat() < 0.01f) { // 1% chance
            float damage = 100.0f * level;
            living.damage(world, world.getDamageSources().playerAttack(player), damage);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
