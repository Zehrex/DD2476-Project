1
https://raw.githubusercontent.com/santiago9739/Ingenieria-del-software-2/master/src/main/java/co/unicauca/tallerpolimorfismo/modelo/ViajeFamiliar.java
package co.unicauca.tallerpolimorfismo.modelo;

import java.util.Date;

public class ViajeFamiliar extends Viaje{
    //<editor-fold defaultstate="collapsed" desc="ATRIBUTOS">
    /**
    * Cantidad de integrantes de la familia
    */
    private int familia;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
    public ViajeFamiliar(String origen, String destino, int costo, Date fechaSalida, Date fechaLlegada, int familia) {
        super(origen, destino, costo, fechaSalida, fechaLlegada);
        this.familia = familia;
    }
//</editor-fold>  
    //<editor-fold defaultstate="collapsed" desc="GETTERS">
    public int getFamilia() {
        return familia;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="SETTERS">
    public void setFamilia(int familia) {
        this.familia = familia;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="METODOS">
    @Override
    public String descripcion() {
        return "Viaje para disfrutar con toda tu familia";
    }
    
    @Override
    public String cualquierMetodo2() {
        return "Método implementado en la clase hija viaje familiar";
    }
//</editor-fold>
}

