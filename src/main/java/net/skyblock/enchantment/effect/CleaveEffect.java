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

import java.util.List;

public record CleaveEffect(EnchantmentLevelBasedValue level) implements EnchantmentEntityEffect {
    public static final MapCodec<CleaveEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(CleaveEffect::level)
    ).apply(instance, CleaveEffect::new));

    @Override
    public void apply(final @NotNull ServerWorld world, final int level, final @NotNull EnchantmentEffectContext context, final @NotNull Entity target, final @NotNull Vec3d pos) {
        if (!(target instanceof LivingEntity living)) return;
        if (!(context.owner() instanceof PlayerEntity player)) return;

        final float damage = this.level.getValue(level);
        final List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, living.getBoundingBox().expand(2.0d), LivingEntity::isAlive);
        entities.forEach(entity -> entity.damage(world, world.getDamageSources().playerAttack(player), damage));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
