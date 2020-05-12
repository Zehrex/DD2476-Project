2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Tile/TileBase.java
package com.naive.phase.Base.Tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public abstract class TileBase extends TileEntity {

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound tag = super.writeToNBT(compound);
        writeSyncNBT(tag);
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readSyncNBT(compound);
    }

    @Override
    public final NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    public abstract void writeSyncNBT(NBTTagCompound compound);

    public abstract void readSyncNBT(NBTTagCompound compound);

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeSyncNBT(tag);
        return new SPacketUpdateTileEntity(pos, -114514, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        readSyncNBT(pkt.getNbtCompound());
    }
}
