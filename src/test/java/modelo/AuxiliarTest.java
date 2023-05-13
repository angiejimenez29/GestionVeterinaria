package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * La clase AuxiliarTest contiene pruebas unitarias para la clase Auxiliar.
 *
 * @author Nuria Vázquez 
 */
public class AuxiliarTest {

    public AuxiliarTest() {
    }

    /**
     * Prueba unitaria del constructor de la clase Auxiliar.
     */
    @Test
    public void tesAuxiliarConstructor() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba Cliente Auxiliar");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Auxiliar auxiliar = new Auxiliar();

        assertNotNull(auxiliar);
    }

    /**
     * Prueba unitaria para el método getTelefono de la clase Auxiliar. Se
     * verifica que el valor devuelto por el método coincida con el valor
     * establecido mediante el método setTelefono.
     */
    @Test
    public void getTelefono() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba getTelefono Auxiliar");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println();

        Auxiliar auxiliar = new Auxiliar();

        auxiliar.setTelefono(950124578);
        Integer result = auxiliar.getTelefono();

        assertEquals(950124578, result);
    }

    /**
     *
     * Prueba unitaria para verificar la creación correcta de un objeto
     * Auxiliar.
     *
     * Se comprueba que todos los atributos se inicializan correctamente.
     */
    @Test
    public void testAuxiliar() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Auxiliar");
        System.out.println("*-*-*-*-*-*-*-*-");
        System.out.println();

        Auxiliar auxiliar = new Auxiliar();
        auxiliar.setEspecialidad("Cuidados");
        auxiliar.setSalario(new BigDecimal("2000.00"));
        auxiliar.setNombre("Ana");
        auxiliar.setApellidoUno("González");
        auxiliar.setApellidoDos("Pérez");
        auxiliar.setDni("12345678A");
        auxiliar.setFechaNac(java.sql.Date.valueOf(LocalDate.parse("12/05/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        auxiliar.setDireccion("Calle costa del sol, 12");
        auxiliar.setCp(29001);
        auxiliar.setTelefono(950121212);
        auxiliar.setEmail("ana.gonzalez@gmail.com");
        auxiliar.setTurno(Auxiliar.Turno.M);
        assertNotNull(auxiliar);

    }

}
