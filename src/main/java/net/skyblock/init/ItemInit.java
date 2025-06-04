package net.skyblock.init;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.skyblock.SkyBlock;
import net.skyblock.item.MenuItem;

import java.util.function.Function;

public class ItemInit {
    public static final Item MENU_ITEM = register("menu_item", MenuItem::new, new Item.Settings());

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
