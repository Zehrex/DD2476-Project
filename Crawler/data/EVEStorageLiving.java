2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/EVE/Storage/EVEStorageLiving.java
package com.naive.phase.Auxiliary.Magic.EVE.Storage;

import com.naive.phase.Auxiliary.Helper.DataHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Eve.Eve;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IEVEStorageEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EVEStorageLiving implements IEVEStorageEntity, ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    private final EntityLivingBase living;
    private final Range hueRange;
    private final Range brightnessRange;
    private final int capacity;
    private static final String TAG_EVE = "eve_energy";

    public EVEStorageLiving(EntityLivingBase living, Range hueRange, Range brightnessRange, int capacity) {
        this.living = living;
        this.hueRange = hueRange;
        this.capacity = capacity;
        this.brightnessRange = brightnessRange;
    }

    @Override
    public EntityLivingBase getLiving() {
        return living;
    }

    @Override
    public Eve getEVE() {
        NBTTagCompound tag = living.getEntityData();
        return tag.hasKey(TAG_EVE) ?
                DataHelper.loadEVEfromNBT(tag) :
                new Eve(0, getCapacity(), getHueRange().getMid(), getBrightnessRange().getMid(), 0);
    }

    @Override
    public void setEVE(Eve incoming) {
        living.getEntityData().setTag(TAG_EVE, incoming.writeToNBT());
    }

    @Override
    public Eve blendEVE(Eve incoming, boolean simulate) {
        return null;
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
        return capability == CapabilityEVE.EVE_ENTITY;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEVE.EVE_ENTITY ? (T) this : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return getEVE().writeToNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        setEVE(getEVE().readFromNBT(nbt));
    }

    public int getCapacity() {
        return capacity;
    }
}
