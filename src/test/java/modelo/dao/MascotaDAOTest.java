package modelo.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import modelo.Mascota;
import modelo.dao.conexion.Conexion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Esta clase contiene pruebas unitarias para la clase MascotaDAO. Utiliza una
 * base de datos de prueba creada específicamente para fines de prueba.
 *
 * @author Nuria Vázquez 
 */
public class MascotaDAOTest {

    private Connection connection;

    public MascotaDAOTest() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
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

    @AfterEach
    public void tearDown() {

    }

    /**
     *
     * Prueba unitaria del método obtenerMascotas() de la clase MascotaDAO.
     * Verifica que el método retorna una lista que incluye una mascota
     * específica, con nombre "Bella" y dueño con DNI "23456789G". Compara los
     * atributos de la mascota encontrada con los valores esperados y muestra un
     * mensaje indicando si se encontró la mascota.
     *
     * @author Nuria Vázquez Flores
     */
    @Test
    public void testObtenerMascotas() {
        System.out.println("testObtenerMascotas");

        MascotaDAO instance = new MascotaDAO();
        List<Mascota> expResult = new ArrayList<>();

        // Crea un objeto Mascota con los datos esperados
        Mascota mascota = new Mascota();
        mascota.setNombre("Bella");
        mascota.setDniCliente("23456789G");

        // Agrega la mascota creada a la lista de resultados esperados
        expResult.add(mascota);

        // Obtiene la lista de mascotas desde la base de datos
        List<Mascota> result = instance.obtenerMascotas();

        // Busca la mascota específica en la lista obtenida y compara sus atributos con los valores esperados
        Mascota mascotaObtenida = null;
        for (Mascota m : result) {
            if (m.getNombre().equals(mascota.getNombre()) && m.getDniCliente().equals(mascota.getDniCliente())) {
                mascotaObtenida = m;
                break;
            }
        }

        assertNotNull(mascotaObtenida);
        assertEquals(mascota.getNombre(), mascotaObtenida.getNombre());
        assertEquals(mascota.getDniCliente(), mascotaObtenida.getDniCliente());
        System.out.println("La mascota con nombre " + mascota.getNombre() + " y el dueño con DNI " + mascota.getDniCliente() + " se encuentra en la base de datos");

    }

}
