1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/L/AbsAutomovil_L.java
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
public abstract class AbsAutomovil_L {

    private String marca;

    public AbsAutomovil_L(String marca) {
        this.marca = marca;
    }

    public String getMarcaAutomovil() {
        return marca;
    }

    public abstract int precioMedioAutomovil();
    public abstract int numAsientos();
}
