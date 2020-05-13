2
https://raw.githubusercontent.com/radudioane99/Shape-Storm/master/MainWindow.java
package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class MainWindow extends JFrame {

    public Character character;
    static Container frontend;
    //For timer
    public static long start;
    public static long end;
    public static String playername;

    MainWindow(Character character, List<Enemy> enemies, List<Enemy2> enemies2) {
        super("Shape Storm");
        this.setLayout(null);
        this.frontend = getContentPane();

        Color myBack2 = new Color(31, 30, 36);
        this.frontend.setBackground(myBack2);

        this.character = character;
        this.character.setLocation(240, 420);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                char key = e.getKeyChar();
                switch (key) {
                    case 'a': {
                        if (character.getX() >= Utils.MOVE_SIZE)
                            character.setLocation(character.getX() - Utils.MOVE_SIZE, character.getY());
                        break;
                    }
                    case 'd': {
                        if (character.getX() <= Utils.WIN_SIZE - 30)
                            character.setLocation(character.getX() + Utils.MOVE_SIZE, character.getY());
                        break;
                    }
                    case 's': {
                        if (character.getY() <= Utils.WIN_SIZE - 55)
                            character.setLocation(character.getX(), character.getY() + Utils.MOVE_SIZE);
                        break;
                    }
                    case 'w': {
                        if (character.getY() >= Utils.MOVE_SIZE)
                            character.setLocation(character.getX(), character.getY() - Utils.MOVE_SIZE);
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setSize(Utils.WIN_SIZE, Utils.WIN_SIZE);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(this.character);

        //for enemyes start position
        int distance = Utils.WIN_SIZE / enemies.size();
        int distance2 = Utils.WIN_SIZE / enemies2.size();
        int[] c = {0};
        int[] d = {0};
        enemies.forEach(e -> {
            e.setLocation(10 + c[0] * distance, 0);
            MainWindow.this.add(e);
            c[0] += 1;
        });

        enemies2.forEach(e -> {
            e.setLocation(0, 0 + d[0] * distance2);
            MainWindow.this.add(e);
            d[0] += 1;
        });


        setVisible(true);
    }
}
