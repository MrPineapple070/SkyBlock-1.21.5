package net.skyblock.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record ThunderlordEffect(EnchantmentLevelBasedValue level) implements EnchantmentEntityEffect {
    public static final MapCodec<ThunderlordEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(ThunderlordEffect::level)
    ).apply(instance, ThunderlordEffect::new));

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        if (!(target instanceof final LivingEntity living)) return;
        if (!(context.owner() instanceof final PlayerEntity player)) return;

        final ItemStack held = player.getMainHandStack();
        final NbtComponent nbt = held.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        final NbtCompound tag = nbt.copyNbt();

        final int hits = tag.getInt("enchantment.thunderbolt.count", 0) + 1;
        if (hits < 3) {
            tag.putInt("enchantment.thunderbolt.count", hits);
            held.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(tag));
            return;
        }

        tag.putInt("enchantment.thunderbolt.count", 0);
        held.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(tag));

        final float amount = this.level.getValue(level);
        final DamageSource damage = world.getDamageSources().playerAttack(player);
        target.damage(world, damage, amount);
        EntityType.LIGHTNING_BOLT.spawn(world, living.getBlockPos(), SpawnReason.TRIGGERED);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
