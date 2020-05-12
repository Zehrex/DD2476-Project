2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Tile/TileEveBase.java
package com.naive.phase.Base.Tile;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Storage.EVEStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileEveBase extends TileBase {

    protected EVEStorage energyHandlerDefault;
    private static final String TAG_EVE = "eve_energy";

    public TileEveBase(int capacity, Range hueRange, Range brightRange) {
        this.energyHandlerDefault = new EVEStorage(capacity, hueRange, brightRange);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEVE.EVE || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEVE.EVE) {
            return CapabilityEVE.EVE.cast(energyHandlerDefault);
        } else
            return super.getCapability(capability, facing);
    }

    @Override
    public void readSyncNBT(NBTTagCompound compound) {
        energyHandlerDefault.getEVE().readFromNBT(compound.getCompoundTag(TAG_EVE));
    }

    @Override
    public void writeSyncNBT(NBTTagCompound compound) {
        compound.setTag(TAG_EVE, energyHandlerDefault.getEVE().writeToNBT());
    }
}
