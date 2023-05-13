package modelo.dao;

import java.sql.Connection;
import java.util.List;
import modelo.Cliente;
import modelo.dao.conexion.Conexion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Esta clase contiene pruebas unitarias para la clase ClienteDAO. Utiliza una
 * base de datos de prueba creada específicamente para fines de prueba.
 *
 * @author Nuria Vázquez 
 */
public class ClienteDAOTest {

    private Connection connection;

    public ClienteDAOTest() {
    }

    @BeforeEach
    public void setUp() {
        connection = Conexion.conectarDirector();
        System.out.println();
        System.out.println("*-*-*-*-*-*");
        System.out.println(" Before ");
        System.out.println("*-*-*-*-*-*");
        System.out.println();
    }

    /**
     *
     * Método de prueba unitaria para comprobar el método auxiliarExiste de la
     * clase AuxiliarDAO.
     *
     * Verifica si un auxiliar con un determinado DNI existe en la base de
     * datos.
     *
     */
    @Test
    public void testBuscarCliente() {

        ClienteDAO instance = new ClienteDAO();
        String dni = "12345678A";
        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        List<Cliente> result = instance.buscarCliente(dni);
        Cliente clienteObtenido = null;
        for (Cliente c : result) {
            if (c.getDni().equals(cliente.getDni())) {
                clienteObtenido = c;
                break;
            }
        }
        assertNotNull(clienteObtenido);
        assertEquals(cliente.getDni(), clienteObtenido.getDni());
        System.out.println("El cliente con dni " + cliente.getDni() + " se encuentra en la base de datos");
    }
}    
