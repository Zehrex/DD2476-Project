2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemPrism/ItemPrism.java
package com.naive.phase.Item.ItemPrism;

import com.naive.phase.Auxiliary.Helper.NBTHelper;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IARadCenter;
import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Item.PhaseItemBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.awt.*;

public class ItemPrism extends PhaseItemBase {

    @Registry.ItemInst
    public static ItemPrism itemPrism;

    public ItemPrism() {
        super("prism");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        IARadCenter center = worldIn.getChunkFromBlockCoords(new BlockPos(entityIn.posX, entityIn.posY, entityIn.posZ)).getCapability(CapabilityEVE.EVE_CHUNK, null);
        if (center == null)
            return;

        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setFloat("hue", center.getHueRange().getMid());
        stack.getTagCompound().setFloat("brightness", center.getBrightnessRange().getMid());

    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 0.0;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        float hue = NBTHelper.getOrDefault(stack.getTagCompound(), "hue", 0f);
        float brightness = NBTHelper.getOrDefault(stack.getTagCompound(), "brightness", 0f);
        return Color.HSBtoRGB(hue, 1f, brightness);
    }
}
