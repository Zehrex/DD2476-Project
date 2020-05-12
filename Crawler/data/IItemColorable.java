2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Item/IItemColorable.java
package com.naive.phase.Base.Item;

import net.minecraft.item.ItemStack;

public interface IItemColorable {
    void setColor(ItemStack stack, float h, float s, float b);
}
