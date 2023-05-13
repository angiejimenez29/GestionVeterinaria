package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * La clase RecepcionistaTest contiene pruebas unitarias para la clase
 * Recepcionista.
 *
 * @author Nuria Vázquez 
 */
public class RecepcionistaTest {

    public RecepcionistaTest() {
    }

    /**
     * Prueba unitaria del constructor de la clase Recepcionista.
     */
    @Test
    public void tesRecepcionistaConstructor() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba Recepcionista Constructor");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Recepcionista recepcionista = new Recepcionista();

        assertNotNull(recepcionista);
    }

    /**
     * Prueba unitaria para el método getNombre de la clase Recepcionista. Se
     * verifica que el valor devuelto por el método coincida con el valor
     * establecido mediante el método setNombre.
     */
    @Test
    public void testgetNombreRecepcionista() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba getNombre Recepcionista");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Recepcionista recepcionista = new Recepcionista();

        recepcionista.setNombre("Carmen");
        String result = recepcionista.getNombre();

        assertEquals("Carmen", result);
    }

    /**
     *
     * Prueba unitaria para verificar la creación correcta de un objeto
     * Recepcionista.
     *
     * Se comprueba que todos los atributos se inicializan correctamente.
     */
    @Test
    public void testRecepcionista() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba Recepcionista ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Recepcionista recepcionista = new Recepcionista();
        recepcionista.setNombre("Juan");
        recepcionista.setApellidoUno("Pérez");
        recepcionista.setApellidoDos("García");
        recepcionista.setDni("12345678A");
        recepcionista.setFechaNac(java.sql.Date.valueOf(LocalDate.parse("12/05/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        recepcionista.setDireccion("Calle Velero, 123");
        recepcionista.setCp(28001);
        recepcionista.setTelefono(123456789);
        recepcionista.setEmail("juan.perez@gmail.com");
        recepcionista.setTurno(Recepcionista.Turno.M);

        assertNotNull(recepcionista);

    }

}
