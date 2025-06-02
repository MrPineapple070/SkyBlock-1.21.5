package net.skyblock.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.skyblock.SkyBlock;

public class MenuItemPayload implements CustomPayload {
    public static final Id<MenuItemPayload> ID = new Id<>(Identifier.of(SkyBlock.MOD_ID, "open_menu"));
    public static final PacketCodec<RegistryByteBuf, MenuItemPayload> PACKET_CODEC = PacketCodec.of(MenuItemPayload::encode, MenuItemPayload::decode);

    private static MenuItemPayload decode(RegistryByteBuf registryByteBuf) {
        return new MenuItemPayload();
    }

    private void encode(RegistryByteBuf registryByteBuf) {
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
