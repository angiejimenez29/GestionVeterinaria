package modelo.dao.conexion;

import java.sql.Connection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Pruebas de integración para la clase Conexion.
 *
 * Comprueba la conexión y desconexión a la base de datos para el rol de
 * Director y Recepcionista.
 *
 * @author Nuria Vázquez 
 */
public class ConexionIT {

    public ConexionIT() {
    }

    /**
     * Comprueba la conexión a la bas de datos con el rol de director.
     *
     * @author Nuria Vázquez Flores
     */
    @Test
    public void testConectarDirector() {
        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Conectar Director ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-");
        System.out.println();
        Connection result = Conexion.conectarDirector();
        assertNotNull(result);
    }

    /**
     * Comprueba la conexión a la bas de datos con el rol de recepcionista.
     *
     * @author Nuria Vázquez Flores
     */
    @Test
    public void testConectarRecepcionista() {
        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" Prueba Conectar Recepcionista ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println();
        Connection result = Conexion.conectarRecepcionista();
        assertNotNull(result);
    }

    /**
     * Comprueba la desconexión de la bas de datos.
     *
     * @author Nuria Vázquez Flores
     */
    @Test
    public void testDesconectar() {
        System.out.println();
        System.out.println("*-*-*-*-*-*-*-*-*-*-");
        System.out.println(" Prueba Desconectar ");
        System.out.println("*-*-*-*-*-*-*-*-*-*-");
        System.out.println();
        Conexion.desconectar();
    }

}
