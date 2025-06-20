package net.skyblock.init;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.skyblock.SkyBlock;
import net.skyblock.network.MenuItemPayload;
import net.skyblock.screen.ItemScreenHandler;

public class ScreenHandlerInit {
    public static final ScreenHandlerType<ItemScreenHandler> ITEM_SCREEN_HANDLER = register("item_screen", ItemScreenHandler::new, MenuItemPayload.PACKET_CODEC);

    private static <Handler extends ScreenHandler, Payload extends CustomPayload> ExtendedScreenHandlerType<Handler, Payload> register(final String name, final ExtendedScreenHandlerType.ExtendedFactory<Handler, Payload> factory, final PacketCodec<? super RegistryByteBuf, Payload> codec) {
        final Identifier id = Identifier.of(SkyBlock.MOD_ID, name);
        SkyBlock.LOGGER.info("Registering screen handler: {}", id);
        return Registry.register(Registries.SCREEN_HANDLER, id, new ExtendedScreenHandlerType<>(factory, codec));
    }

    public static void load() {
        SkyBlock.LOGGER.info("Loading screen handlers");
    }
}
