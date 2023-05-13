package controlador.cliente;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.Cliente.Suscripcion;
import modelo.dao.ClienteDAO;

/**
 * Esta clase controla la vista de obtener un listado de clientes, la cual
 * permite la visualización en la ventana de los clientes guardados en a basee
 * de datos.
 *
 * @author Nuria Vázquez 
 */
public class ListadoClienteController {

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
    private TableColumn<Cliente, Suscripcion> colSuscripcion;

    public ClienteDAO clienteDAO;

    public ListadoClienteController() {
    }

    public ListadoClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    /**
     * Controlador de la ventana de listado de clientes.Este controlador maneja
     * los eventos de la ventana de obtener a los clientes a través de la
     * interacción con la base de datos mediante el objeto ClienteDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.lang.ClassNotFoundException
     */
    public void handleObtenerClientes(ActionEvent event) throws ClassNotFoundException {

        try {
            clienteDAO = new ClienteDAO();
            List<Cliente> clientes = clienteDAO.obtenerClientes();
            ObservableList<Cliente> clienteData = FXCollections.observableArrayList(clientes);
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

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error con cliente");
            alerta.setHeaderText("Ha ocurrido un error con los clientes");
            alerta.setContentText("No se han podido mostrar todos los clientes.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }

    }

}
