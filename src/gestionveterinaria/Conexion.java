package gestionveterinaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private final String usuario = "upqzp62esxzralwp";
    private final String contrasena = "mOVSX56rSYoCM8OA9ijU";
    private final String db = "bqsw3lczrwzt3abas1po";
    private final String ip = "bqsw3lczrwzt3abas1po-mysql.services.clever-cloud.com";
    private final String puerto = "3306";
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + db;
    private Connection cx;

    public Connection conectar() {
        if (cx == null) {
            try {
                Class.forName(driver);
                cx = DriverManager.getConnection(cadena, usuario, contrasena);
                System.out.println("Se conectó a la base de datos " + db);
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println("No se pudo conectar a la base de datos " + db);
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cx;
    }

    public void desconectar() {
        try {
            if (cx != null && !cx.isClosed()) {
                cx.close();
                cx=null;
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
