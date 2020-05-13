1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/O/Mercedes_O.java
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
public class Mercedes_O extends AbsAutomovil_O {

    public Mercedes_O() {
        super("Mercedes");
    }

    @Override
    public int precioMedioAutomovil() {
        return 9700000;
    }
    
    public int numAsientos() {
        return 2;
    }
}
