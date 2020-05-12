2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Render/ItemColorManager.java
package com.naive.phase.Render;

import com.naive.phase.Auxiliary.Helper.NBTHelper;
import com.naive.phase.Auxiliary.Magic.EVE.CapabilityEVE;
import com.naive.phase.Auxiliary.Magic.EVE.Interface.IEVEStorageItem;
import com.naive.phase.Item.ItemBattery.ItemBattery;
import com.naive.phase.Item.ItemBattery.ItemBatteryAdvanced;
import com.naive.phase.Item.ItemPrism.ItemPrism;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;

import java.awt.*;

public class ItemColorManager {
    public static void register() {
        ItemColors items = Minecraft.getMinecraft().getItemColors();

        items.registerItemColorHandler((s, t) -> Color.HSBtoRGB(
                NBTHelper.getOrDefault(s.getTagCompound(), "hue", 0f),
                1,
                NBTHelper.getOrDefault(s.getTagCompound(), "brightness", 0f)),
                ItemPrism.itemPrism
        );

        items.registerItemColorHandler(((stack, tintIndex) -> {
            if (tintIndex == 0)
                return -1;
            IEVEStorageItem eve = stack.getCapability(CapabilityEVE.EVE_ITEM, null);
            return eve.getEVE().getColor();
        }), ItemBattery.itemInst, ItemBatteryAdvanced.itemInst);
    }
}
