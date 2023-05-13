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
import modelo.Cliente;
import static modelo.dao.conexion.Conexion.connection;
import utils.Alertas;

/**
 * Clase ClienteDAO Esta clase representa un objeto DAO (Data Access Object)
 * para la entidad Cliente, que se encarga de realizar operaciones de lectura,
 * escritura, actualización y eliminación de registros de clientes en la base de
 * datos.
 *
 * @author Nuria Vázquez 
 */
public class ClienteDAO {

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
    public ClienteDAO() {

    }

    /**
     * Método que obtiene una lista de todos los clientes almacenados en la base
     * de datos.
     *
     * @return una lista de objetos Cliente
     */
    public List<Cliente> obtenerClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        try {

            String sql = "SELECT * FROM cliente";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("ID_cliente"));
                cliente.setSeguro(resultSet.getString("seguro"));
                cliente.setDni(resultSet.getString("DNI"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellidoUno(resultSet.getString("apellido_uno"));
                cliente.setApellidoDos(resultSet.getString("apellido_dos"));
                cliente.setFechaNac(resultSet.getDate("fecha_nac"));
                cliente.setTelefono(resultSet.getInt("telefono"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setCp(resultSet.getInt("cp"));
                cliente.setPoblacion(resultSet.getString("poblacion"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setSuscripcion(Cliente.Suscripcion.valueOf(resultSet.getString("suscripcion")));

                listaClientes.add(cliente);

            }
        } catch (SQLException e) {
            System.out.println("Clase ClienteDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase ClienteDAO - Error desconocido: " + e.getMessage());

        }

        return listaClientes;
    }

    /**
     * Método que permite buscar clientes en la base de datos a partir del DNI
     * del cliente.
     *
     * @param dniCliente DNI del cliente que se utilizará para buscar los
     * clientes asociados.
     * @return lista de clientes asociados al cliente cuyo DNI se ha
     * proporcionado.
     */
    public List<Cliente> buscarCliente(String dniCliente) {
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM cliente WHERE DNI LIKE ?");
            statement.setString(1, "%" + dniCliente + "%");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("ID_cliente"));
                cliente.setSeguro(resultSet.getString("seguro"));
                cliente.setDni(resultSet.getString("DNI"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellidoUno(resultSet.getString("apellido_uno"));
                cliente.setApellidoDos(resultSet.getString("apellido_dos"));
                cliente.setFechaNac(resultSet.getDate("fecha_nac"));
                cliente.setTelefono(resultSet.getInt("telefono"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setCp(resultSet.getInt("cp"));
                cliente.setPoblacion(resultSet.getString("poblacion"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setSuscripcion(Cliente.Suscripcion.valueOf(resultSet.getString("suscripcion")));

                listaClientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar cliente: " + ex.getMessage());
        }
        return listaClientes;
    }

    /**
     * Método que agrega un nuevo cliente a la base de datos.
     *
     * @param cliente el cliente a agregar
     */
    public void agregarCliente(Cliente cliente) {

        ResultSet rs = null;
        try {
            // Se define la consulta SQL con parámetros "?"
            String sql = "INSERT INTO cliente (seguro, DNI, nombre, apellido_uno, apellido_dos, fecha_nac, telefono,direccion, cp,poblacion,email, suscripcion) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?)";
            // Se prepara el statement con la consulta SQL
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Se establecen los valores de los parámetros de la consulta
            statement.setString(1, cliente.getSeguro());
            statement.setString(2, cliente.getDni());
            statement.setString(3, cliente.getNombre());
            statement.setString(4, cliente.getApellidoUno());
            statement.setString(5, cliente.getApellidoDos());
            statement.setDate(6, cliente.getFechaNac());
            statement.setInt(7, cliente.getTelefono());
            statement.setString(8, cliente.getDireccion());
            statement.setInt(9, cliente.getCp());
            statement.setString(10, cliente.getPoblacion());
            statement.setString(11, cliente.getEmail());
            statement.setString(12, cliente.getSuscripcion().name());
            statement.executeUpdate();

            // Obtener el ID generado automáticamente
            rs = statement.getGeneratedKeys();
            if (rs.next()) {
                cliente.setIdCliente(rs.getInt(1));
            }

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Cliente añadido");
            alerta.setContentText("El cliente " + cliente.getNombre() + " ha sido añadido ");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
            System.out.println("Mensaje clase ClienteDAO - Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase ClienteDAO - Error desconocido: " + e.getMessage());
        }

    }

    /**
     * Método que elimina la mascota indicada de la base de datos.
     *
     * @param cliente la mascota a eliminar
     */
    public void eliminarCliente(Cliente cliente) {
        try {
            String sql = "DELETE FROM cliente WHERE ID_cliente = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cliente.getIdCliente());
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Se ha eliminado correctamente el cliente con id: " + cliente.getIdCliente());
            } else {
                System.out.println("No se ha eliminado ningún cliente");
            }
        } catch (SQLException e) {
            System.out.println("Clase ClienteDAO Resultado de la excepción: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Mensaje clase ClienteDAO - Error desconocido: " + e.getMessage());
        }
    }

    /**
     * Método que modifica los datos del cliente indicado en la base de datos.
     *
     * @param cliente el cliente con los datos modificados
     */
    public void modificarCliente(Cliente cliente) {
        try {
            String sql = "UPDATE cliente SET seguro=?, DNI=?, nombre=?, apellido_uno=?, apellido_dos=?, fecha_nac=?, telefono=?,direccion=?, cp=?,poblacion=?,email=?, suscripcion=?  WHERE ID_cliente=?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, cliente.getSeguro());
            statement.setString(2, cliente.getDni());
            statement.setString(3, cliente.getNombre());
            statement.setString(4, cliente.getApellidoUno());
            statement.setString(5, cliente.getApellidoDos());
            statement.setDate(6, cliente.getFechaNac());
            statement.setInt(7, cliente.getTelefono());
            statement.setString(8, cliente.getDireccion());
            statement.setInt(9, cliente.getCp());
            statement.setString(10, cliente.getPoblacion());
            statement.setString(11, cliente.getEmail());
            statement.setString(12, cliente.getSuscripcion().name());
            statement.setInt(13, cliente.getIdCliente());

            statement.executeUpdate();

            // Mostrar mensaje de éxito
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Exito");
            alerta.setHeaderText("Cliente modificado");
            alerta.setContentText("El cliente " + cliente.getNombre() + " ha sido modificado ");
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
