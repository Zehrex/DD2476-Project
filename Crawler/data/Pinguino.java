1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/I/Pinguino.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solid.I;

/**
 *
 * @author eduaralexis
 */
public class Pinguino implements IAve, IAveNadadora{

    @Override
    public void comer() {
        System.out.println("Comiendo");
    }

    @Override
    public void nadar() {
        System.out.println("Pingüino Nadando");
    }
    
    /*@Override
    public void volar() {
        //El pingüino no veuela
        System.out.println("Volando");
    }*/
}
