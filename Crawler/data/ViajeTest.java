1
https://raw.githubusercontent.com/santiago9739/Ingenieria-del-software-2/master/src/test/java/co/unicauca/tallerpolimorfismo/modelo/ViajeTest.java
package co.unicauca.tallerpolimorfismo.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Libardo Pantoja, Ricardo Zambrano
 */
public class ViajeTest {

    public ViajeTest() {
    }

    /**
     * Test de la clase ViajeFamiliar.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testViajeFamiliar() throws ParseException {
        System.out.println("Viaje familiar");
        ViajeFamiliar viaje = new ViajeFamiliar("Popayán", "Bogotá", 1250000, new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2019"), new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2019"), 5);
        assertEquals("Popayán", viaje.getOrigen());
        assertEquals("Bogotá", viaje.getDestino());
        assertEquals(1250000, viaje.getCosto());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("01/05/2019"), viaje.getFechaSalida());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("05/05/2019"), viaje.getFechaLlegada());
        assertEquals(5, viaje.getFamilia());
        assertEquals("Viaje para disfrutar con toda tu familia", viaje.descripcion());
        assertEquals("Cualquier método implementado en la clase base", viaje.cualquierMetodo());
        assertEquals("Método implementado en la clase hija viaje familiar", viaje.cualquierMetodo2());
    }

    /**
     * Test de la clase ViajeIncentivo.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testViajeIncentivo() throws ParseException {
        System.out.println("Viaje incentivo");
        ViajeIncentivo viaje = new ViajeIncentivo("Popayán", "Medellin", 2100000, new SimpleDateFormat("dd/MM/yyyy").parse("03/06/2019"), new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2019"), "Emtel");
        assertEquals("Popayán", viaje.getOrigen());
        assertEquals("Medellin", viaje.getDestino());
        assertEquals(2100000, viaje.getCosto());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/2019"), viaje.getFechaSalida());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("09/06/2019"), viaje.getFechaLlegada());
        assertEquals("Emtel", viaje.getEmpresa());
        assertEquals("Viaje incentivo que te envia la empresa Emtel", viaje.descripcion());
        assertEquals("Cualquier método implementado en la clase base", viaje.cualquierMetodo());
        assertEquals("Método implementado en la clase hija viaje de incentivo", viaje.cualquierMetodo2());
    }

    /**
     * Test de la clase ViajeIndividual.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testViajeIndividual() throws ParseException {
        System.out.println("Viaje individual");
        Viaje viaje = new ViajeIndividual("Popayán", "San Andres", 4250000, new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2019"), new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2019"));
        assertEquals("Popayán", viaje.getOrigen());
        assertEquals("San Andres", viaje.getDestino());
        assertEquals(4250000, viaje.getCosto());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2019"), viaje.getFechaSalida());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2019"), viaje.getFechaLlegada());
        assertEquals("Disfruta tu viaje individual", viaje.descripcion());
        assertEquals("Cualquier método implementado en la clase base", viaje.cualquierMetodo());
        assertEquals("Cualquier método2 implementado en la clase base", viaje.cualquierMetodo2());

    }

    /**
     * Test de la clase ViajeTodoIncluido.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testViajeTodoIncluido() throws ParseException {
        System.out.println("Viaje todo incluido");
        Viaje viaje = new ViajeTodoIncluido("Popayán", "Cartagena", 7350000, new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2019"), new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2019"));
        assertEquals("Popayán", viaje.getOrigen());
        assertEquals("Cartagena", viaje.getDestino());
        assertEquals(7350000, viaje.getCosto());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("01/07/2019"), viaje.getFechaSalida());
        assertEquals(new SimpleDateFormat("dd/MM/yyyy").parse("12/07/2019"), viaje.getFechaLlegada());
        assertEquals("Disfruta tu viaje todo incluido", viaje.descripcion());
        assertEquals("Cualquier método implementado en la clase base", viaje.cualquierMetodo());
        assertEquals("Cualquier método2 implementado en la clase base", viaje.cualquierMetodo2());
    }
}




