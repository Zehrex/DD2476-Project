1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/D/Dato.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solid.D;

/**
 *
 * @author eduaralexis
 */
public class Dato {

    private String cadenaConexion;

    public Dato() {
    }
    
    public Dato(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }

    public String getCadenaConexion() {
        return cadenaConexion;
    }

    public void setDato(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }
}
