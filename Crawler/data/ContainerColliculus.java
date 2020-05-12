2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemColliculus/ContainerColliculus.java
package com.naive.phase.Item.ItemColliculus;

import com.naive.phase.GUI.SlotLocked;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerColliculus extends Container {

    private final InventoryColliculus inv;

    public ContainerColliculus(EntityPlayer player, InventoryColliculus inv) {
        this.inv = inv;
        IInventory playerInv = player.inventory;
        addSlotToContainer(new SlotItemHandler(inv, 0, 53, 20));
        addSlotToContainer(new SlotItemHandler(inv, 1, 53 + 18, 20));
        addSlotToContainer(new SlotItemHandler(inv, 2, 53 + 18 * 2, 20));
        addSlotToContainer(new SlotItemHandler(inv, 3, 53 + 18 * 3, 20));

        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 51 + i * 18));
        for (int i = 0; i < 9; ++i)
            if (playerInv.getStackInSlot(i) == inv.colliculus)
                addSlotToContainer(new SlotLocked(playerInv, i, 8 + i * 18, 108));
            else addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 108));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getHeldItemMainhand() == inv.colliculus || playerIn.getHeldItemOffhand() == inv.colliculus;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack1 = slot.getStack();
            itemstack = stack1.copy();

            int occStart = 0;
            int occEnd = occStart + 4;
            int invEnd = occEnd + 36;

            if (index < occEnd) {
                if (!mergeItemStack(stack1, occEnd, invEnd, true))
                    return ItemStack.EMPTY;
            } else {
                if (!itemstack.isEmpty() && !mergeItemStack(stack1, occStart, occEnd, false))
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
