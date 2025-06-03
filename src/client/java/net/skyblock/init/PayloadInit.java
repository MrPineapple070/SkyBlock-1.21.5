package net.skyblock.init;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.skyblock.network.MenuItemPayload;

public class PayloadInit {
    public static void load() {
        PayloadTypeRegistry.playS2C().register(MenuItemPayload.ID, MenuItemPayload.PACKET_CODEC);
        PayloadTypeRegistry.playC2S().register(MenuItemPayload.ID, MenuItemPayload.PACKET_CODEC);
    }
}
