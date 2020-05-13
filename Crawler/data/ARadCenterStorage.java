2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/EVE/Storage/ARadCenterStorage.java
package com.naive.phase.Auxiliary.Magic.EVE.Storage;

import com.naive.phase.Auxiliary.Helper.MathHelper;
import com.naive.phase.Auxiliary.Helper.NBTHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IARadCenter;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ARadCenterStorage implements IARadCenter, ICapabilityProvider, INBTSerializable<NBTTagCompound> {

    private final Chunk chunk;
    private Range hueRange;
    private Range brightnessRange;

    public ARadCenterStorage(Chunk chunk, Range hueRange, Range brightnessRange) {
        this.hueRange = hueRange;
        this.brightnessRange = brightnessRange;
        this.chunk = chunk;
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
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEVE.EVE_CHUNK;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEVE.EVE_CHUNK ? (T) this : null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setTag("hue", NBTHelper.fromRange(hueRange));
        tag.setTag("brightness", NBTHelper.fromRange(brightnessRange));
        return tag;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("hue"))
            hueRange = NBTHelper.getRange((NBTTagCompound) nbt.getTag("hue"));
        else
            hueRange = MathHelper.randRange();

        if (nbt.hasKey("brightness"))
            brightnessRange = NBTHelper.getRange((NBTTagCompound) nbt.getTag("brightness"));
        else
            hueRange = MathHelper.randRange(0.2f, 0.8f);
    }
}
