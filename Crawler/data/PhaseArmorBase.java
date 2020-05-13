2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Base/Item/PhaseArmorBase.java
package com.naive.phase.Base.Item;

import com.naive.phase.Auxiliary.Helper.StringHelper;
import com.naive.phase.Phase;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class PhaseArmorBase extends ItemArmor {
    private final String registryName;

    public PhaseArmorBase(String registryName, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.registryName = registryName;
        this.setRegistryName(StringHelper.getPrefixed(registryName));
        this.setUnlocalizedName(Phase.MODID + "." + registryName);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "phase:textures/models/" + registryName + ".png";
    }

    protected String getInformation(ItemStack stack, World world) {
        return I18n.format("info.item." + registryName);
    }

    protected String getInformationBrief(ItemStack stack, World world) {
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
                tooltip.add(I18n.format("phase.help.shiftDown"));
            }
        }
    }

    public ActionResult<ItemStack> onShiftRightClick(World world, EntityPlayer playerIn, EnumHand handIn) {
        return ActionResult.newResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.isSneaking())
            return onShiftRightClick(worldIn, playerIn, handIn);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
