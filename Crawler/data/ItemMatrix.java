2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemMatrix/ItemMatrix.java
package com.naive.phase.Item.ItemMatrix;

import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Item.PhaseItemBase;
import com.naive.phase.GUI.GUILib;
import com.naive.phase.Phase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemMatrix extends PhaseItemBase {

    private static final String TAG_ITEMS = "InvItems";
    private final int slotSize;
    private final int slotLimit;

    @Registry.ItemInst
    public static ItemMatrix itemInst;

    public ItemMatrix() {
        this("matrix", 1000, 4);
    }

    public ItemMatrix(String registryName, int maxDamage, int slotSize) {
        super(registryName);
        setMaxDamage(maxDamage);
        setMaxStackSize(1);
        this.slotSize = slotSize;
        this.slotLimit = 1;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new MatrixInventory(this.slotSize, this.slotLimit);
    }

    private static class MatrixInventory implements ICapabilitySerializable<NBTBase> {

        private final IItemHandler inv;

        MatrixInventory(int slotSize, int slotLimit) {
            inv = new ItemStackHandlerMatrix(slotSize, slotLimit) {
                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    if (!(stack.getItem() instanceof IUpgrade))
                        return stack;
                    return super.insertItem(slot, stack, simulate);
                }
            };
        }

        private static class ItemStackHandlerMatrix extends ItemStackHandler {
            private final int slotLimit;

            ItemStackHandlerMatrix(int size, int slotLimit) {
                super(size);
                this.slotLimit = slotLimit;
            }

            @Override
            public int getSlotLimit(int slot) {
                return slotLimit;
            }
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);
        }

    }

    @Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack) {
        NBTTagCompound tag = super.getNBTShareTag(stack);
        if (tag == null)
            tag = new NBTTagCompound();

        IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if (handler != null)
            tag.setTag(TAG_ITEMS, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null));
        return tag;
    }

    @Override
    public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
        super.readNBTShareTag(stack, nbt);
        if (nbt == null)
            return;
        NBTBase tag = nbt.getTag(TAG_ITEMS);
        IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null, tag);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.openGui(Phase.inst, GUILib.GUI_MATRIX, worldIn, handIn == EnumHand.OFF_HAND ? 1 : 0, 0, 0);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    public static void damageMatrix(ItemStack matrix, ItemStack tool, World world, EntityPlayer player, int amount) {
        IItemHandler handler = matrix.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if (handler != null) {
            for (int i = 0; i < handler.getSlots(); i++) {
                ItemStack upgrade = handler.getStackInSlot(i);
                if (upgrade.getItem() instanceof IUpgrade) {
                    if (!((IUpgrade) upgrade.getItem()).onDamageItem(world, player, upgrade, matrix, tool, amount))
                        return;
                }
            }
            matrix.damageItem(1, player);
        }
    }
}
