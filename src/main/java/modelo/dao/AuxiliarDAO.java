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
import modelo.Auxiliar;
import static modelo.dao.conexion.Conexion.connection;
import utils.Alertas;

/**
 * Clase AuxiliarDAO Esta clase representa un objeto DAO (Data Access Object)
 * para la entidad Auxiliar, que se encarga de realizar operaciones de lectura,
 * escritura, actualización y eliminación de registros de auxiliares en la base
 * de datos.
 *
 * @author Nuria Vázquez 
 */
public class AuxiliarDAO {

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
    public AuxiliarDAO() {

    }

    /**
     * Método que obtiene una lista de todos los auxiliares almacenados en la
     * base de datos.
     *
     * @return una lista de objetos Auxiliar
     */
    public List<Auxiliar> obtenerAuxiliares() {
        List<Auxiliar> listaAuxiliares = new ArrayList<>();
        try {

            String sql = "SELECT * FROM auxiliar";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Auxiliar auxiliar = new Auxiliar();

                auxiliar.setIdAuxiliar(resultSet.getInt("ID_auxiliar"));
                auxiliar.setEspecialidad(resultSet.getString("especialidad"));
                auxiliar.setSalario(resultSet.getBigDecimal("salario"));
                auxiliar.setNombre(resultSet.getString("nombre"));
                auxiliar.setApellidoUno(resultSet.getString("apellido_uno"));
                auxiliar.setApellidoDos(resultSet.getString("apellido_dos"));
                auxiliar.setDni(resultSet.getString("DNI"));
                auxiliar.setFechaNac(resultSet.getDate("fecha_nac"));
                auxiliar.setDireccion(resultSet.getString("direccion"));
                auxiliar.setCp(resultSet.getInt("cp"));
                auxiliar.setTelefono(resultSet.getInt("telefono"));
                auxiliar.setEmail(resultSet.getString("email"));
                auxiliar.setTurno(Auxiliar.Turno.valueOf(resultSet.getString("turno")));

                listaAuxiliares.add(auxiliar);

            }
        } catch (SQLException e) {
            System.out.println("Clase AuxiliarDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase AuxiliarDAO - Error desconocido: " + e.getMessage());
        }

        return listaAuxiliares;
    }

    /**
     * Método que permite buscar auxiliares en la base de datos a partir del DNI
     * del auxiliar.
     *
     * @param dniAuxiliar DNI del auxiliar que se utilizará para buscar los
     * auxiliares asociados.
     * @return lista de auxiliares asociados al auxiliar cuyo DNI se ha
     * proporcionado.
     */
    public List<Auxiliar> buscarAuxiliares(String dniAuxiliar) {
        List<Auxiliar> listaAuxiliares = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM auxiliar WHERE DNI LIKE ?");
            statement.setString(1, "%" + dniAuxiliar + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Auxiliar auxiliar = new Auxiliar();

                auxiliar.setIdAuxiliar(resultSet.getInt("ID_auxiliar"));
                auxiliar.setEspecialidad(resultSet.getString("especialidad"));
                auxiliar.setSalario(resultSet.getBigDecimal("salario"));
                auxiliar.setNombre(resultSet.getString("nombre"));
                auxiliar.setApellidoUno(resultSet.getString("apellido_uno"));
                auxiliar.setApellidoDos(resultSet.getString("apellido_dos"));
                auxiliar.setDni(resultSet.getString("DNI"));
                auxiliar.setFechaNac(resultSet.getDate("fecha_nac"));
                auxiliar.setDireccion(resultSet.getString("direccion"));
                auxiliar.setCp(resultSet.getInt("cp"));
                auxiliar.setTelefono(resultSet.getInt("telefono"));
                auxiliar.setEmail(resultSet.getString("email"));
                auxiliar.setTurno(Auxiliar.Turno.valueOf(resultSet.getString("turno")));

                listaAuxiliares.add(auxiliar);

            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar auxiliar: " + ex.getMessage());
        }
        return listaAuxiliares;
    }

    /**
     * Método que agrega un nuevo auxiliar a la base de datos.
     *
     * @param auxiliar el auxiliar a agregar
     */
    public void agregarAuxiliar(Auxiliar auxiliar) {

        ResultSet rs = null;
        try {
            // Se define la consulta SQL con parámetros "?"
            String sql = "INSERT INTO auxiliar (especialidad, salario, nombre, apellido_uno, apellido_dos, DNI, fecha_nac, direccion, cp, telefono, email, turno) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
            // Se prepara el statement con la consulta SQL
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Se establecen los valores de los parámetros de la consulta
            statement.setString(1, auxiliar.getEspecialidad());
            statement.setBigDecimal(2, auxiliar.getSalario());
            statement.setString(3, auxiliar.getNombre());
            statement.setString(4, auxiliar.getApellidoUno());
            statement.setString(5, auxiliar.getApellidoDos());
            statement.setString(6, auxiliar.getDni());
            statement.setDate(7, auxiliar.getFechaNac());
            statement.setString(8, auxiliar.getDireccion());
            statement.setInt(9, auxiliar.getCp());
            statement.setInt(10, auxiliar.getTelefono());
            statement.setString(11, auxiliar.getEmail());
            statement.setString(12, auxiliar.getTurno().name());
            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                auxiliar.setIdAuxiliar(rs.getInt(1));
            }

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Auxiliar añadido");
            alerta.setContentText("El auxiliar ha sido añadido ");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
            System.out.println("Mensaje clase AuxiliarDAO - Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase AuxiliarDAO - Error desconocido: " + e.getMessage());
        }

    }

    /**
     * Método que elimina el auxiliar indidcado de la base de datos.
     *
     * @param auxiliar el veterinario/a a eliminar
     */
    public void eliminarAuxiliar(Auxiliar auxiliar) {
        try {
            String sql = "DELETE FROM auxiliar WHERE ID_auxiliar = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, auxiliar.getIdAuxiliar());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Auxiliar eliminado");
                alert.setHeaderText("Auxiliar eliminado");
                alert.setContentText("El auxiliar " + auxiliar.getDni() + " ha sido eliminado correctamente.");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.showAndWait();

                System.out.println("Se ha eliminado correctamente el auxiliar con id: " + auxiliar.getIdAuxiliar());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al eliminar auxiliar");
                alert.setHeaderText("Ha ocurrido un error al eliminar el auxiliar.");
                alert.setContentText("No se ha eliminado ningún auxiliar");
                Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.showAndWait();
                System.out.println("No se ha eliminado ningún auxiliar");
            }
        } catch (SQLException e) {
            System.out.println("Clase AuxiliarDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase AuxiliarDAO - Error desconocido: " + e.getMessage());
        }
    }

    /**
     * Método que modifica los datos del auxiliar indicado en la base de datos.
     *
     * @param auxiliar el auxilair con los datos modificados
     */
    public void modificarAuxiliar(Auxiliar auxiliar) {
        try {
            String sql = "UPDATE auxiliar SET especialidad=?, salario=?, nombre=?, apellido_uno=?, apellido_dos=?, DNI=?, fecha_nac=?, direccion=?, cp=?, telefono=?, email=?, turno=?  WHERE ID_auxiliar=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, auxiliar.getEspecialidad());
            statement.setBigDecimal(2, auxiliar.getSalario());
            statement.setString(3, auxiliar.getNombre());
            statement.setString(4, auxiliar.getApellidoUno());
            statement.setString(5, auxiliar.getApellidoDos());
            statement.setString(6, auxiliar.getDni());
            statement.setDate(7, auxiliar.getFechaNac());
            statement.setString(8, auxiliar.getDireccion());
            statement.setInt(9, auxiliar.getCp());
            statement.setInt(10, auxiliar.getTelefono());
            statement.setString(11, auxiliar.getEmail());
            statement.setString(12, auxiliar.getTurno().name());
            statement.setInt(13, auxiliar.getIdAuxiliar());
            statement.executeUpdate();

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Modificar Auxiliar");
            alerta.setHeaderText("Auxiliar modificado");
            alerta.setContentText("El auxiliar " + auxiliar.getNombre() + " ha sido modificado correctamente");
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
     * Este método comprueba si un auxiliar con el DNI proporcionado existe en
     * la base de datos.
     *
     * @param dniAuxiliar el DNI del auxiliar a buscar
     * @return true si el auxiliar existe en la base de datos, false de lo
     * contrario
     * @throws SQLException si ocurre algún error al ejecutar la consulta SQL
     *
     */
    public boolean auxiliarExiste(String dniAuxiliar) throws SQLException {
        String sql = "SELECT COUNT(*) FROM auxiliar WHERE DNI = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dniAuxiliar);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1) > 0;
            }

        }

    }
}
