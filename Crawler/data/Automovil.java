1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/S/Automovil.java
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Solid.S;

/**
 *
 * @author eduaralexis
 */
public class Automovil {

    private String marca;

    public Automovil(String marca){
        this.marca = marca;
    }
    
    public String getMarcaAutomovil() {
        return marca;
    }
    
    //Código de base de datos
    /*void guardarAutomovilDB(Automovil automovil) {
        System.out.println("Guardando automovil");
    }*/
}
