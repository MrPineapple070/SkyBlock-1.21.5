package net.skyblock.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

public record FirstStrikeEffect(EnchantmentLevelBasedValue level) implements EnchantmentEntityEffect {
    public static final MapCodec<FirstStrikeEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            EnchantmentLevelBasedValue.CODEC.fieldOf("level").forGetter(FirstStrikeEffect::level)
    ).apply(instance, FirstStrikeEffect::new));

    private static final String KEY = "enchantment.first_strike.hit";

    @Override
    public void apply(final @NotNull ServerWorld world, final int level, final @NotNull EnchantmentEffectContext context, final @NotNull Entity target, final @NotNull Vec3d pos) {
        if (!(target instanceof final LivingEntity living)) return;
        if (!(context.owner() instanceof final PlayerEntity player)) return;

        final ItemStack held = player.getMainHandStack();
        final NbtComponent nbt = held.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        final NbtCompound tag = nbt.copyNbt();

        final boolean hasHit = tag.getBoolean(KEY, false);
        if (hasHit) return;

        tag.putBoolean(KEY, true);
        held.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(tag));

        final float amount = this.level.getValue(level);
        final DamageSource damage = world.getDamageSources().playerAttack(player);
        target.damage(world, damage, amount);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
