1
https://raw.githubusercontent.com/jesus-antulio/Tankzors-Fangame/master/StartMenu.java
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartMenu extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public StartMenu()
    {    
        super(900, 600, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Title title = new Title();
        addObject(title,450,300);
        Tanque tanque = new Tanque();
        addObject(tanque,783,483);
        
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("enter")){
            Greenfoot.setWorld(new Level1());
        }
       
    }
}
