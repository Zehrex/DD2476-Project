1
https://raw.githubusercontent.com/jesus-antulio/Tankzors-Fangame/master/Tanque.java
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tanque here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tanque extends Actor
{
    private int xDirection = 1;
    private int yDirection = -1;
    private int vel = 2;
    
    public void act() 
    {
        startMenu();
    }
    
    public void startMenu(){
        int x = getX();
        int y = getY();
        int i = 0;
      
        /*if(i==0){
            setLocation(x, y+(yDirection*vel));
            
            if(isAtEdge()){
                yDirection *= -1;
                turn(-90);
                i=1;
            }
        }
        
        if(i==1){
            setLocation(x+(xDirection*vel), y);
            
            if(isAtEdge()){
                xDirection *= -1;
                turn(-90);
                i=0;
            }
        }*/
    }
}
