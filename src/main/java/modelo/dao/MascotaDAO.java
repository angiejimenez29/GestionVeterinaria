package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Mascota;
import static modelo.dao.conexion.Conexion.connection;
import utils.Alertas;

/**
 * Clase MascotaDAO Esta clase representa un objeto DAO (Data Access Object)
 * para la entidad Mascota, que se encarga de realizar operaciones de lectura,
 * escritura, actualización y eliminación de registros de mascotas en la base de
 * datos.
 *
 * @author Nuria Vázquez 
 */
public class MascotaDAO {

    /**
     * Representa un objeto PreparedStatement, utilizado para enviar consultas
     * SQL parametrizadas a la base de datos.
     */
    PreparedStatement statement;
    /**
     * Representa un objeto ResultSet, utilizado para almacenar el resultado de
     * una consulta SQL.
     */
    ResultSet resultSet;

    /**
     * Constructor por defecto de la clase MascotaDAO.
     */
    public MascotaDAO() {

    }

    /**
     * Método que obtiene una lista de todas las mascotas almacenadas en la base
     * de datos.
     *
     * @return una lista de objetos Mascota
     */
    public List<Mascota> obtenerMascotas() {
        List<Mascota> listaMascotas = new ArrayList<>();
        try {

            String sql = "SELECT * FROM mascota";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Mascota mascota = new Mascota();
                mascota.setIdMascota(resultSet.getInt("ID_mascota"));
                mascota.setNombre(resultSet.getString("nombre"));
                mascota.setSexo(Mascota.Sexo.valueOf(resultSet.getString("sexo")));
                mascota.setFechaNacimiento(resultSet.getDate("fecha_nac"));
                mascota.setRaza(resultSet.getString("raza"));
                mascota.setEspecie(resultSet.getString("especie"));
                mascota.setDniCliente(resultSet.getString("DNI_cliente"));
                listaMascotas.add(mascota);

            }
        } catch (SQLException e) {
            System.out.println("Clase MascotaDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase MascotaDAO - Error desconocido: " + e.getMessage());

        }

        return listaMascotas;
    }

    /**
     * Método que agrega una nueva mascota a la base de datos.
     *
     * @param mascota la mascota a agregar
     */
    public void agregarMascota(Mascota mascota) {

        ResultSet rs = null;
        try {
            // Se define la consulta SQL con parámetros "?"
            String sql = "INSERT INTO mascota (nombre, sexo, fecha_nac, raza, especie, DNI_cliente) VALUES (?, ?, ?, ?, ?, ?)";
            // Se prepara el statement con la consulta SQL
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Se establecen los valores de los parámetros de la consulta
            statement.setString(1, mascota.getNombre());
            statement.setString(2, mascota.getSexo().name());
            statement.setDate(3, mascota.getFechaNacimiento());
            statement.setString(4, mascota.getRaza());
            statement.setString(5, mascota.getEspecie());
            statement.setString(6, mascota.getDniCliente());

            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                mascota.setIdMascota(rs.getInt(1));
            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Mascota añadida");
            alerta.setContentText("La mascota ha sido añadida ");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
            System.out.println("Mensaje clase MascotaDAO - Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase MascotaDAO - Error desconocido: " + e.getMessage());
        }

    }

    /**
     * Método que permite buscar mascotas en la base de datos a partir del DNI
     * del cliente.
     *
     * @param dniCliente DNI del cliente que se utilizará para buscar las
     * mascotas asociadas.
     * @return lista de mascotas asociadas al cliente cuyo DNI se ha
     * proporcionado.
     */
    public List<Mascota> buscarMascota(String dniCliente) {
        List<Mascota> listaMascotas = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM mascota WHERE DNI_cliente LIKE ?");
            statement.setString(1, "%" + dniCliente + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Mascota mascota = new Mascota();
                mascota.setIdMascota(resultSet.getInt("ID_mascota"));
                mascota.setNombre(resultSet.getString("nombre"));
                mascota.setSexo(Mascota.Sexo.valueOf(resultSet.getString("sexo")));
                mascota.setFechaNacimiento(resultSet.getDate("fecha_nac"));
                mascota.setRaza(resultSet.getString("raza"));
                mascota.setEspecie(resultSet.getString("especie"));
                mascota.setDniCliente(resultSet.getString("DNI_cliente"));
                listaMascotas.add(mascota);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar mascota: " + ex.getMessage());
        }
        return listaMascotas;
    }

    /**
     * Método que elimina la mascota indicada de la base de datos.
     *
     * @param mascota la mascota a eliminar
     */
    public void eliminarMascota(Mascota mascota) {
        try {
            String sql = "DELETE FROM mascota WHERE ID_mascota = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, mascota.getIdMascota());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Se ha eliminado correctamente la mascota con id: " + mascota.getIdMascota());
            } else {
                System.out.println("No se ha eliminado ninguna mascota");
            }
        } catch (SQLException e) {
            System.out.println("Clase MascotaDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase MascotaDAO - Error desconocido: " + e.getMessage());
        }
    }

    /**
     * Método que modifica los datos de la mascota indicada en la base de datos.
     *
     * @param mascota la mascota con los datos modificados
     */
    public void modificarMascota(Mascota mascota) {
        try {
            String sql = "UPDATE mascota SET nombre=?, sexo=?, fecha_nac=?, raza=?, especie=?, DNI_cliente=? WHERE ID_mascota=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, mascota.getNombre());
            statement.setString(2, mascota.getSexo().name());
            statement.setDate(3, mascota.getFechaNacimiento());
            statement.setString(4, mascota.getRaza());
            statement.setString(5, mascota.getEspecie());
            statement.setString(6, mascota.getDniCliente());
            statement.setInt(7, mascota.getIdMascota());

            statement.executeUpdate();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Modificar Mascota");
            alerta.setHeaderText("Mascota modificada correctamente");
            alerta.setContentText("La mascota ha sido modificada correctamente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

    /**
     *
     * Este método comprueba si un cliente con el DNI proporcionado existe en la
     * base de datos.
     *
     * @param dniCliente el DNI del cliente a buscar
     * @return true si el cliente existe en la base de datos, false de lo
     * contrario
     * @throws SQLException si ocurre algún error al ejecutar la consulta SQL
     *
     */
    public boolean clienteExiste(String dniCliente) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cliente WHERE DNI = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dniCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

}
