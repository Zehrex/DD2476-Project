1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/O/Renault_O.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solid.O;

/**
 *
 * @author eduaralexis
 */
public class Renault_O extends AbsAutomovil_O {

    public Renault_O() {
        super("Renault");
    }

    @Override
    public int precioMedioAutomovil() {
        return 7800000;
    }

    public int numAsientos() {
        return 5;
    }
}
