2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemColorPicker/ItemColorPicker.java
package com.naive.phase.Item.ItemColorPicker;

import com.naive.phase.Auxiliary.Helper.NBTHelper;
import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Item.PhaseItemBase;
import com.naive.phase.GUI.GUILib;
import com.naive.phase.Phase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemColorPicker extends PhaseItemBase {
    private static final String TAG_COLOR = "color";

    @Registry.ItemInst
    public static ItemColorPicker itemInst;

    public ItemColorPicker() {
        super("colorpicker");
    }

    public static Vec3d getColor(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null) {
            NBTTagCompound color = tag.getCompoundTag(TAG_COLOR);
            return new Vec3d(
                    NBTHelper.getOrDefault(color, "h", 1f),
                    NBTHelper.getOrDefault(color, "s", 1f),
                    NBTHelper.getOrDefault(color, "b", 1f)
            );
        }
        return new Vec3d(1f, 1f, 1f);
    }

    public static void setColor(ItemStack stack, float h, float s, float b) {
        NBTTagCompound tag = new NBTTagCompound();
        tag.setFloat("h", h);
        tag.setFloat("s", s);
        tag.setFloat("b", b);

        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());
        NBTTagCompound stackTag = stack.getTagCompound();
        stackTag.setTag(TAG_COLOR, tag);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.openGui(Phase.inst, GUILib.GUI_PICKER, worldIn, handIn == EnumHand.OFF_HAND ? 1 : 0, 0, 0);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
