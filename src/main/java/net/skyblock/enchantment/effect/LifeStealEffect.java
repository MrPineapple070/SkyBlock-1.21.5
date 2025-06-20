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

public record LifeStealEffect(EnchantmentLevelBasedValue level) implements EnchantmentEntityEffect {
    public static final MapCodec<LifeStealEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(LifeStealEffect::level)
    ).apply(instance, LifeStealEffect::new));

    @Override
    public void apply(final @NotNull ServerWorld world, final int level, final @NotNull EnchantmentEffectContext context, final @NotNull Entity target, final @NotNull Vec3d pos) {
        if (!(target instanceof final LivingEntity living)) return;
        if (!(context.owner() instanceof final PlayerEntity player)) return;

        final float healAmount = this.level.getValue(level) * player.getMaxHealth();
        player.heal(healAmount);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
