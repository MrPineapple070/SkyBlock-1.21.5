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

public record SwarmEffect(EnchantmentLevelBasedValue level) implements EnchantmentEntityEffect {
    public static final MapCodec<SwarmEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(SwarmEffect::level)
    ).apply(instance, SwarmEffect::new));

    @Override
    public void apply(final @NotNull ServerWorld world, final int level, final @NotNull EnchantmentEffectContext context, final @NotNull Entity target, final @NotNull Vec3d pos) {
        if (!(target instanceof final LivingEntity living)) return;
        if (!(context.owner() instanceof final PlayerEntity player)) return;

        // Deal more damage based on how many mobs are nearby
        int nearbyMobs = world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(8.0),
                e -> e != player && e != target && e.isAlive()).size();

        float damage = this.level.getValue(level) * (1 + (nearbyMobs * 0.02f));
        living.damage(world, world.getDamageSources().playerAttack(player), damage);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
