2
https://raw.githubusercontent.com/radudioane99/Shape-Storm/master/Enemy2.java
package game;

import javax.swing.*;
import java.awt.*;

public class Enemy2 extends JComponent {
    public int speed;
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Utils.ENEMEY2_SIZE, Utils.ENEMEY2_SIZE);
    }
}

