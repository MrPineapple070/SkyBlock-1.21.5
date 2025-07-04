package net.skyblock;

import net.fabricmc.api.ModInitializer;
import net.skyblock.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkyBlock implements ModInitializer {
    public static final String MOD_ID = "skyblock";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");

        ItemInit.load();
        ScreenHandlerInit.load();
        RecipeInit.load();
        EnchantmentInit.load();
        CommandInit.load();
    }
}