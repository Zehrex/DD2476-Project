1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/I/Loro.java
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
public class Loro implements IAve, IAveVoladora{

    @Override
    public void volar() {
        System.out.println("Loro Volando");
    }

    @Override
    public void comer() {
        System.out.println("Comiendo");
    }

    /*@Override
    public void nadar() {
        //El loro no nada
        System.out.println("Nadando");
    }*/
}
