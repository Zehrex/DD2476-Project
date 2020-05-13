2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemBattery/ItemBattery.java
package com.naive.phase.Item.ItemBattery;

import com.naive.phase.Auxiliary.Instantiable.Data.Eve.Eve;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IEVEStorageItem;
import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Item.PhaseItemEveBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemBattery extends PhaseItemEveBase {

    @Registry.ItemInst
    public static ItemBattery itemInst;

    public ItemBattery() {
        this("battery", new Range(0f, 1f), new Range(0f, 1f), 1000);
    }

    public ItemBattery(String registryName, Range hueRange, Range brightRange, int capacity) {
        super(registryName, hueRange, brightRange, capacity);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);
            IEVEStorageItem eve = stack.getCapability(CapabilityEVE.EVE_ITEM, null);
            eve.setEVE(new Eve(0, 1000, 0, 1, 1));
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
