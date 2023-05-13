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
import modelo.Recepcionista;
import static modelo.dao.conexion.Conexion.connection;
import utils.Alertas;

/**
 * Clase RecepcionistaDAO Esta clase representa un objeto DAO (Data Access
 * Object) para la entidad Recepcionista, que se encarga de realizar operaciones
 * de lectura, escritura, actualización y eliminación de registros de
 * recepcionistas en la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class RecepcionistaDAO {

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
     * Constructor por defecto de la clase AuxiliarDAO.
     */
    public RecepcionistaDAO() {

    }

    /**
     * Método que obtiene una lista de todos los recepcionistas almacenados en
     * la base de datos.
     *
     * @return una lista de objetos Recepcionista
     */
    public List<Recepcionista> obtenerRecepcionistas() {
        List<Recepcionista> listaRecepcionistas = new ArrayList<>();
        try {

            String sql = "SELECT * FROM recepcionista";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Recepcionista recepcionista = new Recepcionista();

                recepcionista.setIdRecepcionista(resultSet.getInt("ID_recepcionista"));
                recepcionista.setSalario(resultSet.getBigDecimal("salario"));
                recepcionista.setNombre(resultSet.getString("nombre"));
                recepcionista.setApellidoUno(resultSet.getString("apellido_uno"));
                recepcionista.setApellidoDos(resultSet.getString("apellido_dos"));
                recepcionista.setDni(resultSet.getString("DNI"));
                recepcionista.setFechaNac(resultSet.getDate("fecha_nac"));
                recepcionista.setDireccion(resultSet.getString("direccion"));
                recepcionista.setCp(resultSet.getInt("cp"));
                recepcionista.setTelefono(resultSet.getInt("telefono"));
                recepcionista.setEmail(resultSet.getString("email"));
                recepcionista.setTurno(Recepcionista.Turno.valueOf(resultSet.getString("turno")));

                listaRecepcionistas.add(recepcionista);

            }
        } catch (SQLException e) {
            System.out.println("Clase RecepcionistaDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase RecepcionistaDAO - Error desconocido: " + e.getMessage());
        }

        return listaRecepcionistas;
    }

    /**
     * Método que permite buscar recepcionistas en la base de datos a partir del
     * DNI del recepcionista.
     *
     * @param dniRecepcionista DNI del recepcionista que se utilizará para
     * buscar los recepcionistas asociados.
     * @return lista de recepcionistas asociados al recepcionista cuyo DNI se ha
     * proporcionado.
     */
    public List<Recepcionista> buscarRecepcionistas(String dniRecepcionista) {
        List<Recepcionista> listaRecepcionistas = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM recepcionista WHERE DNI LIKE ?");
            statement.setString(1, "%" + dniRecepcionista + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Recepcionista recepcionista = new Recepcionista();

                recepcionista.setIdRecepcionista(resultSet.getInt("ID_recepcionista"));
                recepcionista.setSalario(resultSet.getBigDecimal("salario"));
                recepcionista.setNombre(resultSet.getString("nombre"));
                recepcionista.setApellidoUno(resultSet.getString("apellido_uno"));
                recepcionista.setApellidoDos(resultSet.getString("apellido_dos"));
                recepcionista.setDni(resultSet.getString("DNI"));
                recepcionista.setFechaNac(resultSet.getDate("fecha_nac"));
                recepcionista.setDireccion(resultSet.getString("direccion"));
                recepcionista.setCp(resultSet.getInt("cp"));
                recepcionista.setTelefono(resultSet.getInt("telefono"));
                recepcionista.setEmail(resultSet.getString("email"));
                recepcionista.setTurno(Recepcionista.Turno.valueOf(resultSet.getString("turno")));

                listaRecepcionistas.add(recepcionista);

            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar recepcionista: " + ex.getMessage());
        }
        return listaRecepcionistas;
    }

    /**
     * Método que elimina el recepcionista indidcado de la base de datos.
     *
     * @param recepcionista el recepcionista a eliminar
     */
    public void eliminarRecepcionista(Recepcionista recepcionista) {
        try {
            String sql = "DELETE FROM recepcionista WHERE ID_recepcionista = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recepcionista.getIdRecepcionista());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Recepcionista eliminado");
                alert.setHeaderText("Recepcionista eliminado");
                alert.setContentText("El recepcionista " + recepcionista.getDni() + " ha sido eliminado correctamente.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.showAndWait();

                System.out.println("Se ha eliminado correctamente el recepcionista con id: " + recepcionista.getIdRecepcionista());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al eliminar recepcionista");
                alert.setHeaderText("Ha ocurrido un error al eliminar el recepcionista.");
                alert.setContentText("No se ha eliminado ningún recepcionista");
                Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.showAndWait();
                System.out.println("No se ha eliminado ningún recepcionista");
            }
        } catch (SQLException e) {
            System.out.println("Clase RecepcionistaDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase RecepcionistaDAO - Error desconocido: " + e.getMessage());
        }
    }

    /**
     * Método que agrega un nuevo recepcionista a la base de datos.
     *
     * @param recepcionista el recepcionista a agregar
     */
    public void agregarRecepcionista(Recepcionista recepcionista) {

        ResultSet rs = null;
        try {
            // Se define la consulta SQL con parámetros "?"
            String sql = "INSERT INTO recepcionista (salario, nombre, apellido_uno, apellido_dos, DNI, fecha_nac, direccion, cp, telefono, email, turno) VALUES (?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
            // Se prepara el statement con la consulta SQL
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Se establecen los valores de los parámetros de la consulta
            statement.setBigDecimal(1, recepcionista.getSalario());
            statement.setString(2, recepcionista.getNombre());
            statement.setString(3, recepcionista.getApellidoUno());
            statement.setString(4, recepcionista.getApellidoDos());
            statement.setString(5, recepcionista.getDni());
            statement.setDate(6, recepcionista.getFechaNac());
            statement.setString(7, recepcionista.getDireccion());
            statement.setInt(8, recepcionista.getCp());
            statement.setInt(9, recepcionista.getTelefono());
            statement.setString(10, recepcionista.getEmail());
            statement.setString(11, recepcionista.getTurno().name());
            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                recepcionista.setIdRecepcionista(rs.getInt(1));
            }

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Recepcionista añadido");
            alerta.setContentText("El recepcionista ha sido añadido ");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
            System.out.println("Mensaje clase RecepcionistaDAO - Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase RecepcionistaDAO - Error desconocido: " + e.getMessage());
        }

    }

    /**
     * Método que modifica los datos del recepcionista indicado en la base de
     * datos.
     *
     * @param recepcionista el recepcionista con los datos modificados
     */
    public void modificarRecepcionista(Recepcionista recepcionista) {
        try {
            String sql = "UPDATE recepcionista SET salario=?, nombre=?, apellido_uno=?, apellido_dos=?, DNI=?, fecha_nac=?, direccion=?, cp=?, telefono=?, email=?, turno=?  WHERE ID_recepcionista=?";
            statement = connection.prepareStatement(sql);
            statement.setBigDecimal(1, recepcionista.getSalario());
            statement.setString(2, recepcionista.getNombre());
            statement.setString(3, recepcionista.getApellidoUno());
            statement.setString(4, recepcionista.getApellidoDos());
            statement.setString(5, recepcionista.getDni());
            statement.setDate(6, recepcionista.getFechaNac());
            statement.setString(7, recepcionista.getDireccion());
            statement.setInt(8, recepcionista.getCp());
            statement.setInt(9, recepcionista.getTelefono());
            statement.setString(10, recepcionista.getEmail());
            statement.setString(11, recepcionista.getTurno().name());
            statement.setInt(12, recepcionista.getIdRecepcionista());
            statement.executeUpdate();

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Modificar recepcionista");
            alerta.setHeaderText("Recepcionista modificado");
            alerta.setContentText("El recepcionista " + recepcionista.getNombre() + " ha sido modificado correctamente");
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
     * Este método comprueba si un recepcionista con el DNI proporcionado existe
     * en la base de datos.
     *
     * @param dniRecepcionista el DNI del recepcionista a buscar
     * @return true si el recepcionista existe en la base de datos, false de lo
     * contrario
     * @throws SQLException si ocurre algún error al ejecutar la consulta SQL
     *
     */
    public boolean recepcionistaExiste(String dniRecepcionista) throws SQLException {
        String sql = "SELECT COUNT(*) FROM recepcionista WHERE DNI = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dniRecepcionista);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

}
