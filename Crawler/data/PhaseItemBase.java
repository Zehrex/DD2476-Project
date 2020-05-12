2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Item/PhaseItemBase.java
package com.naive.phase.Base.Item;

import com.naive.phase.Phase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PhaseItemBase extends Item {
    private String registryName;

    public PhaseItemBase(String registryName) {
        this.registryName = registryName;
        setRegistryName(registryName);
        setUnlocalizedName(Phase.MODID + "." + registryName);
    }

    protected String getInformation(ItemStack stack, World worldIn) {
        return I18n.format("info.item." + registryName);
    }

    ;

    protected String getInformationBrief(ItemStack stack, World worldIn) {
        return I18n.format("info.item.brief." + registryName);
    }

    @Override
    public final void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (GuiScreen.isShiftKeyDown()) {
            if (!getInformation(stack, worldIn).isEmpty()) {
                tooltip.add(getInformation(stack, worldIn));
            }
        } else {
            if (!getInformationBrief(stack, worldIn).isEmpty()) {
                tooltip.add(getInformationBrief(stack, worldIn));
            }
            if (!getInformation(stack, worldIn).isEmpty())
                tooltip.add(I18n.format("phase.help.shiftDown"));
        }
    }
}
