package net.skyblock.init;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.skyblock.SkyBlock;
import net.skyblock.item.EnchantedItem;
import net.skyblock.item.MenuItem;

import java.util.function.Function;

public class ItemInit {
    private static final Item.Settings SETTINGS_01 = new Item.Settings().fireproof().maxCount(1);
    private static final Item.Settings SETTINGS_64 = new Item.Settings().fireproof().maxCount(64);

    public static final Item MENU_ITEM = register("menu_item", MenuItem::new, SETTINGS_01);
    public static final Item ENCHANTED_COBBLESTONE = register("enchanted_cobblestone", EnchantedItem::new, SETTINGS_64);

    public static Item register(final String name, final Function<Item.Settings, Item> factory, final Item.Settings settings) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering item: {}", id);
        final RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);
        return Items.register(key, factory, settings);
    }

    public static void load() {
        SkyBlock.LOGGER.info("Loading items");
    }
}
