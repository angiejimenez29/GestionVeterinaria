package controlador.cliente;

import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.dao.ClienteDAO;
import utils.Alertas;
import utils.Utils;

/**
 *
 * Esta clase controla la vista de la búsqueda de clientes.
 *
 * @author Nuria Vázquez 
 */
public class BuscarClienteController {

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, Integer> colIdCliente;
    @FXML
    private TableColumn<Cliente, String> colDNI;
    @FXML
    private TableColumn<Cliente, String> colSeguroCliente;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellidoUno;
    @FXML
    private TableColumn<Cliente, String> colApellidoDos;
    @FXML
    private TableColumn<Cliente, java.sql.Date> colFechaNacimiento;
    @FXML
    private TableColumn<Cliente, Integer> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, Integer> colCP;
    @FXML
    private TableColumn<Cliente, String> colPoblacion;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TableColumn<Cliente, Cliente.Suscripcion> colSuscripcion;

    @FXML
    private TextField buscarCliente;

    public ClienteDAO clienteDAO;

    /**
     * Controlador de la ventana de búsqueda de clientes. Este controlador
     * maneja los eventos de la ventana de búsqueda de clientes y realiza la
     * búsqueda de clientes a través de la interacción con la base de datos
     * mediante el objeto ClienteDAO. Los resultados de la búsqueda se muestran
     * en la tabla de la ventana de búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarClientes(ActionEvent event) {

        // si el campo esta vacio
        if (buscarCliente.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar al cliente");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del cliente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniCliente = buscarCliente.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {

            clienteDAO = new ClienteDAO();
            // si el cliente existe en la base de datos
            if (clienteDAO.clienteExiste(dniCliente)) {
                List<Cliente> listaClientes = clienteDAO.buscarCliente(dniCliente);
                ObservableList<Cliente> clienteData = FXCollections.observableArrayList(listaClientes);

                tablaClientes.setItems(clienteData);
                colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
                colDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
                colSeguroCliente.setCellValueFactory(new PropertyValueFactory<>("seguro"));
                colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colApellidoUno.setCellValueFactory(new PropertyValueFactory<>("apellidoUno"));
                colApellidoDos.setCellValueFactory(new PropertyValueFactory<>("apellidoDos"));
                colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
                colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
                colCP.setCellValueFactory(new PropertyValueFactory<>("cp"));
                colPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
                colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                colSuscripcion.setCellValueFactory(new PropertyValueFactory<>("suscripcion"));

                // ajustar el ancho de las columnas al contenido
                tablaClientes.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

                TableColumn[] columnas = {colIdCliente, colDNI, colSeguroCliente, colNombre, colApellidoUno, colApellidoDos, colFechaNacimiento, colTelefono, colDireccion, colCP, colPoblacion, colEmail, colSuscripcion};
                double[] anchos = {0.05, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.08, 0.7, 0.7, 0.05};

                for (int i = 0; i < columnas.length; i++) {
                    columnas[i].prefWidthProperty().bind(tablaClientes.widthProperty().multiply(anchos[i]));
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar cliente");
                alerta.setHeaderText("Cliente no encontrado");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no está registrado en la base de datos.");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
            }

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }

    }
}
