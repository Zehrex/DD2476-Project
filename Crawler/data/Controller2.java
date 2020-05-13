2
https://raw.githubusercontent.com/radudioane99/Shape-Storm/master/Controller2.java
package game;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class Controller2 extends Thread {
    private List<Enemy2> enemies2;
    public static boolean collision2;
    private Character character;

    public Controller2(List<Enemy2> enemies, Character charac) {
        this.enemies2 = enemies;
        this.character = charac;
    }


    public void run() {
        Random rand = new Random();

        //Set enemies speed (start)
        enemies2.forEach(e -> {
            e.speed = 10 + rand.nextInt(30);
        });


        while (!collision2) {
            enemies2.forEach(e -> {
                e.setLocation(e.getX()+ e.speed, e.getY() );

                //Reuse enemies + change speed
                if (e.getX() >= Utils.WIN_SIZE) {
                    e.setLocation(0, rand.nextInt(Utils.WIN_SIZE - Utils.ENEMEY2_SIZE));
                    e.speed = 10 + rand.nextInt(40);
                }

                //Check collision
                if (e.getX() < character.getX() + Utils.CHARACTER_SIZE &&
                        e.getX() + Utils.ENEMEY2_SIZE > character.getX() &&
                        e.getY() < character.getY() + Utils.CHARACTER_SIZE &&
                        e.getY() + Utils.ENEMEY2_SIZE > character.getY()) {
                    //Shut this thread
                    collision2 = true;

                    //Shut the other thread too
                    Controller.collision=true;
                    //Stop the timer
                    MainWindow.end= System.currentTimeMillis();
                    new Final();
                }
            });

            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }

        }
    }


}


