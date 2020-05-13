2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemMatrix/ContainerMatrix.java
package com.naive.phase.Item.ItemMatrix;

import com.naive.phase.GUI.SlotLocked;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerMatrix extends Container {
    private final InventoryMatrix inv;

    public ContainerMatrix(EntityPlayer player, InventoryMatrix inv) {
        this.inv = inv;
        int rows = inv.getSlots() / 4;
        IInventory playerInv = player.inventory;

        for (int i = 0; i < inv.getSlots(); i++)
            addSlotToContainer(new SlotUnmodifiable(inv, i, 53 + 18 * (i % 4), 18 + 18 * (i / 4)));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 38 + i * 18 + rows * 18));
        for (int i = 0; i < 9; ++i)
            if (playerInv.getStackInSlot(i) == inv.matrix)
                addSlotToContainer(new SlotLocked(playerInv, i, 8 + i * 18, 96 + rows * 18));
            else addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 96 + rows * 18));
    }

    private static class SlotUnmodifiable extends SlotItemHandler {

        public SlotUnmodifiable(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean canTakeStack(EntityPlayer playerIn) {
            return false;
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getHeldItemMainhand() == inv.matrix || playerIn.getHeldItemOffhand() == inv.matrix;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            itemstack = stack1.copy();

            int matrixStart = 0;
            int matrixEnd = inv.getSlots();
            int invEnd = matrixEnd + 36;

            if (index < matrixEnd) {
                if (!mergeItemStack(stack1, matrixEnd, invEnd, true))
                    return ItemStack.EMPTY;
            } else {
                if (!itemstack.isEmpty() && !mergeItemStack(stack1, matrixStart, matrixEnd, false))
                    return ItemStack.EMPTY;
            }

            if (stack1.isEmpty())
                slot.putStack(ItemStack.EMPTY);
            else slot.onSlotChanged();

            if (stack1.getCount() == itemstack.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, stack1);
        }
        return itemstack;
    }
}
