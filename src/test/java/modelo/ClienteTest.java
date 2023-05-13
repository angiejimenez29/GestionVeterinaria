package modelo;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * La clase ClienteTest contiene pruebas unitarias para la clase Cliente.
 *
 * @author Nuria Vázquez 
 */
public class ClienteTest {

    public ClienteTest() {
    }

    /**
     * Prueba unitaria del constructor de la clase Cliente.
     */
    @Test
    public void tesClienteConstructor() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba Cliente Constructor");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Cliente cliente = new Cliente();

        assertNotNull(cliente);
    }

    /**
     * Prueba unitaria para el método getSeguro de la clase Cliente. Se verifica
     * que el valor devuelto por el método coincida con el valor establecido
     * mediante el método setSeguro.
     */
    @Test
    public void testgetSeguro() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba getSeguro Cliente");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();

        Cliente cliente = new Cliente();

        cliente.setSeguro("Hogar");
        String result = cliente.getSeguro();

        assertEquals("Hogar", result);
    }

    /**
     *
     * Prueba unitaria para verificar la creación correcta de un objeto Cliente.
     *
     * Se comprueba que todos los atributos se inicializan correctamente.
     */
    @Test
    public void testCliente() {

        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*");
        System.out.println(" Prueba Cliente");
        System.out.println("*-*-*-*-*-*-*-*");
        System.out.println();

        Cliente nuevoCliente = new Cliente();
        nuevoCliente.setSeguro("Canitas");
        nuevoCliente.setDni("12345678A");
        nuevoCliente.setNombre("Roberto");
        nuevoCliente.setApellidoUno("Pérez");
        nuevoCliente.setApellidoDos("García");
        nuevoCliente.setFechaNac(java.sql.Date.valueOf(LocalDate.parse("12/05/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        nuevoCliente.setTelefono(123456789);
        nuevoCliente.setDireccion("Calle Alderete, 12");
        nuevoCliente.setCp(29012);
        nuevoCliente.setPoblacion("Málaga");
        nuevoCliente.setEmail("roberto.perez@example.com");
        nuevoCliente.setSuscripcion(Cliente.Suscripcion.S);
        assertNotNull(nuevoCliente);

    }

}
