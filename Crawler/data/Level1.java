1
https://raw.githubusercontent.com/jesus-antulio/Tankzors-Fangame/master/Level1.java
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    public Level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(900, 600, 1);
        prepare();
    }
    
    public void prepare(){
        int x=0, y=120;
        
        Pared_v pared_v1 = new Pared_v();
        Pared_v pared_v2 = new Pared_v();
        addObject(pared_v1,25,300);
        addObject(pared_v2,(900-25),300);
        Pared_h pared_h1 = new Pared_h();
        Pared_h pared_h2 = new Pared_h();
        addObject(pared_h1,450,15);
        addObject(pared_h2,450,(600-15));
        
        for (int i=0; i<5; i++){
            for (int j=0; j<6; j++){
                Pared pared = new Pared();
                addObject(pared, (x+=130), y);
            }
            
            y=y+120;
            x=0;
        }
        
        Player player = new Player(pared_v1, pared_v2, pared_h1, pared_h2);
        addObject(player,105,530);
        player.gira(90);
    }
    
}
