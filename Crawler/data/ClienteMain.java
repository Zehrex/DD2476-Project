1
https://raw.githubusercontent.com/santiago9739/Ingenieria-del-software-2/master/src/main/java/co/unicauca/tallerpolimorfismo/cliente/ClienteMain.java
package co.unicauca.tallerpolimorfismo.cliente;

import co.unicauca.tallerpolimorfismo.modelo.ViajeTodoIncluido;
import co.unicauca.tallerpolimorfismo.modelo.Viaje;
import co.unicauca.tallerpolimorfismo.modelo.ViajeIndividual;
import co.unicauca.tallerpolimorfismo.modelo.ViajeFamiliar;
import co.unicauca.tallerpolimorfismo.modelo.ViajeIncentivo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteMain {

    /*
    //Arreglo Polifórmico de viajes
     */
    public static List<Viaje> viajes = new ArrayList();

    public static void main(String[] args) {
        leerViajes();
        mostrarViajes();
    }

    /**
     * Lee viajes en el arreglo polimórfico
     */
    public static void leerViajes() {
        try {
            Viaje viaje1 = new ViajeFamiliar("Popayán", "Bogotá", 1250000, 
                           new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2019"), 
                           new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2019"), 5);
            viajes.add(viaje1);
            Viaje viaje2 = new ViajeIncentivo("Popayán", "Medellin", 2100000, 
                           new SimpleDateFormat("dd/MM/yyyy").parse("03/06/2019"), 
                           new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2019"), "Emtel");
            viajes.add(viaje2);
            Viaje viaje3 = new ViajeIndividual("Popayán", "San Andres", 4250000,
                           new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2019"), 
                           new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2019"));
            viajes.add(viaje3);
            Viaje viaje4 = new ViajeTodoIncluido("Popayán", "Cartagena", 7350000,
                           new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2019"), 
                           new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2019"));
            viajes.add(viaje4);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null,ex);
        }
    }

    /**
     * Recorre e imprime datos del arreglo polimófico
     */
    public static void mostrarViajes() {
        // En este caso vemos que todos los viajes ejecutan el método "descripcion()" de forma diferente 
        // ya que al ser este método abstracto en la clase padre, les forzamos a las clases hijas a que 
        // implementen ese método.
        for (Viaje viaje : viajes) {
            System.out.println("Origen: " + viaje.getOrigen());
            System.out.println("Destino: " + viaje.getDestino());
            System.out.println("Fecha salida: " + viaje.getFechaSalida());
            System.out.println("Fecha llegada: " + viaje.getFechaLlegada());
            System.out.println("Costo: " + viaje.getCosto());
            System.out.println("Descripción: " + viaje.descripcion());
            System.out.println("Cualquier método: " + viaje.cualquierMetodo());
            System.out.println("Cualquier método2: " + viaje.cualquierMetodo2());
            System.out.println("");
        }
    }
}

