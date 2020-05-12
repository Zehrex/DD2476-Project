2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/ARad/Interfaces/IARadEffective.java
package com.naive.phase.Auxiliary.Magic.ARad.Interfaces;

import com.naive.phase.Auxiliary.Magic.ARad.ARadNetworkManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IARadEffective {

    World getWorld();

    BlockPos getPos();

    String getUUID();

    void onARadNetworkChange(ARadNetworkManager.NetworkEventType type, IARadEffective toAdd);

    void onRegister();

    void onRemove();
}
