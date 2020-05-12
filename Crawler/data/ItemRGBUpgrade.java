2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemUpgrades/ItemRGBUpgrade.java
package com.naive.phase.Item.ItemUpgrades;

import com.naive.phase.Auxiliary.Register.Registry;

public class ItemRGBUpgrade extends ItemUpgradeBlank {

    @Registry.ItemInst
    public static ItemRGBUpgrade itemInst;

    public ItemRGBUpgrade() {
        super("upgrade_rgb");
    }

    @Override
    public int getEnergyCost() {
        return 10;
    }
}
