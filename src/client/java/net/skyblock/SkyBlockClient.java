package net.skyblock;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.skyblock.init.ScreenHandlerInit;
import net.skyblock.network.MenuItemPayload;
import net.skyblock.screen.ItemScreen;

public class SkyBlockClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PayloadTypeRegistry.playS2C().register(MenuItemPayload.ID, MenuItemPayload.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(MenuItemPayload.ID, MenuItemPayload.PACKET_CODEC);

        ClientPlayNetworking.registerGlobalReceiver(MenuItemPayload.ID, (payload, context) -> {});

        HandledScreens.register(ScreenHandlerInit.ITEM_SCREEN_HANDLER, ItemScreen::new);
        ScreenHandlerInit.load();
    }
}