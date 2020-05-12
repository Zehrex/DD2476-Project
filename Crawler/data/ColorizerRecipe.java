2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Recipes/ColorizerRecipe.java
package com.naive.phase.Recipes;

import com.naive.phase.Base.Item.IItemColorable;
import com.naive.phase.Item.ItemColorPicker.ItemColorPicker;
import com.naive.phase.Phase;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ColorizerRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    public ColorizerRecipe() {
        setRegistryName(new ResourceLocation(Phase.MODID, "colorize"));
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        ItemStack stackColorable = ItemStack.EMPTY;
        ItemStack stackPicker = ItemStack.EMPTY;

        if (inv.getWidth() == 3 && inv.getHeight() == 3) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    ItemStack stack = inv.getStackInRowAndColumn(i, j);
                    if (stack.getItem() instanceof IItemColorable || stack.getItem() instanceof ItemColorPicker || stack.isEmpty()) {
                        if (stack.getItem() instanceof IItemColorable && stackColorable.isEmpty()) {
                            stackColorable = stack;
                        } else if (stack.getItem() instanceof ItemColorPicker && stackPicker.isEmpty()) {
                            stackPicker = stack;
                        } else if (stack.isEmpty()) {
                        } else return false;
                    } else return false;
                }
        }
        return !stackColorable.isEmpty() && !stackPicker.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        ItemStack stackColorable = ItemStack.EMPTY;
        ItemStack stackPicker = ItemStack.EMPTY;

        if (inv.getWidth() == 3 && inv.getHeight() == 3) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++) {
                    ItemStack stack = inv.getStackInRowAndColumn(i, j);
                    if (stack.getItem() instanceof IItemColorable || stack.getItem() instanceof ItemColorPicker || stack.isEmpty()) {
                        if (stack.getItem() instanceof IItemColorable && stackColorable.isEmpty()) {
                            stackColorable = stack;
                        } else if (stack.getItem() instanceof ItemColorPicker && stackPicker.isEmpty()) {
                            stackPicker = stack;
                        } else if (stack.isEmpty()) {
                        } else return ItemStack.EMPTY;
                    } else return ItemStack.EMPTY;
                }
        }

        ItemStack copy = stackColorable.copy();
        IItemColorable handler = (IItemColorable) stackColorable.getItem();
        Vec3d color = ItemColorPicker.getColor(stackPicker);
        handler.setColor(copy, (float) color.x, (float) color.y, (float) color.z);
        return copy;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width == 3 && height == 3;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> result = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < result.size(); ++i)
            if (inv.getStackInSlot(i).getItem() instanceof ItemColorPicker)
                result.set(i, inv.getStackInSlot(i));
        return result;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }
}
