1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/L/Audi_L.java
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
public class Audi_L extends AbsAutomovil_L {

    public Audi_L() {
        super("Audi");
    }

    @Override
    public int precioMedioAutomovil() {
        return 8500000;
    }

    @Override
    public int numAsientos() {
        return 4;
    }
}
