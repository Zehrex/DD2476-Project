2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Item/PhaseItemEveBase.java
package com.naive.phase.Base.Item;

import com.naive.phase.Auxiliary.Helper.DataHelper;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.Auxiliary.Magic.EVE.Storage.EVEStorageItem;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class PhaseItemEveBase extends PhaseItemBase {

    private final Range hueRange;
    private final Range brightRange;
    private final int capacity;

    public PhaseItemEveBase(String registryName, Range hueRange, Range brightRange, int capacity) {
        super(registryName);

        this.hueRange = hueRange;
        this.brightRange = brightRange;
        this.capacity = capacity;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new EVEStorageItem(stack, hueRange, brightRange, capacity);
    }

    private boolean getShowState(ItemStack stack) {
        return !Minecraft.getMinecraft().player.isSneaking() && DataHelper.getEVEFromStack(stack).getSaturation() > 0;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return getShowState(stack) ? DataHelper.getEVEFromStack(stack).getColor() : super.getRGBDurabilityForDisplay(stack);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return getShowState(stack) ? 1f - DataHelper.getEVEFromStack(stack).getSaturation() : super.getDurabilityForDisplay(stack);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return getShowState(stack) || super.showDurabilityBar(stack);
    }
}
