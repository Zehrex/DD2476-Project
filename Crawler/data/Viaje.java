1
https://raw.githubusercontent.com/santiago9739/Ingenieria-del-software-2/master/src/main/java/co/unicauca/tallerpolimorfismo/modelo/Viaje.java
package co.unicauca.tallerpolimorfismo.modelo;

import java.util.Date;

public abstract class Viaje {
    //<editor-fold defaultstate="collapsed" desc="ATRIBUTOS">
    protected String origen;
    protected String destino;
    protected int costo;
    protected Date fechaSalida;
    protected Date fechaLlegada;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
    public Viaje(String origen, String destino, int costo, Date fechaSalida, Date fechaLlegada){
        this.origen = origen;
        this.destino = destino;
        this.costo = costo;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="GETTERS">
    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }
    public int getCosto() {
        return costo;
    }
    public Date getFechaSalida() {
        return fechaSalida;
    }
    public Date getFechaLlegada() {
        return fechaLlegada;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="SETTERS">
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="METODOS">
    public abstract String descripcion();
    
    public String cualquierMetodo(){
        return "Cualquier método implementado en la clase base";
    }
    public String cualquierMetodo2(){
        return "Cualquier método2 implementado en la clase base";
    }
//</editor-fold>
}

