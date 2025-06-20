package net.skyblock.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.skyblock.SkyBlock;
import net.skyblock.item.EnchantedItem;
import net.skyblock.item.MenuItem;

import java.util.function.Function;
import java.util.function.Supplier;

public class ItemInit {
    private static final RegistryKey<ItemGroup> FARMING_GROUP_KEY = register("farming");
    private static final ItemGroup FARMING_GROUP = register("farming", FARMING_GROUP_KEY, Items.GOLDEN_HOE::getDefaultStack);

    private static final RegistryKey<ItemGroup> MINING_GROUP_KEY = register("mining");
    private static final ItemGroup MINING_GROUP = register("mining", MINING_GROUP_KEY, Items.STONE_PICKAXE::getDefaultStack);

    private static final RegistryKey<ItemGroup> COMBAT_GROUP_KEY = register("combat");
    private static final ItemGroup COMBAT_GROUP = register("combat", COMBAT_GROUP_KEY, Items.STONE_SWORD::getDefaultStack);

    private static final RegistryKey<ItemGroup> FORAGING_GROUP_KEY = register("foraging");
    private static final ItemGroup FORAGING_GROUP = register("foraging", FORAGING_GROUP_KEY, Items.JUNGLE_SAPLING::getDefaultStack);

    private static final RegistryKey<ItemGroup> FISHING_GROUP_KEY = register("fishing");
    private static final ItemGroup FISHING_GROUP = register("fishing", FISHING_GROUP_KEY, Items.FISHING_ROD::getDefaultStack);

    private static final RegistryKey<ItemGroup> BOSS_GROUP_KEY = register("boss");
    private static final ItemGroup BOSS_GROUP = register("boss", BOSS_GROUP_KEY, Items.WITHER_SKELETON_SKULL::getDefaultStack);

    private static final RegistryKey<ItemGroup> RIFT_GROUP_KEY = register("rift");
    private static final ItemGroup RIFT_GROUP = register("rift", RIFT_GROUP_KEY, Items.MYCELIUM::getDefaultStack);

    private static final Item.Settings SETTINGS_01 = new Item.Settings().fireproof().maxCount(1);
    private static final Item.Settings SETTINGS_64 = new Item.Settings().fireproof().maxCount(64);

    public static final Item MENU_ITEM = register("menu_item", MenuItem::new, SETTINGS_01);
    public static final Item ANCIENT_CLAW = register("ancient_claw", Item::new, SETTINGS_64);
    public static final Item FIG_LOG = register("fig_log", Item::new, SETTINGS_64);
    public static final Item GLACITE = register("glacite", Item::new, SETTINGS_64);
    public static final Item HARD_STONE = register("hard_stone", Item::new, SETTINGS_64);
    public static final Item LUSH_BERBERIS = register("lush_berberis", Item::new, SETTINGS_64);
    public static final Item MITHRIL_ORE = register("mithril_ore", Item::new, SETTINGS_64);
    public static final Item SHARK_FIN = register("shark_fin", Item::new, SETTINGS_64);
    public static final Item SULPHUR = register("sulphur", Item::new, SETTINGS_64);
    public static final Item TITANIUM = register("titanium_ore", Item::new, SETTINGS_64);
    public static final Item TUNGSTEN = register("tungsten", Item::new, SETTINGS_64);
    public static final Item UMBER = register("umber", Item::new, SETTINGS_64);
    public static final Item ENCHANTED_ACACIA_LOG = register("enchanted_acacia_log", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_ANCIENT_CLAW = register("enchanted_ancient_claw", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BAKED_POTATO = register("enchanted_baked_potato", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BIRCH_LOG = register("enchanted_birch_log", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BLAZE_POWDER = register("enchanted_blaze_powder", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BLAZE_ROD = register("enchanted_blaze_rod", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BONE = register("enchanted_bone", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BONE_BLOCK = register("enchanted_bone_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BONE_MEAL = register("enchanted_bone_meal", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BOOKSHELF = register("enchanted_bookshelf", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BREAD = register("enchanted_bread", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BROWN_MUSHROOM = register("enchanted_brown_mushroom", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CACTUS = register("enchanted_cactus", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CACTUS_GREEN = register("enchanted_cactus_green", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CAKE = register("enchanted_cake", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CARROT = register("enchanted_carrot", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CARROT_ON_A_STICK = register("enchanted_carrot_on_a_stick", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CHARCOAL = register("enchanted_charcoal", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CLAY_BALL = register("enchanted_clay_ball", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CLAY_BLOCK = register("enchanted_clay_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CLOCK = register("enchanted_clock", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CLOWNFISH = register("enchanted_clownfish", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COAL = register("enchanted_coal", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COAL_BLOCK = register("enchanted_coal_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COBBLESTONE = register("enchanted_cobblestone", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COCOA = register("enchanted_cocoa", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COMPOST = register("enchanted_compost", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COOKED_BEEF = register("enchanted_cooked_beef", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COOKED_FISH = register("enchanted_cooked_fish", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COOKED_MUTTON = register("enchanted_cooked_mutton", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COOKED_SALMON = register("enchanted_cooked_salmon", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COOKIE = register("enchanted_cookie", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_DANDELION = register("enchanted_dandelion", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_DARK_OAK_LOG = register("enchanted_dark_oak_log", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_DIAMOND = register("enchanted_diamond", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_DIAMOND_BLOCK = register("enchanted_diamond_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_EGG = register("enchanted_egg", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_EMERALD = register("enchanted_emerald", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_EMERALD_BLOCK = register("enchanted_emerald_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_ENDER_PEARL = register("enchanted_ender_pearl", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_EYE_OF_ENDER = register("enchanted_eye_of_ender", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_ENDSTONE = register("enchanted_endstone", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_FEATHER = register("enchanted_feather", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_FERMENTED_SPIDER_EYE = register("enchanted_fermented_spider_eye", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_FIG_LOG = register("enchanted_fig_log", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_FIREWORK_ROCKET = register("enchanted_firework_rocket", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_FLINT = register("enchanted_flint", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GHAST_TEAR = register("enchanted_ghast_tear", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GLACITE = register("enchanted_glacite", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GLISTERING_MELON = register("enchanted_glistering_melon", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GLOWSTONE = register("enchanted_glowstone", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GLOWSTONE_DUST = register("enchanted_glowstone_dust", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GOLD_INGOT = register("enchanted_gold_ingot", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GOLDEN_CARROT = register("enchanted_golden_carrot", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GOLD_BLOCK = register("enchanted_gold_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GRIFFIN_FEATHER = register("enchanted_griffin_feather", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_COOKED_PORKCHOP = register("enchanted_cooked_porkchop", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_GUNPOWDER = register("enchanted_gunpowder", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_HARD_STONE = register("enchanted_hard_stone", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_HAY_BALE = register("enchanted_hay_bale", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_HOPPER = register("enchanted_hopper", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_RED_MUSHROOM_BLOCK = register("enchanted_red_mushroom_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BROWN_MUSHROOM_BLOCK = register("enchanted_brown_mushroom_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_ICE = register("enchanted_ice", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_INK_SAC = register("enchanted_ink_sac", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_IRON = register("enchanted_iron", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_IRON_BLOCK = register("enchanted_iron_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_JACK_O_LANTERN = register("enchanted_jack_o_lantern", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_JUNGLE_LOG = register("enchanted_jungle_log", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_LAPIS = register("enchanted_lapis", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_LAPIS_BLOCK = register("enchanted_lapis_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_LAVA_BUCKET = register("enchanted_lava_bucket", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_LEATHER = register("enchanted_leather", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_LUSH_BERBERIS = register("enchanted_lush_berberis", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_MAGMA_CREAM = register("enchanted_magma_cream", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_MELON = register("enchanted_melon", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_MELON_BLOCK = register("enchanted_melon_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_MITHRIL = register("enchanted_mithril", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_MUTTON = register("enchanted_mutton", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_MYCELIUM = register("enchanted_mycelium", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_MYCELIUM_CUBE = register("enchanted_mycelium_cube", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_NETHERRACK = register("enchanted_netherrack", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_NETHER_WART = register("enchanted_nether_wart", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_OAK_LOG = register("enchanted_oak_log", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_OBSIDIAN = register("enchanted_obsidian", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_PACKED_ICE = register("enchanted_packed_ice", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_PAPER = register("enchanted_paper", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_POISONOUS_POTATO = register("enchanted_poisonous_potato", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_POPPY = register("enchanted_poppy", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_PORKCHOP = register("enchanted_porkchop", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_POTATO = register("enchanted_potato", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_PRISMARINE_CRYSTALS = register("enchanted_prismarine_crystals", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_PRISMARINE_SHARD = register("enchanted_prismarine_shard", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_PUFFERFISH = register("enchanted_pufferfish", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_PUMPKIN = register("enchanted_pumpkin", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_QUARTZ = register("enchanted_quartz", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_QUARTZ_BLOCK = register("enchanted_quartz_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_RABBIT = register("enchanted_rabbit", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_RABBIT_FOOT = register("enchanted_rabbit_foot", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_RABBIT_HIDE = register("enchanted_rabbit_hide", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_BEEF = register("enchanted_beef", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_CHICKEN = register("enchanted_chicken", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_FISH = register("enchanted_fish", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SALMON = register("enchanted_salmon", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_REDSTONE = register("enchanted_redstone", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_REDSTONE_BLOCK = register("enchanted_redstone_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_REDSTONE_LAMP = register("enchanted_redstone_lamp", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_RED_MUSHROOM = register("enchanted_red_mushroom", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_RED_SAND = register("enchanted_red_sand", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_RED_SAND_CUBE = register("enchanted_red_sand_cube", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_ROTTEN_FLESH = register("enchanted_rotten_flesh", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SAND = register("enchanted_sand", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SEEDS = register("enchanted_seeds", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SHARK_FIN = register("enchanted_shark_fin", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SLIME_BALL = register("enchanted_slime_ball", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SLIME_BLOCK = register("enchanted_slime_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SNOW_BLOCK = register("enchanted_snow_block", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SPIDER_EYE = register("enchanted_spider_eye", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SPONGE = register("enchanted_sponge", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SPRUCE_LOG = register("enchanted_spruce_log", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_STRING = register("enchanted_string", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SUGAR = register("enchanted_sugar", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SUGAR_CANE = register("enchanted_sugar_cane", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SULPHUR = register("enchanted_sulphur", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_SULPHUR_CUBE = register("enchanted_sulphur_cube", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_TITANIUM = register("enchanted_titanium", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_TUNGSTEN = register("enchanted_tungsten", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_UMBER = register("enchanted_umber", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_VAMPIRIC_MELON = register("enchanted_vampiric_melon", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_LILY_PAD = register("enchanted_lily_pad", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_WET_SPONGE = register("enchanted_wet_sponge", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_WHEAT = register("enchanted_wheat", EnchantedItem::new, SETTINGS_64);
    public static final Item ENCHANTED_WOOL = register("enchanted_wool", EnchantedItem::new, SETTINGS_64);

    /**
     * Registers an {@link RegistryKey}
     * @param name The name of the group
     * @return The {@link RegistryKey}
     */
    private static RegistryKey<ItemGroup> register(final String name) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering item group: {}", id);
        return RegistryKey.of(Registries.ITEM_GROUP.getKey(), id);
    }

    /**
     * Registers an {@link ItemGroup}
     *
     * @param name The name of the group
     * @param key The {@link RegistryKey} of the group
     * @param stack The icon of the group
     * @return The {@link ItemGroup}
     */
    private static ItemGroup register(final String name, final RegistryKey<ItemGroup> key, final Supplier<ItemStack> stack) {
        final ItemGroup group = FabricItemGroup.builder()
                .icon(stack)
                .displayName(Text.translatable("itemGroup." + name))
                .build();
        return Registry.register(Registries.ITEM_GROUP, key, group);
    }

    /**
     * Registers an {@link Item}
     *
     * @param name The name of the item
     * @param factory The factory of the item
     * @param settings The settings of the item
     * @return The {@link Item}
     */
    private static Item register(final String name, final Function<Item.Settings, Item> factory, final Item.Settings settings) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering item: {}", id);
        final RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        return Items.register(key, factory, settings);
    }

    /**
     * Adds Items to the {@link ItemGroup}
     */
    private static void addItemsToGroup() {
        ItemGroupEvents.modifyEntriesEvent(FARMING_GROUP_KEY).register(entries -> {
            entries.add(ENCHANTED_CACTUS);
            entries.add(ENCHANTED_CACTUS_GREEN);
            entries.add(ENCHANTED_CARROT);
            entries.add(ENCHANTED_CARROT_ON_A_STICK);
            entries.add(ENCHANTED_GOLDEN_CARROT);
            entries.add(ENCHANTED_FEATHER);
            entries.add(ENCHANTED_COCOA);
            entries.add(ENCHANTED_COOKIE);
            entries.add(ENCHANTED_LEATHER);
            entries.add(ENCHANTED_BEEF);
            entries.add(ENCHANTED_COOKED_BEEF);
            entries.add(ENCHANTED_MELON);
            entries.add(ENCHANTED_GLISTERING_MELON);
            entries.add(ENCHANTED_MELON_BLOCK);
            entries.add(ENCHANTED_RED_MUSHROOM);
            entries.add(ENCHANTED_BROWN_MUSHROOM);
            entries.add(ENCHANTED_RED_MUSHROOM_BLOCK);
            entries.add(ENCHANTED_BROWN_MUSHROOM_BLOCK);
            entries.add(ENCHANTED_MUTTON);
            entries.add(ENCHANTED_COOKED_MUTTON);
            entries.add(ENCHANTED_WOOL);
            entries.add(ENCHANTED_NETHER_WART);
            entries.add(ENCHANTED_PORKCHOP);
            entries.add(ENCHANTED_COOKED_PORKCHOP);
            entries.add(ENCHANTED_POTATO);
            entries.add(ENCHANTED_POISONOUS_POTATO);
            entries.add(ENCHANTED_BAKED_POTATO);
            entries.add(ENCHANTED_PUMPKIN);
            entries.add(ENCHANTED_RABBIT);
            entries.add(ENCHANTED_RABBIT_FOOT);
            entries.add(ENCHANTED_RABBIT_HIDE);
            entries.add(ENCHANTED_CHICKEN);
            entries.add(ENCHANTED_EGG);
            entries.add(ENCHANTED_CAKE);
            entries.add(ENCHANTED_SEEDS);
            entries.add(ENCHANTED_PAPER);
            entries.add(ENCHANTED_SUGAR);
            entries.add(ENCHANTED_SUGAR_CANE);
            entries.add(ENCHANTED_BOOKSHELF);
            entries.add(ENCHANTED_WHEAT);
            entries.add(ENCHANTED_BREAD);
            entries.add(ENCHANTED_HAY_BALE);
            entries.add(ENCHANTED_COMPOST);
        });
        ItemGroupEvents.modifyEntriesEvent(MINING_GROUP_KEY).register(entries -> {
            entries.add(ENCHANTED_COAL);
            entries.add(ENCHANTED_CHARCOAL);
            entries.add(ENCHANTED_COAL_BLOCK);
            entries.add(ENCHANTED_LAVA_BUCKET);
            entries.add(ENCHANTED_COBBLESTONE);
            entries.add(ENCHANTED_DIAMOND);
            entries.add(ENCHANTED_DIAMOND_BLOCK);
            entries.add(ENCHANTED_EMERALD);
            entries.add(ENCHANTED_EMERALD_BLOCK);
            entries.add(ENCHANTED_ENDSTONE);
            entries.add(ENCHANTED_GLACITE);
            entries.add(ENCHANTED_TUNGSTEN);
            entries.add(ENCHANTED_UMBER);
            entries.add(ENCHANTED_GLOWSTONE_DUST);
            entries.add(ENCHANTED_GLOWSTONE);
            entries.add(ENCHANTED_REDSTONE_LAMP);
            entries.add(ENCHANTED_GOLD_INGOT);
            entries.add(ENCHANTED_GOLD_BLOCK);
            entries.add(ENCHANTED_CLOCK);
            entries.add(ENCHANTED_FLINT);
            entries.add(ENCHANTED_HARD_STONE);
            entries.add(ENCHANTED_ICE);
            entries.add(ENCHANTED_PACKED_ICE);
            entries.add(ENCHANTED_LAPIS);
            entries.add(ENCHANTED_LAPIS_BLOCK);
            entries.add(ENCHANTED_IRON);
            entries.add(ENCHANTED_IRON_BLOCK);
            entries.add(ENCHANTED_HOPPER);
            entries.add(ENCHANTED_MITHRIL);
            entries.add(ENCHANTED_MYCELIUM);
            entries.add(ENCHANTED_MYCELIUM_CUBE);
            entries.add(ENCHANTED_NETHERRACK);
            entries.add(ENCHANTED_OBSIDIAN);
            entries.add(ENCHANTED_QUARTZ);
            entries.add(ENCHANTED_QUARTZ_BLOCK);
            entries.add(ENCHANTED_REDSTONE);
            entries.add(ENCHANTED_REDSTONE_BLOCK);
            entries.add(ENCHANTED_RED_SAND);
            entries.add(ENCHANTED_RED_SAND_CUBE);
            entries.add(ENCHANTED_SAND);
            entries.add(ENCHANTED_SULPHUR);
            entries.add(ENCHANTED_SULPHUR_CUBE);
            entries.add(ENCHANTED_SNOW_BLOCK);
            entries.add(ENCHANTED_TITANIUM);
        });
        ItemGroupEvents.modifyEntriesEvent(COMBAT_GROUP_KEY).register(entries -> {
            entries.add(ENCHANTED_BLAZE_POWDER);
            entries.add(ENCHANTED_BLAZE_ROD);
            entries.add(ENCHANTED_BONE_MEAL);
            entries.add(ENCHANTED_BONE);
            entries.add(ENCHANTED_BONE_BLOCK);
            entries.add(ENCHANTED_ENDER_PEARL);
            entries.add(ENCHANTED_EYE_OF_ENDER);
            entries.add(ENCHANTED_GHAST_TEAR);
            entries.add(ENCHANTED_MAGMA_CREAM);
            entries.add(ENCHANTED_ROTTEN_FLESH);
            entries.add(ENCHANTED_SLIME_BALL);
            entries.add(ENCHANTED_SLIME_BLOCK);
            entries.add(ENCHANTED_SPIDER_EYE);
            entries.add(ENCHANTED_FERMENTED_SPIDER_EYE);
            entries.add(ENCHANTED_STRING);
            entries.add(ENCHANTED_GUNPOWDER);
            entries.add(ENCHANTED_FIREWORK_ROCKET);
        });
        ItemGroupEvents.modifyEntriesEvent(FORAGING_GROUP_KEY).register(entries -> {
            entries.add(ENCHANTED_SPRUCE_LOG);
            entries.add(ENCHANTED_BIRCH_LOG);
            entries.add(ENCHANTED_JUNGLE_LOG);
            entries.add(ENCHANTED_OAK_LOG);
            entries.add(ENCHANTED_DARK_OAK_LOG);
            entries.add(ENCHANTED_ACACIA_LOG);
        });
        ItemGroupEvents.modifyEntriesEvent(FISHING_GROUP_KEY).register(entries -> {
            entries.add(ENCHANTED_CLAY_BALL);
            entries.add(ENCHANTED_CLAY_BLOCK);
            entries.add(ENCHANTED_INK_SAC);
            entries.add(ENCHANTED_PRISMARINE_CRYSTALS);
            entries.add(ENCHANTED_PRISMARINE_SHARD);
            entries.add(ENCHANTED_SALMON);
            entries.add(ENCHANTED_COOKED_SALMON);
            entries.add(ENCHANTED_CLOWNFISH);
            entries.add(ENCHANTED_PUFFERFISH);
            entries.add(ENCHANTED_FISH);
            entries.add(ENCHANTED_COOKED_FISH);
            entries.add(ENCHANTED_SPONGE);
            entries.add(ENCHANTED_WET_SPONGE);
            entries.add(ENCHANTED_LILY_PAD);
            entries.add(ENCHANTED_SHARK_FIN);
        });
        ItemGroupEvents.modifyEntriesEvent(BOSS_GROUP_KEY).register(entries -> {

        });
        ItemGroupEvents.modifyEntriesEvent(RIFT_GROUP_KEY).register(entries -> {
            entries.add(ENCHANTED_FIG_LOG);
            entries.add(ENCHANTED_VAMPIRIC_MELON);
        });
    }

    public static void load() {
        SkyBlock.LOGGER.info("Loading items");

        addItemsToGroup();
    }
}
