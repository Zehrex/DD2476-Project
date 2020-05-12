2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/Item/ItemMatrix/GuiMatrix.java
package com.naive.phase.Item.ItemMatrix;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiMatrix extends GuiContainer {
    private static final ResourceLocation MATRIX_GUI_TEXTURE = new ResourceLocation("phase:textures/gui/matrix.png");
    private final int rows;

    public GuiMatrix(EntityPlayer player, InventoryMatrix matrix) {
        super(new ContainerMatrix(player, matrix));
        this.rows = matrix.getSlots() / 4;
        this.ySize = 114 + this.rows * 18;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(MATRIX_GUI_TEXTURE);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.rows * 18 + 35);
        this.drawTexturedModalRect(i, j + this.rows * 18 + 17, 0, 89, this.xSize, 116);
    }
}
