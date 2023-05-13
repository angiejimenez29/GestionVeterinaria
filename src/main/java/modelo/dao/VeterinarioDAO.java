package modelo.dao;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Veterinario;
import static modelo.dao.conexion.Conexion.connection;
import utils.Alertas;

/**
 * Clase VeterinarioDAO Esta clase representa un objeto DAO (Data Access Object)
 * para la entidad Veterinario, que se encarga de realizar operaciones de
 * lectura, escritura, actualización y eliminación de registros de veterinarios
 * en la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class VeterinarioDAO {

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
    public VeterinarioDAO() {

    }

    /**
     * Método que obtiene una lista de todos los veterinarios almacenados en la
     * base de datos.
     *
     * @return una lista de objetos Veterinario
     */
    public List<Veterinario> obtenerVeterinarios() {
        List<Veterinario> listaVeterinarios = new ArrayList<>();
        try {

            String sql = "SELECT * FROM veterinario";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setIdVeterinario(resultSet.getInt("ID_veterinario"));
                veterinario.setEspecialidad(resultSet.getString("especialidad"));
                veterinario.setSalario(resultSet.getBigDecimal("salario"));
                veterinario.setNombre(resultSet.getString("nombre"));
                veterinario.setApellidoUno(resultSet.getString("apellido_uno"));
                veterinario.setApellidoDos(resultSet.getString("apellido_dos"));
                veterinario.setDni(resultSet.getString("DNI"));
                veterinario.setFechaNac(resultSet.getDate("fecha_nac"));
                veterinario.setDireccion(resultSet.getString("direccion"));
                veterinario.setCp(resultSet.getInt("cp"));
                veterinario.setTelefono(resultSet.getInt("telefono"));
                veterinario.setEmail(resultSet.getString("email"));
                veterinario.setTurno(Veterinario.Turno.valueOf(resultSet.getString("turno")));

                listaVeterinarios.add(veterinario);

            }
        } catch (SQLException e) {
            System.out.println("Clase VeterinarioDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase VeterinarioDAO - Error desconocido: " + e.getMessage());

        }

        return listaVeterinarios;
    }

    /**
     * Método que permite buscar veterinarios/as en la base de datos a partir
     * del DNI del veterinario/a.
     *
     * @param dniVeterinario DNI del veterinario/a que se utilizará para buscar
     * los veterinarios asociados.
     * @return lista de clientes asociados al cliente cuyo DNI se ha
     * proporcionado.
     */
    public List<Veterinario> buscarVeterinarios(String dniVeterinario) {
        List<Veterinario> listaVeterinarios = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM veterinario WHERE DNI LIKE ?");
            statement.setString(1, "%" + dniVeterinario + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setIdVeterinario(resultSet.getInt("ID_veterinario"));
                veterinario.setEspecialidad(resultSet.getString("especialidad"));
                veterinario.setSalario(resultSet.getBigDecimal("salario"));
                veterinario.setNombre(resultSet.getString("nombre"));
                veterinario.setApellidoUno(resultSet.getString("apellido_uno"));
                veterinario.setApellidoDos(resultSet.getString("apellido_dos"));
                veterinario.setDni(resultSet.getString("DNI"));
                veterinario.setFechaNac(resultSet.getDate("fecha_nac"));
                veterinario.setDireccion(resultSet.getString("direccion"));
                veterinario.setCp(resultSet.getInt("cp"));
                veterinario.setTelefono(resultSet.getInt("telefono"));
                veterinario.setEmail(resultSet.getString("email"));
                veterinario.setTurno(Veterinario.Turno.valueOf(resultSet.getString("turno")));

                listaVeterinarios.add(veterinario);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar veterinario: " + ex.getMessage());
        }
        return listaVeterinarios;
    }

    /**
     * Método que elimina el veterinario/a indidcado de la base de datos.
     *
     * @param veterinario el veterinario/a a eliminar
     */
    public void eliminarVeterinario(Veterinario veterinario) {
        try {
            String sql = "DELETE FROM veterinario WHERE ID_veterinario = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, veterinario.getIdVeterinario());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Se ha eliminado correctamente el veterinario con id: " + veterinario.getIdVeterinario());
            } else {
                System.out.println("No se ha eliminado ningún veterinario");
            }
        } catch (SQLException e) {
            System.out.println("Clase VeterinarioDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase VeterinarioDAO - Error desconocido: " + e.getMessage());
        }
    }

    /**
     *
     * Este método comprueba si un veterinario/a con el DNI proporcionado existe
     * en la base de datos.
     *
     * @param dniVeterinario el DNI del veterinario/a a buscar
     * @return true si el veterinario/a existe en la base de datos, false de lo
     * contrario
     * @throws SQLException si ocurre algún error al ejecutar la consulta SQL
     *
     */
    public boolean veterinarioExiste(String dniVeterinario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM veterinario WHERE DNI = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dniVeterinario);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }
        }
    }

    /**
     * Método que agrega un nuevo veterinario/a a la base de datos.
     *
     * @param veterinario el veterinario/a a agregar
     */
    public void agregarVeterinario(Veterinario veterinario) {

        ResultSet rs = null;
        try {
            // Se define la consulta SQL con parámetros "?"
            String sql = "INSERT INTO veterinario (especialidad, salario, nombre, apellido_uno, apellido_dos, DNI, fecha_nac, direccion, cp, telefono, email, turno) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
            // Se prepara el statement con la consulta SQL
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Se establecen los valores de los parámetros de la consulta
            statement.setString(1, veterinario.getEspecialidad());
            statement.setBigDecimal(2, veterinario.getSalario());
            statement.setString(3, veterinario.getNombre());
            statement.setString(4, veterinario.getApellidoUno());
            statement.setString(5, veterinario.getApellidoDos());
            statement.setString(6, veterinario.getDni());
            statement.setDate(7, veterinario.getFechaNac());
            statement.setString(8, veterinario.getDireccion());
            statement.setInt(9, veterinario.getCp());
            statement.setInt(10, veterinario.getTelefono());
            statement.setString(11, veterinario.getEmail());
            statement.setString(12, veterinario.getTurno().name());
            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                veterinario.setIdVeterinario(rs.getInt(1));
            }

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Veterinario/a añadido/a");
            alerta.setContentText("El/la veterinario/a" + veterinario.getNombre() + "ha sido añadido ");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
            System.out.println("Mensaje clase VeterinarioDAO - Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase VeterinarioDAO - Error desconocido: " + e.getMessage());
        }

    }

    /**
     * Método que modifica los datos del veterinario/a indicado en la base de
     * datos.
     *
     * @param veterinario el cliente con los datos modificados
     */
    public void modificarVeterinario(Veterinario veterinario) {
        try {
            String sql = "UPDATE veterinario SET especialidad=?, salario=?, nombre=?, apellido_uno=?, apellido_dos=?, DNI=?, fecha_nac=?, direccion=?, cp=?, telefono=?, email=?, turno=?  WHERE ID_veterinario=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, veterinario.getEspecialidad());
            statement.setBigDecimal(2, veterinario.getSalario());
            statement.setString(3, veterinario.getNombre());
            statement.setString(4, veterinario.getApellidoUno());
            statement.setString(5, veterinario.getApellidoDos());
            statement.setString(6, veterinario.getDni());
            statement.setDate(7, veterinario.getFechaNac());
            statement.setString(8, veterinario.getDireccion());
            statement.setInt(9, veterinario.getCp());
            statement.setInt(10, veterinario.getTelefono());
            statement.setString(11, veterinario.getEmail());
            statement.setString(12, veterinario.getTurno().name());
            statement.setInt(13, veterinario.getIdVeterinario());
            statement.executeUpdate();

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Modificar Veterinario/a");
            alerta.setHeaderText("Veterinario/a modificado/a");
            alerta.setContentText("El veterinario/a " + veterinario.getNombre() + " ha sido modificado/a correctamente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

}
