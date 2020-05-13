2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemColliculus/GuiColliculus.java
package com.naive.phase.Item.ItemColliculus;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

public class GuiColliculus extends GuiContainer {

    private static final ResourceLocation COLLICULUS_GUI_TEXTURE = new ResourceLocation("phase:textures/gui/colliculus.png");

    public GuiColliculus(EntityPlayer player, InventoryColliculus colliculus) {
        super(new ContainerColliculus(player, colliculus));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1, 1, 1, 1);
        mc.getTextureManager().bindTexture(COLLICULUS_GUI_TEXTURE);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        for (int i = 0; i < 7; ++i) {
            Slot slot = inventorySlots.inventorySlots.get(i);
            if (slot.getHasStack() && slot.getSlotStackLimit() == 1)
                drawTexturedModalRect(guiLeft + slot.xPos, guiTop + slot.yPos, 200, 0, 16, 16);
        }
    }
}
