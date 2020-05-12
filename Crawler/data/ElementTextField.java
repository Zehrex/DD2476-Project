2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/GUI/Elements/ElementTextField.java
package com.naive.phase.GUI.Elements;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.GUI.Interfaces.IGUICallback;
import com.naive.phase.GUI.Interfaces.IGUIElement;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class ElementTextField extends GuiTextField implements IGUIElement {
    protected IGUICallback callback;

    public ElementTextField(FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height) {
        super(-1, fontrendererObj, x, y, par5Width, par6Height);
    }

    @Override
    public void setGUI(IGUICallback back) {
        callback = back;
    }

    @Override
    public void render(int x, int y, float partialTicks) {
        drawTextBox();
    }

    @Override
    public void renderForeground(int x, int y, float partialTicks) {

    }

    @Override
    public Pairs<Range, Range> getClickAwareRange() {
        return new Pairs<>(new Range(x, x + width), new Range(y, y + width));
    }

    @Override
    public void onClicked(int mouseX, int mouseY, int mouseButton) {
        mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onClickedOutside(int mouseX, int mouseY, int mouseButton) {
        onClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean onKeyInput(char key, int keyCode) {
        if (textboxKeyTyped(key, keyCode)) {
            callback.update(this);
            return true;
        }
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public int getID() {
        return 1;
    }

    public String getValue() {
        return getText();
    }
}
