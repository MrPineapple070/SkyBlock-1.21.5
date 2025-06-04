package net.skyblock;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.skyblock.init.PayloadInit;
import net.skyblock.init.ScreenHandlerInit;
import net.skyblock.screen.ItemScreen;

public class SkyBlockClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PayloadInit.load();

        ScreenHandlerInit.load();
        HandledScreens.register(ScreenHandlerInit.ITEM_SCREEN_HANDLER, ItemScreen::new);
    }
}