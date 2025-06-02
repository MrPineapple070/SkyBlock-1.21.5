package net.skyblock.item;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.skyblock.network.MenuItemPayload;
import net.skyblock.screen.ItemScreenHandler;
import org.jetbrains.annotations.Nullable;

public class MenuItem extends Item {
    public static final Text NAME = Text.translatable("skyblock.item_screen.title");

    private static class ItemScreenHandlerFactory implements ExtendedScreenHandlerFactory<MenuItemPayload> {
        public ItemScreenHandlerFactory() {
        }

        @Override
        public MenuItemPayload getScreenOpeningData(ServerPlayerEntity player) {
            return new MenuItemPayload();
        }

        @Override
        public Text getDisplayName() {
            return NAME;
        }

        @Override
        public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
            return new ItemScreenHandler(syncId, playerInventory);
        }
    }

    public MenuItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient)
            user.openHandledScreen(this.createScreenHandlerFactory());
        return ActionResult.SUCCESS;
    }

    private NamedScreenHandlerFactory createScreenHandlerFactory() {
        return new ItemScreenHandlerFactory();
    }
}
