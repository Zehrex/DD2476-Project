2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Item/IPhaseToolGenXPlus.java
package com.naive.phase.Base.Item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public interface IPhaseToolGenXPlus {
    ItemStack getMatrix(ItemStack stack);

    IItemHandler getGenXInventory(ItemStack stack);
}
