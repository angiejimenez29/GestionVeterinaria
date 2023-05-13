package modelo.dao.conexion;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase Conexión, encargada de realizar la conexión con la base de datos.
 *
 * @author Nuria Vázquez 
 *
 */
public class Conexion {

    //Declaración e inicialización de variables.
    public static String url = "jdbc:mysql://localhost/clinica_veterinaria";
    public static Connection connection;
    public static Statement sentencia;

    /**
     *
     * Método que se encarga de realizar la conexión a la base de datos como
     * director.
     *
     * @return la conexión establecida si fue exitosa, null si falló.
     */
    public static Connection conectarDirector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "director", "director1234");

            if (connection != null) {
                System.out.println("Conexión establecida con la BD el DIRECTOR");
            } else {
                System.out.println("Error al comunicar con la base de datos");
                return null;
            }
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e + " Error al comunicar con la base de datos");
            return null;
        }
    }

    /**
     *
     * Método que se encarga de realizar la conexión a la base de datos como
     * recepcionista.
     *
     * @return la conexión establecida si fue exitosa, null si falló.
     */
    public static Connection conectarRecepcionista() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "recepcionista", "recepcionista1234");

            if (connection != null) {
                System.out.println("Conexión establecida con la BD el RECEPCIONISTA");
            }
            return connection;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e + " Error al comunicar con la base de datos");
            return null;
        }

    }

    /**
     * Método para poder desconectarse de la base de datos.
     *
     */
    public static void desconectar() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

}
