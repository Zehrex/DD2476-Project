2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Instantiable/Interface/INBTSerializable.java
package com.naive.phase.Auxiliary.Instantiable.Interface;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTSerializable {
    NBTTagCompound writeToNBT();

    void readFromNBT(NBTTagCompound tag);
}
