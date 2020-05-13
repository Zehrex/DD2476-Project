2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Auxiliary/Magic/EVE/Interface/IEVEStorageItem.java
package com.naive.phase.Auxiliary.Magic.EVE.Interface;

import com.naive.phase.Auxiliary.Magic.EVE.Interface.IEVEStorage;
import net.minecraft.item.ItemStack;

public interface IEVEStorageItem extends IEVEStorage {
    ItemStack getContainer();
}
