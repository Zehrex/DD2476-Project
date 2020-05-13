2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemColliculus/ItemColliculus.java
package com.naive.phase.Item.ItemColliculus;

import com.naive.phase.Auxiliary.Helper.DataHelper;
import com.naive.phase.Auxiliary.Helper.NBTHelper;
import com.naive.phase.Auxiliary.Register.Registry;
import com.naive.phase.Base.Item.IItemColorable;
import com.naive.phase.Base.Item.PhaseArmorBase;
import com.naive.phase.GUI.GUILib;
import com.naive.phase.Item.ItemBattery.ItemBattery;
import com.naive.phase.Item.ItemMatrix.IUpgrade;
import com.naive.phase.Item.ItemMatrix.ItemMatrix;
import com.naive.phase.Item.ItemUpgrades.ItemBrightnessUpgrade;
import com.naive.phase.Item.ItemUpgrades.ItemRGBUpgrade;
import com.naive.phase.Phase;
import com.naive.phase.Render.Mistuned.MistuneRenderManager;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemColliculus extends PhaseArmorBase implements IItemColorable {

    private static final String TAG_ITEMS = "InvItems";
    private static final String TAG_COLOR = "color";

    @Registry.ItemInst
    public static ItemColliculus itemInst;

    private final ModelBiped colliculusModel = new ModelColliculus();

    public ItemColliculus() {
        super("colliculus", ArmorMaterial.IRON, 0, EntityEquipmentSlot.HEAD);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        IItemHandler args = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if (args == null)
            return;
        ItemStack battery = args.getStackInSlot(0);
        ItemStack lensLeft = args.getStackInSlot(1);
        ItemStack lensRight = args.getStackInSlot(2);
        ItemStack matrix = args.getStackInSlot(3);

        if (world.isRemote) {
            //Get attribute modifiers

            float brightnessMultiplier = 1.f;
            brightnessMultiplier += DataHelper.getUpgradesMatching(matrix, ItemBrightnessUpgrade.class).size() * 0.5f;

            boolean hasRGB = DataHelper.hasUpgrade(matrix, ItemRGBUpgrade.class);

            //Enables the colliculus
            MistuneRenderManager.setShouldEffect(true);

            //Disable sight base on your lens
            MistuneRenderManager.setShowLeft(lensLeft.getItem() instanceof ItemLens);
            MistuneRenderManager.setShowRight(lensRight.getItem() instanceof ItemLens);

            //Blend colors

            //Change color depending on the matrix attributes
            MistuneRenderManager.setBrightnessMultiplier(brightnessMultiplier);

            //Turn on decoloring
            MistuneRenderManager.setShouldDecolor(!hasRGB);

            //Set some visual effects
            MistuneRenderManager.setScalinesOpt(1.0f);
        } else {
            IItemHandler inv = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            if (world.getWorldTime() % 20 == 0)
                if (inv == null || inv.getStackInSlot(3).isEmpty() || (inv.getStackInSlot(1).isEmpty() && inv.getStackInSlot(2).isEmpty())) {
                    player.addItemStackToInventory(itemStack.copy());
                    itemStack.setCount(0);
                }
        }
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return colliculusModel;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new ColliculusInventory();
    }

    @Override
    public void setColor(ItemStack stack, float h, float s, float b) {
        NBTTagCompound color = new NBTTagCompound();
        color.setFloat("h", h);
        color.setFloat("s", s);
        color.setFloat("b", b);

        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setTag(TAG_COLOR, color);
    }

    private static class ColliculusInventory implements ICapabilitySerializable<NBTBase> {
        private final IItemHandler inv = new ItemStackHandler(4) {
            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {

                switch (slot) {
                    case 0:
                        if (!(stack.getItem() instanceof ItemBattery) || !inv.getStackInSlot(0).isEmpty())
                            return stack;
                        else break;
                    case 1:
                        if (!(stack.getItem() instanceof ItemLens) || !inv.getStackInSlot(1).isEmpty())
                            return stack;
                        else break;
                    case 2:
                        if (!(stack.getItem() instanceof ItemLens) || !inv.getStackInSlot(2).isEmpty())
                            return stack;
                        else break;
                    case 3:
                        if (!(stack.getItem() instanceof ItemMatrix) || !inv.getStackInSlot(3).isEmpty())
                            return stack;
                        else break;
                }

                if (!simulate) {
                    this.stacks.set(slot, stack);
                    onContentsChanged(slot);
                }

                return ItemStack.EMPTY;
            }
        };

        @Override
        public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY;
        }

        @Nullable
        @Override
        public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(inv) : null;
        }

        @Override
        public NBTBase serializeNBT() {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(inv, null);
        }

        @Override
        public void deserializeNBT(NBTBase nbt) {
            CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(inv, null, nbt);
        }
    }

    @Override
    public ActionResult<ItemStack> onShiftRightClick(World world, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.openGui(Phase.inst, GUILib.GUI_COLLICULUS, world, handIn == EnumHand.OFF_HAND ? 1 : 0, 0, 0);
        return super.onShiftRightClick(world, playerIn, handIn);
    }

    @Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack) {
        NBTTagCompound tag = super.getNBTShareTag(stack);
        if (tag == null)
            tag = new NBTTagCompound();

        IItemHandler iItemHandler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if (iItemHandler != null)
            tag.setTag(TAG_ITEMS, CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, iItemHandler, null));
        return tag;
    }

    @Override
    public void readNBTShareTag(ItemStack stack, @Nullable NBTTagCompound nbt) {
        super.readNBTShareTag(stack, nbt);
        if (nbt == null)
            return;
        NBTBase tag = nbt.getTag(TAG_ITEMS);
        IItemHandler iItemHandler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, iItemHandler, null, tag);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        // Validate before you wear it, or you will be completely blind.
        ItemStack stack = playerIn.getHeldItem(handIn);
        IItemHandler inv = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        if (!playerIn.isSneaking() && (inv == null || inv.getStackInSlot(3).isEmpty() || (inv.getStackInSlot(1).isEmpty() && inv.getStackInSlot(2).isEmpty()))) {

            if (worldIn.isRemote)
                playerIn.sendMessage(new TextComponentString(I18n.format("info.item.colliculus.invalid")));
            return ActionResult.newResult(EnumActionResult.FAIL, stack);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
