2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Network/Vec3fPacket.java
package com.naive.phase.Network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;

public abstract class Vec3fPacket<REQ extends AbstractPacket<REQ>> extends AbstractPacket<REQ> {
    protected float x, y, z;

    public Vec3fPacket() {

    }

    public Vec3fPacket(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(x);
        buf.writeFloat(y);
        buf.writeFloat(z);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        x = buf.readFloat();
        y = buf.readFloat();
        z = buf.readFloat();
    }
}
