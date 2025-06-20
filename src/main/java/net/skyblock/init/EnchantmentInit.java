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
import net.skyblock.enchantment.effect.*;
import org.jetbrains.annotations.NotNull;

public class EnchantmentInit {
    // Enchantment Registry Keys
    public static final RegistryKey<Enchantment> THUNDERBOLT = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "thunderbolt"));
    public static final RegistryKey<Enchantment> THUNDERLORD = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "thunderlord"));
    public static final RegistryKey<Enchantment> CHAMPION = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "champion"));
    public static final RegistryKey<Enchantment> CLEAVE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "cleave"));
    public static final RegistryKey<Enchantment> CRITICAL = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "critical"));
    public static final RegistryKey<Enchantment> CUBISM = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "cubism"));
    public static final RegistryKey<Enchantment> DIVINE_GIFT = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "divine_gift"));
    public static final RegistryKey<Enchantment> DRAGON_HUNTER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "dragon_hunter"));
    public static final RegistryKey<Enchantment> ENDER_SLAYER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "ender_slayer"));
    public static final RegistryKey<Enchantment> EXECUTE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "execute"));
    public static final RegistryKey<Enchantment> EXPERIENCE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "experience"));
    public static final RegistryKey<Enchantment> FIRST_STRIKE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "first_strike"));
    public static final RegistryKey<Enchantment> GIANT_KILLER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "giant_killer"));
    public static final RegistryKey<Enchantment> IMPALING = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "impaling"));
    public static final RegistryKey<Enchantment> KNOCKBACK = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "knockback"));
    public static final RegistryKey<Enchantment> LETHALITY = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "lethality"));
    public static final RegistryKey<Enchantment> LIFE_STEAL = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "life_steal"));
    public static final RegistryKey<Enchantment> LUCK = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "luck"));
    public static final RegistryKey<Enchantment> MANA_STEAL = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "mana_steal"));
    public static final RegistryKey<Enchantment> PROSECUTE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "prosecute"));
    public static final RegistryKey<Enchantment> SCAVENGER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "scavenger"));
    public static final RegistryKey<Enchantment> SMOLDERING = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "smoldering"));
    public static final RegistryKey<Enchantment> SYPHON = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "siphon"));
    public static final RegistryKey<Enchantment> TOBASCO = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "tobasco"));
    public static final RegistryKey<Enchantment> TITAN_KILLER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "titan_killer"));
    public static final RegistryKey<Enchantment> TRIPLE_STRIKE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "triple_strike"));
    public static final RegistryKey<Enchantment> VAMPIRISM = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "vampirism"));
    public static final RegistryKey<Enchantment> VENOMOUS = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "venomous"));
    public static final RegistryKey<Enchantment> VICIOUS = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "vicious"));
    public static final RegistryKey<Enchantment> BANK = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "bank"));
    public static final RegistryKey<Enchantment> BOBBIN_TIME = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "bobbin_time"));
    public static final RegistryKey<Enchantment> DUPLEX = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "duplex"));
    public static final RegistryKey<Enchantment> FLASH = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "flash"));
    public static final RegistryKey<Enchantment> FLOWSTATE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "flowstate"));
    public static final RegistryKey<Enchantment> HABANERO = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "habanero"));
    public static final RegistryKey<Enchantment> LAST_STAND = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "last_stand"));
    public static final RegistryKey<Enchantment> LEGION = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "legion"));
    public static final RegistryKey<Enchantment> NO_PAIN_NO_GAIN = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "no_pain_no_gain"));
    public static final RegistryKey<Enchantment> REFRIGERATE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "refrigerate"));
    public static final RegistryKey<Enchantment> REND = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "rend"));
    public static final RegistryKey<Enchantment> THE_ONE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "the_one"));
    public static final RegistryKey<Enchantment> ULTIMATE_WISE = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "ultimate_wise"));
    public static final RegistryKey<Enchantment> WISDOM = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "wisdom"));
    public static final RegistryKey<Enchantment> CHIMERA = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "chimera"));
    public static final RegistryKey<Enchantment> COMBO = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "combo"));
    public static final RegistryKey<Enchantment> FATAL_TEMPO = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "fatal_tempo"));
    public static final RegistryKey<Enchantment> INFERNO = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "inferno"));
    public static final RegistryKey<Enchantment> ONE_FOR_ALL = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "one_for_all"));
    public static final RegistryKey<Enchantment> SOUL_EATER = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "soul_eater"));
    public static final RegistryKey<Enchantment> SWARM = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "swarm"));
    public static final RegistryKey<Enchantment> ULTIMATE_JERRY = RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(SkyBlock.MOD_ID, "ultimate_jerry"));

    // Enchantment Effects
    public static final MapCodec<ThunderboltEffect> THUNDERBOLT_EFFECT = register("thunderbolt", ThunderboltEffect.CODEC);
    public static final MapCodec<ThunderlordEffect> THUNDERLORD_EFFECT = register("thunderlord", ThunderlordEffect.CODEC);
    public static final MapCodec<ChampionEffect> CHAMPION_EFFECT = register("champion", ChampionEffect.CODEC);
    public static final MapCodec<CleaveEffect> CLEAVE_EFFECT = register("cleave", CleaveEffect.CODEC);
    public static final MapCodec<CriticalEffect> CRITICAL_EFFECT = register("critical", CriticalEffect.CODEC);
    public static final MapCodec<DivineGiftEffect> DIVINE_GIFT_EFFECT = register("divine_gift", DivineGiftEffect.CODEC);
    public static final MapCodec<ExecuteEffect> EXECUTE_EFFECT = register("execute", ExecuteEffect.CODEC);
    public static final MapCodec<ExperienceEffect> EXPERIENCE_EFFECT = register("experience", ExperienceEffect.CODEC);
    public static final MapCodec<FirstStrikeEffect> FIRST_STRIKE_EFFECT = register("first_strike", FirstStrikeEffect.CODEC);
    public static final MapCodec<GiantKillerEffect> GIANT_KILLER_EFFECT = register("giant_killer", GiantKillerEffect.CODEC);
    public static final MapCodec<LethalityEffect> LETHALITY_EFFECT = register("lethality", LethalityEffect.CODEC);
    public static final MapCodec<LifeStealEffect> LIFE_STEAL_EFFECT = register("life_steal", LifeStealEffect.CODEC);
    public static final MapCodec<ChimeraEffect> CHIMERA_EFFECT = register("chimera", ChimeraEffect.CODEC);
    public static final MapCodec<ComboEffect> COMBO_EFFECT = register("combo", ComboEffect.CODEC);
    public static final MapCodec<FatalTempoEffect> FATAL_TEMPO_EFFECT = register("fatal_tempo", FatalTempoEffect.CODEC);
    public static final MapCodec<InfernoEffect> INFERNO_EFFECT = register("inferno", InfernoEffect.CODEC);
    public static final MapCodec<OneForAllEffect> ONE_FOR_ALL_EFFECT = register("one_for_all", OneForAllEffect.CODEC);
    public static final MapCodec<SoulEaterEffect> SOUL_EATER_EFFECT = register("soul_eater", SoulEaterEffect.CODEC);
    public static final MapCodec<SwarmEffect> SWARM_EFFECT = register("swarm", SwarmEffect.CODEC);
    public static final MapCodec<UltimateJerryEffect> ULTIMATE_JERRY_EFFECT = register("ultimate_jerry", UltimateJerryEffect.CODEC);
    public static final MapCodec<UltimateWiseEffect> ULTIMATE_WISE_EFFECT = register("ultimate_wise", UltimateWiseEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(final @NotNull String name, final @NotNull MapCodec<T> codec) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering Enchantment: {}", id);
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, id, codec);
    }

    public static void load() {
        SkyBlock.LOGGER.info("Loading Enchantments...");
    }
}
