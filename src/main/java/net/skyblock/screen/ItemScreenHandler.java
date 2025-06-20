package net.skyblock.screen;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.NbtWriteView;
import net.minecraft.util.ErrorReporter;
import net.skyblock.SkyBlock;
import net.skyblock.init.ScreenHandlerInit;
import net.skyblock.item.MenuItem;
import net.skyblock.network.MenuItemPayload;
import org.jetbrains.annotations.NotNull;

public class ItemScreenHandler extends ScreenHandler {
    private static final ErrorReporter reporter = new ErrorReporter.Logging(SkyBlock.LOGGER);

    private final SimpleInventory inventory;
    private final ItemStack stack;

    public ItemScreenHandler(final int syncId, final @NotNull PlayerInventory playerInventory, final MenuItemPayload ignored) {
        this(syncId, playerInventory);
    }

    public ItemScreenHandler(final int syncId, final @NotNull PlayerInventory playerInventory) {
        super(ScreenHandlerInit.ITEM_SCREEN_HANDLER, syncId);
        this.inventory = new SimpleInventory(54);
        this.stack = playerInventory.player.getMainHandStack();
        this.loadInventory(playerInventory.player.getRegistryManager());
        this.addInventorySlots(this.inventory);
        this.addPlayerSlots(playerInventory, 8, 140);
        this.inventory.onOpen(playerInventory.player);
        this.inventory.onClose(playerInventory.player);
    }

    @Override
    public ItemStack quickMove(final PlayerEntity player, final int slotIndex) {
        ItemStack stack = ItemStack.EMPTY;
        final Slot slot = this.getSlot(slotIndex);

        if (slot != null && slot.hasStack()) {
            final ItemStack inSlot = slot.getStack();
            stack = inSlot.copy();

            if (slotIndex < this.inventory.size()) {
                if (!this.insertItem(inSlot, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(inSlot, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (inSlot.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return stack;
    }

    @Override
    public boolean canUse(final PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public void onSlotClick(final int slotId, final int clickData, final SlotActionType actionType, final PlayerEntity playerEntity) {
        if (slotId >= 0) { // slotId < 0 are used for networking internals
            final ItemStack stack = this.getSlot(slotId).getStack();
            if (stack.getItem() instanceof MenuItem) return;
        }

        super.onSlotClick(slotId, clickData, actionType, playerEntity);
    }

    @Override
    public void onClosed(final PlayerEntity player) {
        super.onClosed(player);
        this.saveInventory(player.getRegistryManager());
    }

    /**
     * Load the inventory from the given stack. The stack is expected to have
     * custom data containing the inventory in the format written by
     * {@link #saveInventory(RegistryWrapper.WrapperLookup)}.
     *
     * @param registry the registry to use for loading the inventory
     */
    private void loadInventory(final RegistryWrapper.WrapperLookup registry) {
        this.inventory.clear();
        final NbtComponent nbt = this.stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        final NbtCompound tag = nbt.copyNbt();
        Inventories.readData(NbtReadView.create(reporter, registry, tag), this.inventory.getHeldStacks());
    }

    /**
     * Saves the current inventory state to the stack's custom data.
     *
     * @param registry the registry to use for saving the inventory
     */
    private void saveInventory(final RegistryWrapper.WrapperLookup registry) {
        final NbtWriteView writer = NbtWriteView.create(reporter, registry);
        Inventories.writeData(writer, this.inventory.getHeldStacks());
        this.stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(writer.getNbt()));
    }

    /**
     * Adds the 6x9 {@link SimpleInventory} {@link Slot}s to the screen handler.
     *
     * @param inventory the inventory
     */
    private void addInventorySlots(final SimpleInventory inventory) {
        for (int row = 0; row < 6; ++row) {
            for (int col = 0; col < 9; ++col) {
                final int index = col + row * 9;
                this.addSlot(new Slot(inventory, index, 8 + col * 18, 18 + row * 18));
            }
        }
    }
}
