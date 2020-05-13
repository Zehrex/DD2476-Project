2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Tile/TileEnergyBase.java
package com.naive.phase.Base.Tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.energy.EnergyStorage;

public class TileEnergyBase extends TileBase {
    protected EnergyStorage energyStorageDefault;

    public TileEnergyBase(EnergyStorage storage) {
        this.energyStorageDefault = storage;
    }

    public TileEnergyBase(int capacity, int send, int receive) {
        this.energyStorageDefault = new EnergyStorage(capacity, receive, send);
    }

    @Override
    public void writeSyncNBT(NBTTagCompound compound) {

    }

    @Override
    public void readSyncNBT(NBTTagCompound compound) {

    }
}
