2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/GUI/Elements/ElementSlidebar.java
package com.naive.phase.GUI.Elements;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;
import com.naive.phase.GUI.Interfaces.IGUICallback;
import com.naive.phase.GUI.Interfaces.IGUIElement;

public class ElementSlidebar extends GuiElementBase implements IGUIElement {
    private final float min;
    private final float max;
    private float percent;
    private IGUICallback callback;


    public ElementSlidebar(int x, int y, int width, int height, float min, float max) {
        super(-1, x, y, width, height);
        this.min = min;
        this.max = max;
    }


    @Override
    public void setGUI(IGUICallback back) {
        this.callback = back;
    }

    @Override
    public void render(int x, int y, float partialTicks) {
        drawRect(this.x, this.y, this.x + width, this.y + height, 0xFFFFFFFF);
        drawRect((int) (this.x + width * percent), this.y, (int) (this.x + width * percent + 4), this.y + height, 0xFF000000);
    }

    @Override
    public void renderForeground(int x, int y, float partialTicks) {

    }

    @Override
    public void onClicked(int mouseX, int mouseY, int mouseButton) {
        int offset = mouseX - x;
        this.percent = offset / (float) this.width;
    }

    @Override
    public void onClickedOutside(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public boolean onKeyInput(char key, int keyCode) {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public int getID() {
        return 0;
    }

    public float getValue() {
        return (max - min) * percent + min;
    }

    public void setValue(float value) {
        this.percent = (value - min) / (max - min);
    }
}
