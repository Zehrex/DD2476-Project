2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Tile/TileARadBase.java
package com.naive.phase.Base.Tile;

import com.naive.phase.Auxiliary.Helper.NBTHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.ARad.ARadNetworkManager;
import com.naive.phase.Auxiliary.Magic.ARad.Interfaces.IARadEffective;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

public class TileARadBase extends TileEveBase implements IARadEffective {

    private String uuid;
    private static final String TAG_UUID = "arad_uuid";

    public TileARadBase(int capacity, Range hueRange, Range brightRange) {
        super(capacity, hueRange, brightRange);
    }

    @Override
    public String getUUID() {
        return uuid;
    }

    @Override
    public void onARadNetworkChange(ARadNetworkManager.NetworkEventType type, IARadEffective toAdd) {

    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onRemove() {

    }

    @Override
    public void readSyncNBT(NBTTagCompound compound) {
        super.readSyncNBT(compound);
        uuid = NBTHelper.getOrDefault(compound, TAG_UUID, UUID.randomUUID().toString());
    }

    @Override
    public void writeSyncNBT(NBTTagCompound compound) {
        super.writeSyncNBT(compound);
        compound.setString(TAG_UUID, uuid == null || uuid.isEmpty() ? UUID.randomUUID().toString() : uuid);
    }

    @Override
    public void validate() {
        super.validate();
        this.readFromNBT(getUpdateTag());
        if (!world.isRemote) {
            ARadNetworkManager.instance.register(this.uuid, this);
        }
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (!world.isRemote)
            ARadNetworkManager.instance.remove(this.uuid);
    }
}
