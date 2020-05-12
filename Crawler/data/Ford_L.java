1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/L/Ford_L.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solid.L;

/**
 *
 * @author eduaralexis
 */
public class Ford_L extends AbsAutomovil_L {

    public Ford_L() {
        super("Ford");
    }

    @Override
    public int precioMedioAutomovil() {
        return 10000000;
    }

    @Override
    public int numAsientos() {
        return 5;
    }
}
