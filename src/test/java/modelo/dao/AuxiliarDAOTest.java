package modelo.dao;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.dao.conexion.Conexion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Esta clase contiene pruebas unitarias para la clase AuxiliarDAO. Utiliza una
 * base de datos de prueba creada específicamente para fines de prueba.
 *
 * @author Nuria Vázquez 
 */
public class AuxiliarDAOTest {

    private Connection connection;

    public AuxiliarDAOTest() {
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
     * @throws SQLException si se produce un error al acceder a la base de
     * datos.
     */
    @Test
    public void testAuxiliarExiste() throws SQLException {

        try {
            AuxiliarDAO auxiliarDAO = new AuxiliarDAO();
            String dniAuxiliar = "20451201S";
            boolean resultadoEsperado = true;
            boolean resultadoObtenido = auxiliarDAO.auxiliarExiste(dniAuxiliar);
            assertEquals(resultadoEsperado, resultadoObtenido);
            if (resultadoObtenido) {
                System.out.println("El auxiliar con DNI " + dniAuxiliar + " existe en la base de datos.");
            }
        } catch (SQLException e) {
            System.err.println("Error al acceder a la base de datos: " + e.getMessage());
        }
    }

}
