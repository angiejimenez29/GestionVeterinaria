package gestionveterinaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    String usuario = "upqzp62esxzralwp";
    String contrasena = "mOVSX56rSYoCM8OA9ijU";
    String db = "bqsw3lczrwzt3abas1po";
    String ip = "bqsw3lczrwzt3abas1po-mysql.services.clever-cloud.com";
    String puerto = "3306";
    
    String driver = "com.mysql.cj.jdbc.Driver";
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + db;
    Connection cx;
    public Conexion(){
        
    }
    
    public Connection conectar(){
        try{
            Class.forName(driver);
            cx =DriverManager.getConnection(cadena,usuario,contrasena);
            System.out.print("Se conecto a BD" +db);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print("No se conecto a BD" +db);
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }return cx;
    }
    
    public void desconectar(){
        try {
            cx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        Conexion conexion = new Conexion();
        conexion.conectar();
        System.out.println("");
        Administrador administrador = new Administrador();
        administrador.iniciarPrograma();
}
    }  


