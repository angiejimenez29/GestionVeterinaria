package gestionveterinaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {
    private Conexion conexion;

    public Usuario(Conexion conexion) {
        this.conexion = conexion;
    }

    public boolean iniciarSesion(String usuario, String contrasena) {
        String sql = "SELECT * FROM Usuario WHERE usuario = ? AND contrasena = ?";
        try (PreparedStatement stmt = conexion.conectar().prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Inicio de sesión exitoso.");
                return true;
            } else {
                System.out.println("Usuario o contraseña incorrectos.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


