1
https://raw.githubusercontent.com/santiago9739/Ingenieria-del-software-2/master/src/main/java/co/unicauca/tallerpolimorfismo/modelo/ViajeIncentivo.java
package co.unicauca.tallerpolimorfismo.modelo;

import java.util.Date;

public class ViajeIncentivo extends Viaje{
    //<editor-fold defaultstate="collapsed" desc="ATRIBUTOS">
    /**
     * Empresa que patrocina el viaje al empleado
     */
    private String empresa;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
    public ViajeIncentivo(String origen, String destino, int costo, Date fechaSalida, Date fechaLlegada, String empresa) {
        super(origen, destino, costo, fechaSalida, fechaLlegada);
        this.empresa = empresa;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="GETTERS">
    public String getEmpresa() {
        return empresa;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="SETTERS">
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="METODOS">
    @Override
    public String descripcion() {
        return "Viaje incentivo que te envia la empresa " + empresa;
    }
    
    public String cualquierMetodo2() {
        return "Método implementado en la clase hija viaje de incentivo";
    }
//</editor-fold>
}

