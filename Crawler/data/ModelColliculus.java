2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemColliculus/ModelColliculus.java
package com.naive.phase.Item.ItemColliculus;

import com.naive.phase.Auxiliary.Helper.NBTHelper;
import com.naive.phase.Base.Render.ModelArmor;
import com.naive.phase.Base.Render.RecolorableModels;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.awt.*;

public class ModelColliculus extends ModelArmor {
    private final RecolorableModels framePart;
    private final ModelRenderer fix1;
    private final ModelRenderer fix2;
    private final ModelRenderer fix3;
    private final ModelRenderer fix4;

    private final RecolorableModels rightLight;
    private final RecolorableModels leftLight2;
    private final RecolorableModels leftLight1;
    private final RecolorableModels matrix;
    private final RecolorableModels rightGlassPart;
    private final RecolorableModels leftGlassPart;
    private final ModelRenderer helmAnchor;


    public ModelColliculus() {
        super(EntityEquipmentSlot.HEAD);

        textureWidth = 64;
        textureHeight = 64;

        framePart = new RecolorableModels(this);
        framePart.setRotationPoint(0.0F, 24.0F, 0.0F);
        framePart.cubeList.add(new ModelBox(framePart, 0, 0, -5.0F, -28.5F, -4.21F, 10, 2, 9, 0.0F, false));
        framePart.cubeList.add(new ModelBox(framePart, 12, 15, -0.02F, -29.5F, -5.01F, 5, 1, 1, 0.0F, false));
        framePart.cubeList.add(new ModelBox(framePart, 14, 19, -0.51F, -29.0F, -5.061F, 1, 2, 1, 0.0F, false));
        framePart.cubeList.add(new ModelBox(framePart, 10, 17, 1.01F, -26.5F, -5.01F, 4, 1, 1, 0.0F, false));
        framePart.cubeList.add(new ModelBox(framePart, 0, 15, -4.98F, -29.5F, -5.01F, 5, 1, 1, 0.0F, false));
        framePart.cubeList.add(new ModelBox(framePart, 0, 17, -5.01F, -26.5F, -5.01F, 4, 1, 1, 0.0F, false));
        framePart.cubeList.add(new ModelBox(framePart, 10, 19, -4.99F, -29.25F, -5.02F, 1, 3, 1, 0.0F, false));
        framePart.cubeList.add(new ModelBox(framePart, 6, 19, 3.99F, -29.25F, -5.02F, 1, 3, 1, 0.0F, false));

        fix2 = new ModelRenderer(this);
        fix2.setRotationPoint(-1.01F, -26.0F, -4.51F);
        framePart.addChild(fix2);
        setRotationAngle(fix2, 0.0F, 0.0F, -0.7854F);
        fix2.cubeList.add(new ModelBox(fix2, 0, 19, -0.3394F, -0.6323F, -0.54F, 2, 1, 1, 0.0F, false));

        fix3 = new ModelRenderer(this);
        fix3.setRotationPoint(0.99F, -26.0F, -4.51F);
        framePart.addChild(fix3);
        setRotationAngle(fix3, 0.0F, 0.0F, 0.7854F);
        fix3.cubeList.add(new ModelBox(fix3, 0, 6, -1.6464F, -0.6464F, -0.548F, 2, 1, 1, 0.0F, false));

        rightLight = new RecolorableModels(this);
        rightLight.setRotationPoint(4.25F, -4.6F, -5.1F);
        rightLight.cubeList.add(new ModelBox(rightLight, 5, 5, -1.0F, -1.0F, 0.07F, 1, 1, 1, 0.0F, false));

        leftLight2 = new RecolorableModels(this);
        leftLight2.setRotationPoint(0.0F, 24.0F, 0.0F);
        leftLight2.cubeList.add(new ModelBox(leftLight2, 5, 1, -3.0F, -29.6F, -5.03F, 1, 1, 1, 0.0F, false));

        leftLight1 = new RecolorableModels(this);
        leftLight1.setRotationPoint(0.0F, 24.0F, 0.0F);
        leftLight1.cubeList.add(new ModelBox(leftLight1, 5, 3, -4.25F, -29.6F, -5.03F, 1, 1, 1, 0.0F, false));

        matrix = new RecolorableModels(this);
        matrix.setRotationPoint(0.0F, 24.0F, 0.0F);
        matrix.cubeList.add(new ModelBox(matrix, 0, 0, -1.0F, -29.4F, -5.1F, 2, 1, 1, 0.0F, false));

        rightGlassPart = new RecolorableModels(this);
        rightGlassPart.setRotationPoint(1.49F, -2.5F, -4.51F);
        rightGlassPart.cubeList.add(new ModelBox(rightGlassPart, 0, 11, -1.0F, -2.5F, -0.53F, 4, 3, 1, 0.0F, false));

        leftGlassPart = new RecolorableModels(this);
        leftGlassPart.setRotationPoint(-3.5F, -1.0F, -9.0F);
        leftGlassPart.cubeList.add(new ModelBox(leftGlassPart, 10, 11, -1.01F, -4.0F, 3.96F, 4, 3, 1, 0.0F, false));

        fix1 = new ModelRenderer(this);
        fix1.setRotationPoint(1.99F, -1.5F, 4.49F);
        leftGlassPart.addChild(fix1);
        setRotationAngle(fix1, 0.0F, 0.0F, -0.7854F);
        fix1.cubeList.add(new ModelBox(fix1, 0, 4, -0.3395F, -0.6323F, -0.55F, 2, 1, 1, 0.0F, false));

        fix4 = new ModelRenderer(this);
        fix4.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightGlassPart.addChild(fix4);
        setRotationAngle(fix4, 0.0F, 0.0F, 0.7854F);
        fix4.cubeList.add(new ModelBox(fix4, 0, 2, -1.6463F, -0.6464F, -0.549F, 2, 1, 1, 0.0F, false));


        helmAnchor = new ModelRenderer(this);
        helmAnchor.addBox(-1.0f, -2.0f, 0.0f, 2, 2, 2);
        helmAnchor.setRotationPoint(0f, 0f, 0f);
        helmAnchor.addChild(framePart);
        helmAnchor.addChild(rightLight);
        helmAnchor.addChild(leftLight1);
        helmAnchor.addChild(leftLight2);
        helmAnchor.addChild(rightGlassPart);
        helmAnchor.addChild(leftGlassPart);
        helmAnchor.addChild(matrix);

        ;
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        //Try to set models color

        if (entityIn instanceof EntityLivingBase) {

            //Recolor the part
            ItemStack colliculusStack = ((EntityLivingBase) entityIn).getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            NBTTagCompound tag = colliculusStack.getTagCompound();
            NBTTagCompound colorTag = NBTHelper.getSafe(tag, "color");
            Color color = Color.getHSBColor(
                    NBTHelper.getOrDefault(colorTag, "h", 0f),
                    NBTHelper.getOrDefault(colorTag, "s", 0f),
                    NBTHelper.getOrDefault(colorTag, "b", 0f));

            framePart.setColor(color.getRed() / 255.f, color.getGreen() / 255.f, color.getBlue() / 255.f, 1);

            IItemHandler itemCap = colliculusStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            if (itemCap != null) {
                ItemStack battery = itemCap.getStackInSlot(0);
                ItemStack lensLeft = itemCap.getStackInSlot(1);
                ItemStack lensRight = itemCap.getStackInSlot(2);
                ItemStack matrix = itemCap.getStackInSlot(3);

                if (!lensLeft.isEmpty()) {
                    leftGlassPart.showModel = true;
                } else leftGlassPart.showModel = false;

                if (!lensRight.isEmpty()) {
                    rightGlassPart.showModel = true;
                } else rightGlassPart.showModel = false;

                if (!matrix.isEmpty()) {
                    ;
                }
            }
        }
        bipedHeadwear.showModel = false;
        helmAnchor.showModel = true;

        //leftGlassPart.setColor(color.getRed() / 255.f, color.getGreen() / 255.f, color.getBlue() / 255.f, 1);
        bipedHead = helmAnchor;

        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
