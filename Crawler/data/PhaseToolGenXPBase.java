2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Item/PhaseToolGenXPBase.java
package com.naive.phase.Base.Item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class PhaseToolGenXPBase extends PhaseItemBase implements IPhaseToolGenXPlus {
    private static final String TAG_ITEMS = "InvItems";
    private static final String TAG_ENERGY = "energy";

    public PhaseToolGenXPBase(String registryName) {
        super(registryName);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new defaultCapabilities(new ItemStackHandler(3), 1000, 10);
    }

    protected class defaultCapabilities implements ICapabilitySerializable<NBTTagCompound> {

        private final ItemStackHandler invHandler;
        private final EnergyStorage energyHandler;

        defaultCapabilities(ItemStackHandler inv, int FECapacity, int power) {
            this.invHandler = inv;
            this.energyHandler = new EnergyStorage(FECapacity, power, 0, 0);
        }

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityEnergy.ENERGY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(invHandler);
            else if (capability == CapabilityEnergy.ENERGY)
                return CapabilityEnergy.ENERGY.cast(energyHandler);

            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag(TAG_ITEMS, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(invHandler, null));
            tag.setTag(TAG_ENERGY, CapabilityEnergy.ENERGY.writeNBT(energyHandler, null));

            return tag;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            if (nbt != null) {
                CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(invHandler, null, nbt.getCompoundTag(TAG_ITEMS));
                CapabilityEnergy.ENERGY.readNBT(energyHandler, null, nbt.getCompoundTag(TAG_ENERGY));
            }
        }
    }
}
