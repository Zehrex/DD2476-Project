2
https://raw.githubusercontent.com/Prunoideae/PhasePotion/master/src/main/java/com/naive/phase/GUI/Interfaces/IGUIElement.java
package com.naive.phase.GUI.Interfaces;

import com.naive.phase.Auxiliary.Instantiable.Data.Math.Pairs;
import com.naive.phase.Auxiliary.Instantiable.Data.Math.Range;

public interface IGUIElement {
    void setGUI(IGUICallback back);

    void render(int x, int y, float partialTicks);

    void renderForeground(int x, int y, float partialTicks);

    Pairs<Range, Range> getClickAwareRange();

    void onClicked(int mouseX, int mouseY, int mouseButton);

    void onClickedOutside(int mouseX, int mouseY, int mouseButton);

    boolean onKeyInput(char key, int keyCode);

    void update();

    int getID();
}
