1
https://raw.githubusercontent.com/EduarAlexis/SOLID/master/src/main/java/Solid/D/AccesoADatos.java
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
public class AccesoADatos {
    private IConexion conexion;
 
    public AccesoADatos(IConexion conexion){
        this.conexion = conexion;
    }
 
    public Dato getDatos(){
        return conexion.getDatos();
    }

}
