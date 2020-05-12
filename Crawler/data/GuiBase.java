2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/GUI/GuiBase.java
package com.naive.phase.GUI;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.GUI.Interfaces.IGUICallback;
import com.naive.phase.GUI.Interfaces.IGUIElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class GuiBase extends GuiScreen implements IGUICallback {

    protected final List<IGUIElement> elements = new ArrayList<>();
    public int guiX, guiY, xSize, ySize;

    @Override
    public void initGui() {
        super.initGui();
        elements.clear();
        guiX = width / 2 - xSize / 2;
        guiY = height / 2 - ySize / 2;
    }

    public void addElement(IGUIElement element) {
        elements.add(element);
        element.setGUI(this);
    }

    public void removeElement(IGUIElement element) {
        elements.remove(element);
    }

    protected abstract ResourceLocation getTexture();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.color(1f, 1f, 1f, 1f);
        if (getTexture() != null) {
            mc.getTextureManager().bindTexture(getTexture());
        }
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (IGUIElement element : elements) {
            element.render(mouseX, mouseY, partialTicks);
            element.renderForeground(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        LinkedList<IGUIElement> l = new LinkedList<>(elements);

        l.forEach(element -> {
            Pairs<Range, Range> bound = element.getClickAwareRange();
            if (bound.getFirst().in(mouseX) && bound.getSecond().in(mouseY))
                element.onClicked(mouseX, mouseY, mouseButton);
            else
                element.onClickedOutside(mouseX, mouseY, mouseButton);
        });
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1)
            super.keyTyped(typedChar, keyCode);
        else
            for (IGUIElement element : elements)
                if (element.onKeyInput(typedChar, keyCode))
                    break;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        elements.forEach(IGUIElement::update);
    }


}
