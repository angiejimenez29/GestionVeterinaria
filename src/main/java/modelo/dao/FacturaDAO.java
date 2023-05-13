package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cita;
import static modelo.dao.conexion.Conexion.connection;

/**
 * Clase FacturaDAO encargado de crear una factura buscando una cita asignada a
 * un cliente.
 *
 * @author Nuria Vázquez 
 */
public class FacturaDAO {

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
    public FacturaDAO() {

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
            statement = connection.prepareStatement("SELECT cita.ID_cita, cita.fecha, cita.hora, cita.tipo_consulta, cita.diagnostico, cita.tratamiento, cita.medicacion, cita.precio, veterinario.nombre, veterinario.apellido_uno, \n"
                    + "    veterinario.apellido_dos, mascota.nombre AS nombre_mascota,cliente.nombre AS nombre_cliente,cliente.apellido_uno AS apellido_uno_cliente,\n"
                    + "    cliente.apellido_dos AS apellido_dos_cliente, cliente.DNI AS dni_cliente\n"
                    + "FROM cita\n"
                    + "INNER JOIN cliente ON cita.ID_cliente = cliente.ID_cliente\n"
                    + "INNER JOIN mascota ON cita.ID_mascota = mascota.ID_mascota\n"
                    + "INNER JOIN veterinario ON cita.ID_veterinario = veterinario.ID_veterinario\n"
                    + "WHERE cliente.DNI = ?;");

            statement.setString(1, dniCliente);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Cita cita = new Cita();

                cita.setIdCita(resultSet.getInt("ID_cita"));
                cita.setNombreVeterinario(resultSet.getString("nombre"));
                cita.setApellidoUnoVeterinario(resultSet.getString("apellido_uno"));
                cita.setApellidoDosVeterinario(resultSet.getString("apellido_dos"));
                cita.setDniCliente(resultSet.getString("dni_cliente"));
                cita.setNombreCliente(resultSet.getString("nombre_cliente"));
                cita.setApellidoUnoCliente(resultSet.getString("apellido_uno_cliente"));
                cita.setApellidoDosCliente(resultSet.getString("apellido_dos_cliente"));
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

}
