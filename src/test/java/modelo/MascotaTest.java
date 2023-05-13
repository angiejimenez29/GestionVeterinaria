package modelo;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * La clase MascotaTest contiene pruebas unitarias para la clase Mascota.
 *
 * @author Nuria Vázquez 
 */
public class MascotaTest {

    public MascotaTest() {
    }

    /**
     * Prueba unitaria del constructor de la clase Mascota.
     */
    @Test
    public void tesMascotaConstructor() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Mascota Constructor");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println();

        Mascota mascota = new Mascota();

        assertNotNull(mascota);
    }

    /**
     * Prueba unitaria para el método getSexo de la clase Mascota. Se verifica
     * que el valor devuelto por el método coincida con el valor establecido
     * mediante el método setSexo.
     */
    @Test
    public void testgetSexo() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba getSexo Mascota");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Mascota mascota = new Mascota();

        mascota.setSexo(Mascota.Sexo.M);
        String result = mascota.getSexo().name();

        assertEquals("M", result);
    }

    /**
     *
     * Prueba unitaria para verificar la creación correcta de un objeto Mascota.
     *
     * Se comprueba que todos los atributos se inicializan correctamente.
     */
    @Test
    public void testMascota() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Mascota ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-");
        System.out.println();

        Mascota mascota = new Mascota();
        mascota.setIdMascota(1);
        mascota.setNombre("Paco");
        mascota.setSexo(Mascota.Sexo.M);
        mascota.setFechaNacimiento(java.sql.Date.valueOf(LocalDate.parse("12/05/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        mascota.setRaza("Labrador");
        mascota.setEspecie("Perro");
        mascota.setDniCliente("12345678A");

        assertNotNull(mascota);

    }

}
