1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/L/Renault_L.java
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
public class Renault_L extends AbsAutomovil_L {

    public Renault_L() {
        super("Renault");
    }

    @Override
    public int precioMedioAutomovil() {
        return 7800000;
    }

    @Override
    public int numAsientos() {
        return 5;
    }

}
