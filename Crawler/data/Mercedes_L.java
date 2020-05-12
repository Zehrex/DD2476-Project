1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/L/Mercedes_L.java
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
public class Mercedes_L extends AbsAutomovil_L {

    public Mercedes_L() {
        super("Mercedes");
    }

    @Override
    public int precioMedioAutomovil() {
        return 9700000;
    }

    @Override
    public int numAsientos() {
        return 2;
    }
}
