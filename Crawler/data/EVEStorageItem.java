2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/EVE/Storage/EVEStorageItem.java
package com.naive.phase.Auxiliary.Magic.EVE.Storage;

import com.naive.phase.Auxiliary.Helper.DataHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Eve.Eve;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IEVEStorageItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EVEStorageItem implements IEVEStorageItem, ICapabilityProvider {
    private static final String EVE_NBT_KEY = "eve";

    protected ItemStack container;
    protected final Range hueRange;
    protected final Range brightnessRange;
    protected final int capacity;

    public EVEStorageItem(@Nonnull ItemStack container, Range hueRange, Range brightnessRange, int capacity) {
        this.container = container;
        this.hueRange = hueRange;
        this.brightnessRange = brightnessRange;
        this.capacity = capacity;
    }

    @Override
    public ItemStack getContainer() {
        return container;
    }

    @Override
    public Eve getEVE() {
        NBTTagCompound tag = container.getTagCompound();
        Eve blankEve = new Eve(0, capacity, hueRange.getMid(), brightnessRange.getMid(), 0);
        if (tag == null || !tag.hasKey(EVE_NBT_KEY))
            return blankEve;
        return blankEve.readFromNBT(tag.getCompoundTag(EVE_NBT_KEY));
    }

    @Override
    public void setEVE(Eve incoming) {
        if (container.getTagCompound() == null)
            container.setTagCompound(new NBTTagCompound());

        container.getTagCompound().setTag(EVE_NBT_KEY, incoming.writeToNBT());
    }

    @Override
    public Eve blendEVE(Eve incoming, boolean simulate) {
        Pairs<Eve, Eve> result = DataHelper.blend(getEVE(), incoming);
        if (!simulate)
            setEVE(result.getFirst());
        return result.getSecond();
    }

    @Override
    public Range getHueRange() {
        return hueRange;
    }

    @Override
    public Range getBrightnessRange() {
        return brightnessRange;
    }

    @Override
    public boolean canBlend(Eve incoming) {
        return getHueRange().in(incoming.getHue()) && getBrightnessRange().in(incoming.getBrightness());
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEVE.EVE_ITEM;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEVE.EVE_ITEM ? (T) this : null;
    }
}
