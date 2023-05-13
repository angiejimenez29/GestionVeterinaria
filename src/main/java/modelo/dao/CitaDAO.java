package modelo.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cita;
import static modelo.dao.conexion.Conexion.connection;
import utils.Alertas;

/**
 * Clase CitaDAO Esta clase representa un objeto DAO (Data Access Object) para
 * la entidad Cita, que se encarga de realizar operaciones de lectura,
 * escritura, actualización y eliminación de registros de citas en la base de
 * datos.
 *
 * @author Nuria Vázquez 
 */
public class CitaDAO {

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
     * Constructor por defecto de la clase CitaDAO.
     */
    public CitaDAO() {

    }

    /**
     * Método que obtiene una lista de todos las citas almacenadas en la base de
     * datos.
     *
     * @return una lista de objetos Cita
     */
    public List<Cita> obtenerCitas() {
        List<Cita> listaCitas = new ArrayList<>();
        try {

            String sql = "SELECT c.fecha, c.hora, c.tipo_consulta, c.diagnostico, c.tratamiento, c.medicacion, c.precio, v.nombre, v.apellido_uno, v.apellido_dos, c.ID_cita, m.nombre AS nombre_mascota, cl.DNI AS dni_cliente "
                    + "FROM cita c "
                    + "INNER JOIN veterinario v ON c.ID_veterinario = v.ID_veterinario "
                    + "INNER JOIN mascota m ON c.ID_mascota = m.ID_mascota "
                    + "INNER JOIN cliente cl ON c.ID_cliente = cl.ID_cliente";

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cita cita = new Cita();

                cita.setIdCita(resultSet.getInt("ID_cita"));
                cita.setNombreVeterinario(resultSet.getString("nombre"));
                cita.setApellidoUnoVeterinario(resultSet.getString("apellido_uno"));
                cita.setApellidoDosVeterinario(resultSet.getString("apellido_dos"));
                cita.setNombreMascota(resultSet.getString("nombre_mascota"));
                cita.setFechaCita(resultSet.getDate("fecha"));
                cita.setHoraCita(resultSet.getTime("hora"));
                cita.setTipoConsulta(resultSet.getString("tipo_consulta"));
                cita.setDiagnostico(resultSet.getString("diagnostico"));
                cita.setTratamiento(resultSet.getString("tratamiento"));
                cita.setMedicacion(resultSet.getString("medicacion"));
                cita.setPrecio(resultSet.getBigDecimal("precio"));
                cita.setDniCliente(resultSet.getString("dni_cliente"));

                listaCitas.add(cita);

            }
        } catch (SQLException e) {
            System.out.println("Clase CitaDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase CitaDAO - Error desconocido: " + e.getMessage());
        }

        return listaCitas;
    }

    /**
     * Método que permite buscar citas en la base de datos a partir del DNI del
     * cliente.
     *
     * @param dniCliente DNI del cliente que se utilizará para buscar los
     * clientes asociados.
     * @return lista de citas asociadas al cliente cuyo DNI se ha proporcionado.
     */
    public List<Cita> buscarCitas(String dniCliente) {
        List<Cita> listaCitas = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT cita.ID_cita, cita.fecha, cita.hora, cita.tipo_consulta, cita.diagnostico, cita.tratamiento, cita.medicacion, cita.precio,\n"
                    + "veterinario.nombre, veterinario.apellido_uno, veterinario.apellido_dos,\n"
                    + "mascota.nombre AS nombre_mascota\n"
                    + "FROM cita\n"
                    + "INNER JOIN cliente ON cita.ID_cliente = cliente.ID_cliente\n"
                    + "INNER JOIN mascota ON cita.ID_mascota = mascota.ID_mascota\n"
                    + "INNER JOIN veterinario ON cita.ID_veterinario = veterinario.ID_veterinario\n"
                    + "WHERE cliente.DNI = ?");

            statement.setString(1, dniCliente);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Cita cita = new Cita();

                cita.setIdCita(resultSet.getInt("ID_cita"));
                cita.setNombreVeterinario(resultSet.getString("nombre"));
                cita.setApellidoUnoVeterinario(resultSet.getString("apellido_uno"));
                cita.setApellidoDosVeterinario(resultSet.getString("apellido_dos"));
                cita.setNombreMascota(resultSet.getString("nombre_mascota"));
                cita.setFechaCita(resultSet.getDate("fecha"));
                cita.setHoraCita(resultSet.getTime("hora"));
                cita.setTipoConsulta(resultSet.getString("tipo_consulta"));
                cita.setDiagnostico(resultSet.getString("diagnostico"));
                cita.setTratamiento(resultSet.getString("tratamiento"));
                cita.setMedicacion(resultSet.getString("medicacion"));
                cita.setPrecio(resultSet.getBigDecimal("precio"));

                listaCitas.add(cita);
            }

        } catch (SQLException ex) {
            System.out.println("Error al buscar cita: " + ex.getMessage());
        }
        return listaCitas;
    }

    /**
     * Método que elimina la cita indidcada de la base de datos.
     *
     * @param cita la cita a eliminar
     */
    public void eliminarCita(Cita cita) {
        try {
            String sql = "DELETE FROM cita WHERE ID_cita = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cita.getIdCita());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cita eliminada");
                alert.setHeaderText("Cita eliminada");
                alert.setContentText("La cita de " + cita.getNombreMascota() + " ha sido eliminada de forma permanente.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.showAndWait();

                System.out.println("Se ha eliminado correctamente la cita de: " + cita.getNombreMascota());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al eliminar cita");
                alert.setHeaderText("Ha ocurrido un error al eliminar la cita.");
                alert.setContentText("No se ha eliminado ninguna cita");
                Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.showAndWait();
                System.out.println("No se ha eliminado ninguna cita");
            }
        } catch (SQLException e) {
            System.out.println("Clase CitaDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase CitaDAO - Error desconocido: " + e.getMessage());
        }
    }

    /**
     * Método que agrega una nueva cita a la base de datos.
     *
     * @param cita la cita para agregar
     */
    public void agregarCita(Cita cita) {

        ResultSet rs = null;

        try {
            // Se define la consulta SQL con parámetros "?"
            String sql = "INSERT INTO cita (ID_mascota, ID_veterinario, ID_cliente, ID_recepcionista, fecha, hora, tipo_consulta, diagnostico, tratamiento, medicacion, precio)\n"
                    + "VALUES (\n"
                    + "  (SELECT ID_mascota FROM mascota WHERE nombre = ?),\n"
                    + "  (SELECT ID_veterinario FROM veterinario WHERE nombre = ? AND apellido_uno = ? AND apellido_dos = ?),\n"
                    + "  (SELECT ID_cliente FROM cliente WHERE DNI = ?),\n"
                    + "  (SELECT ID_recepcionista FROM recepcionista WHERE nombre = ?),\n"
                    + "  ?,\n"
                    + "  ?,\n"
                    + "  ?,\n"
                    + "  ?,\n"
                    + "  ?,\n"
                    + "  ?,\n"
                    + "  ?\n"
                    + ");";
            // Se prepara el statement con la consulta SQL
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, cita.getNombreMascota());
            statement.setString(2, cita.getNombreVeterinario());
            statement.setString(3, cita.getApellidoUnoVeterinario());
            statement.setString(4, cita.getApellidoDosVeterinario());
            statement.setString(5, cita.getDniCliente());
            statement.setString(6, cita.getNombreRecepcion());

            statement.setDate(7, cita.getFechaCita());
            statement.setObject(8, cita.getHoraCita());
            statement.setString(9, cita.getTipoConsulta());
            statement.setString(10, cita.getDiagnostico());
            statement.setString(11, cita.getTratamiento());
            statement.setString(12, cita.getMedicacion());
            statement.setBigDecimal(13, cita.getPrecio());

            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                cita.setIdCita(rs.getInt(1));
            }

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Cita añadida");
            alerta.setContentText("La cita ha sido añadida");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción CitaDAO: " + e.getMessage());
            System.out.println(e.getErrorCode());
            e.printStackTrace();
            System.out.println("Mensaje clase CitaDAO - Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase CitaDAO - Error desconocido: " + e.getMessage());
        }

    }

    /**
     * Método que modifica los datos de la cita indicada en la base de datos.
     *
     * @param cita la cita con los datos modificados
     */
    public void modificarCita(Cita cita) {
        try {
            String sql = "UPDATE cita SET fecha = ?, hora = ?, tipo_consulta = ?, diagnostico = ?, tratamiento = ?, medicacion = ?, precio = ? WHERE ID_cita = ?";

            statement = connection.prepareStatement(sql);
            statement.setDate(1, cita.getFechaCita());
            statement.setObject(2, cita.getHoraCita());
            statement.setString(3, cita.getTipoConsulta());
            statement.setString(4, cita.getDiagnostico());
            statement.setString(5, cita.getTratamiento());
            statement.setString(6, cita.getMedicacion());
            statement.setBigDecimal(7, cita.getPrecio());
            statement.setInt(8, cita.getIdCita());

            statement.executeUpdate();

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Cita modificada");
            alerta.setContentText("La cita ha sido modificado correctamente");
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
     * Verifica si un cliente tiene citas asignadas a través de su número de
     * DNI.
     *
     * @param dniCliente El número de identificación (DNI) del cliente.
     * @return true si el cliente tiene citas asignadas, false en caso
     * contrario.
     * @throws SQLException Si ocurre un error al ejecutar la consulta SQL.
     */
    public boolean clienteCitas(String dniCliente) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cita WHERE ID_cliente = (SELECT ID_cliente FROM cliente WHERE DNI = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dniCliente);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    /**
     *
     * Verifica si existe un veterinario con el nombre y apellidos especificados
     * en la base de datos.
     *
     * @param nombreVeterinario el nombre del veterinario.
     * @param apellidoUnoVeterinario el primer apellido del veterinario.
     * @param apellidoDosVeterinario el segundo apellido del veterinario.
     * @return true si existe un veterinario con los datos especificados, false
     * en caso contrario o si ocurre un error en la consulta SQL.
     */
    public boolean existeVeterinario(String nombreVeterinario, String apellidoUnoVeterinario, String apellidoDosVeterinario) {
        try {
            String sql = "SELECT COUNT(*) AS cantidad FROM veterinario WHERE nombre = ? AND apellido_uno = ? AND apellido_dos = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, nombreVeterinario);
            statement.setString(2, apellidoUnoVeterinario);
            statement.setString(3, apellidoDosVeterinario);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                if (cantidad > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en CitaDAO - existeVeterinario: " + e.getMessage());
        }
        return false;
    }

    /**
     *
     * Verifica si existe una cita en una fecha y hora determinada para un
     * veterinario específico.
     *
     * @param fecha La fecha de la cita a buscar.
     * @param hora La hora de la cita a buscar.
     * @param nombreVeterinario El nombre del veterinario de la cita a buscar.
     * @param apellidoUnoVeterinario El primer apellido del veterinario de la
     * cita a buscar.
     * @param apellidoDosVeterinario El segundo apellido del veterinario de la
     * cita a buscar.
     * @return True si existe la cita, False en caso contrario.
     */
    public boolean existeCita(LocalDate fecha, Time hora, String nombreVeterinario, String apellidoUnoVeterinario, String apellidoDosVeterinario) {
        try {
            String sql = "SELECT COUNT(*) AS cantidad FROM cita WHERE fecha = ? AND hora = ? AND ID_veterinario = (SELECT ID_veterinario FROM veterinario WHERE nombre = ? AND apellido_uno = ? AND apellido_dos = ?)";
            statement = connection.prepareStatement(sql);
            statement.setObject(1, fecha);
            statement.setTime(2, hora);
            statement.setString(3, nombreVeterinario);
            statement.setString(4, apellidoUnoVeterinario);
            statement.setString(5, apellidoDosVeterinario);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                if (cantidad > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en CitaDAO - existeCita: " + e.getMessage());
        }
        return false;
    }

    /**
     *
     * Verifica si existe un recepcionista con el nombre especificado.
     *
     * @param nombreRecepcionista El nombre del recepcionista a verificar.
     * @return true si existe un recepcionista con el nombre especificado, false
     * en caso contrario.
     */
    public boolean existeRecepcionista(String nombreRecepcionista) {
        try {
            String sql = "SELECT COUNT(*) AS cantidad FROM recepcionista WHERE nombre = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, nombreRecepcionista);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                if (cantidad > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en RecepcionistaDAO - existeRecepcionista: " + e.getMessage());
        }
        return false;
    }

    /**
     *
     * Comprueba si una mascota con el nombre y el DNI del cliente especificados
     * ya existe en la base de datos.
     *
     * @param nombreMascota el nombre de la mascota a buscar
     * @param dniCliente el DNI del cliente propietario de la mascota
     * @return true si existe una mascota con el nombre y DNI del cliente
     * especificados, false en caso contrario o si se produce un error en la
     * consulta SQL
     */
    public boolean existeMascota(String nombreMascota, String dniCliente) {
        try {
            String sql = "SELECT COUNT(*) AS cantidad FROM mascota WHERE nombre = ? AND DNI_cliente = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, nombreMascota);
            statement.setString(2, dniCliente);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int cantidad = rs.getInt("cantidad");
                if (cantidad > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL: " + e.getMessage());
        }
        return false;
    }

}
