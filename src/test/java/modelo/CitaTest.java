package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * La clase CitaTest contiene pruebas unitarias para la clase Cita.
 *
 * @author Nuria Vázquez 
 */
public class CitaTest {

    public CitaTest() {
    }

    /**
     * Prueba unitaria del constructor de la clase Cita.
     */
    @Test
    public void tesCitaConstructor() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Cita Constructor");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println();

        Cita cita = new Cita();

        assertNotNull(cita);
    }

    /**
     * Prueba unitaria para el método getPrecio de la clase cita. Se verifica
     * que el valor devuelto por el método coincida con el valor establecido
     * mediante el método setPrecio.
     */
    @Test
    public void getPrecio() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba getPrecio Cita");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println();

        Cita cita = new Cita();

        cita.setPrecio(new BigDecimal(100.00));
        BigDecimal result = cita.getPrecio();

        assertEquals(0, result.compareTo(new BigDecimal(100.00)));
    }

    /**
     *
     * Prueba unitaria para verificar la creación correcta de un objeto Cita.
     *
     * Se comprueba que todos los atributos se inicializan correctamente.
     */
    @Test
    public void testCita() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-");
        System.out.println(" Prueba Cita");
        System.out.println("*-*-*-*-*-*-");
        System.out.println();

        Cita cita = new Cita();
        cita.setNombreRecepcion("Ana");
        cita.setNombreVeterinario("Juan");
        cita.setApellidoUnoVeterinario("Pérez");
        cita.setApellidoDosVeterinario("García");
        cita.setNombreMascota("Bobby");
        cita.setNombreCliente("Emilio");
        cita.setDniCliente("12345678A");
        cita.setApellidoUnoCliente("Martín");
        cita.setApellidoDosCliente("Aguilar");
        cita.setFechaCita(java.sql.Date.valueOf(LocalDate.parse("12/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        cita.setTipoConsulta("Vacuna");
        cita.setDiagnostico("Ninguno");
        cita.setTratamiento("Ninguno");
        cita.setMedicacion("Ninguno");
        cita.setPrecio(new BigDecimal("80.00"));
        cita.setHoraCita(java.sql.Time.valueOf(LocalTime.parse("15:30:00")));

        assertNotNull(cita);

    }

}
