2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemUpgrades/ItemUpgradeBlank.java
package com.naive.phase.Item.ItemUpgrades;

import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Item.PhaseItemBase;
import com.naive.phase.Item.ItemMatrix.IUpgrade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemUpgradeBlank extends PhaseItemBase implements IUpgrade {

    @Registry.ItemInst
    public static ItemUpgradeBlank itemInst;

    public ItemUpgradeBlank() {
        this("upgrade_blank");
    }

    public ItemUpgradeBlank(String registryName) {
        super(registryName);
    }

    @Override
    public void onUseItem(World world, EntityPlayer player, ItemStack upgrade, ItemStack matrix, ItemStack tool) {

    }

    @Override
    public int getEnergyCost() {
        return 0;
    }

}
