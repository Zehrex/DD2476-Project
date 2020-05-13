1
https://raw.githubusercontent.com/santiago9739/Ingenieria-del-software-2/master/src/main/java/co/unicauca/tallerpolimorfismo/modelo/ViajeTodoIncluido.java
package co.unicauca.tallerpolimorfismo.modelo;

import java.util.Date;

public class ViajeTodoIncluido extends Viaje{
    //<editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
    @Override
    public String descripcion() {
        return "Disfruta tu viaje todo incluido";
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="METODOS">
    public ViajeTodoIncluido(String origen, String destino, int costo, Date fechaSalida, Date fechaLlegada) {
        super(origen, destino, costo, fechaSalida, fechaLlegada);
    }
//</editor-fold>
    //No se sobreescribe cualquierMetodo2() 
}
