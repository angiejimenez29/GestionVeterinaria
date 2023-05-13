package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * La clase VeterinarioTest contiene pruebas unitarias para la clase
 * Veterinario.
 *
 * @author Nuria Vázquez 
 */
public class VeterinarioTest {

    public VeterinarioTest() {
    }

    /**
     * Prueba unitaria del constructor de la clase Veterinario.
     */
    @Test
    public void tesVeterinarioConstructor() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Veterinario Constructor");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println();

        Veterinario veterinario = new Veterinario();

        assertNotNull(veterinario);
    }

    /**
     * Prueba unitaria para el método getEspecialidad de la clase Veterinario.
     * Se verifica que el valor devuelto por el método coincida con el valor
     * establecido mediante el método setEspecialidad.
     */
    @Test
    public void testgetEspecialidad() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba getEspecialidad Veterinario");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Veterinario veterinario = new Veterinario();

        veterinario.setEspecialidad("Cardiología");
        String result = veterinario.getEspecialidad();

        assertEquals("Cardiología", result);
    }

    /**
     *
     * Prueba unitaria para verificar la creación correcta de un objeto
     * Veterinario.
     *
     * Se comprueba que todos los atributos se inicializan correctamente.
     */
    @Test
    public void testVeterinario() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Veterinario ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-");
        System.out.println();

        Veterinario nuevoVeterinario = new Veterinario();
        nuevoVeterinario.setEspecialidad("Dermatología");
        nuevoVeterinario.setSalario(new BigDecimal("3000.00"));
        nuevoVeterinario.setNombre("Ana");
        nuevoVeterinario.setApellidoUno("González");
        nuevoVeterinario.setApellidoDos("Pérez");
        nuevoVeterinario.setDni("12345678A");
        nuevoVeterinario.setFechaNac(java.sql.Date.valueOf(LocalDate.parse("12/05/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        nuevoVeterinario.setDireccion("Calle costa del sol, 12");
        nuevoVeterinario.setCp(29001);
        nuevoVeterinario.setTelefono(950121212);
        nuevoVeterinario.setEmail("ana.gonzalez@gmail.com");
        nuevoVeterinario.setTurno(Veterinario.Turno.M);

        assertNotNull(nuevoVeterinario);

    }

}
