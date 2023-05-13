package controlador.cliente;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.dao.ClienteDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de modificar clientes, la cual permite la
 * gestión de la modificación de clientes de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class ModificarClienteController {

    private ClienteDAO clienteDAO;

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
    private TextField field_nombreCliente;
    @FXML
    private TextField field_apellidoUnoCliente;
    @FXML
    private TextField field_apellidoDosCliente;
    @FXML
    private TextField field_dniCliente;
    @FXML
    private DatePicker fechanacCliente;
    @FXML
    private TextField field_seguroCliente;
    @FXML
    private TextField field_telefonoCliente;
    @FXML
    private TextField field_direccionCliente;
    @FXML
    private TextField field_cpCliente;
    @FXML
    private TextField field_PoblacionCliente;
    @FXML
    private TextField field_correoCliente;
    @FXML
    private ComboBox<Cliente.Suscripcion> suscripcionCliente;

    /**
     * Inicializa los elementos de la vista de cargando los clientes.
     */
    public void initialize() {
        ObservableList<Cliente.Suscripcion> opciones = FXCollections.observableArrayList(Cliente.Suscripcion.values());
        suscripcionCliente.setItems(opciones);
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
    }

    /**
     *
     * Maneja el evento de selección de un cliente en la tablaClientes.
     * Actualiza los campos de texto y los campos de selección en la vista para
     * mostrar la información del cliente seleccionado.
     *
     * @param event el evento de selección del mouse
     */
    @FXML
    private void seleccionar(MouseEvent event) {
        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado != null) {
            field_nombreCliente.setText(clienteSeleccionado.getNombre());
            field_apellidoUnoCliente.setText(clienteSeleccionado.getApellidoUno());
            field_apellidoDosCliente.setText(clienteSeleccionado.getApellidoDos());
            field_dniCliente.setText(clienteSeleccionado.getDni());
            fechanacCliente.setValue(clienteSeleccionado.getFechaNac().toLocalDate());
            field_seguroCliente.setText(clienteSeleccionado.getSeguro());
            field_telefonoCliente.setText(String.valueOf(clienteSeleccionado.getTelefono()));
            field_direccionCliente.setText(clienteSeleccionado.getDireccion());
            field_cpCliente.setText(String.valueOf(clienteSeleccionado.getCp()));
            field_PoblacionCliente.setText(clienteSeleccionado.getPoblacion());
            field_correoCliente.setText(clienteSeleccionado.getEmail());
            suscripcionCliente.setValue(clienteSeleccionado.getSuscripcion());
        }
    }

    /**
     * Controlador de la ventana de modificar cliente.Este controlador maneja
     * los eventos de la ventana de modificar los campos de los clientes a
     * través de la interacción con la base de datos mediante el objeto
     * ClienteDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.sql.SQLException
     */
    public void handleModificarClientes(ActionEvent event) throws SQLException {

        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        try {
            //obtengo los datos del formulario
            String nombre = field_nombreCliente.getText();
            String apellidoUno = field_apellidoUnoCliente.getText();
            String apellidoDos = field_apellidoDosCliente.getText();
            String dni = field_dniCliente.getText();
            LocalDate fechaNac = fechanacCliente.getValue();
            String seguro = field_seguroCliente.getText();
            String telefono = field_telefonoCliente.getText();
            String direccion = field_direccionCliente.getText();
            String cp = field_cpCliente.getText();
            String poblacion = field_PoblacionCliente.getText();
            String correo = field_correoCliente.getText();
            Cliente.Suscripcion suscripcion = suscripcionCliente.getValue();

            // los campos no son nulos ni vacios
            if (nombre == null || nombre.isEmpty()
                    || apellidoUno == null || apellidoUno.isEmpty()
                    || apellidoDos == null || apellidoDos.isEmpty()
                    || dni == null || dni.isEmpty()
                    || fechaNac == null
                    || seguro == null || seguro.isEmpty()
                    || telefono == null || telefono.isEmpty()
                    || direccion == null || direccion.isEmpty()
                    || cp == null || cp.isEmpty()
                    || poblacion == null || poblacion.isEmpty()
                    || correo == null || correo.isEmpty()
                    || suscripcion == null) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al modificar cliente");
                alerta.setHeaderText("Campos vacios");
                alerta.setContentText("Seleccione una fila o rellene todos los campos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            // si la fecha no es válida
            if (!Utils.validarFechaLocalDate(fechaNac)) {
                Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
                return;
            }

            // si el dni no tiene 8 números enteros y una letra en mayúsculas
            if (!Utils.validarDni(dni)) {
                Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
                return;
            }

            // Verifica que el teléfono no tiene 9 dígitos    
            if (!Utils.validarTelefono(telefono)) {
                Alertas.alertTelefonoIncorrecto("Debes introducir un teléfono válido con 9 dígitos");
                return;
            }

            // Verifica que el cp no tiene 5 dígitos    
            if (!Utils.validarCp(cp)) {
                Alertas.alertCpIncorrecto("Debes introducir un cp válido con 5 dígitos");
                return;
            }

            // Verifica que el correo no tiene el formato esperado  
            if (!Utils.validarEmail(correo)) {
                Alertas.alertEmailIncorrecto("Por favor, ingrese un correo electrónico válido.");
                return;
            }

            // Verificar si el cliente no existe en la base de datos
            if (!clienteDAO.clienteExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al agregar cliente");
                alerta.setHeaderText("El cliente no está registrado");
                alerta.setContentText("El cliente con el DNI " + dni + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;

            }
            int telefonoInt = Integer.parseInt(telefono);
            int cpInt = Integer.parseInt(cp);
            // Modificamos el cliente
            clienteSeleccionado.setNombre(nombre);
            clienteSeleccionado.setApellidoUno(apellidoUno);
            clienteSeleccionado.setApellidoDos(apellidoDos);
            clienteSeleccionado.setDni(dni);
            clienteSeleccionado.setFechaNac(java.sql.Date.valueOf(fechaNac));
            clienteSeleccionado.setSeguro(seguro);
            clienteSeleccionado.setTelefono(telefonoInt);
            clienteSeleccionado.setDireccion(direccion);
            clienteSeleccionado.setCp(cpInt);
            clienteSeleccionado.setPoblacion(poblacion);
            clienteSeleccionado.setEmail(correo);
            clienteSeleccionado.setSuscripcion(Cliente.Suscripcion.valueOf(suscripcion.toString()));
            clienteDAO.modificarCliente(clienteSeleccionado);
            limpiarCampos();
            tablaClientes.refresh();


        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Error en campos numéricos");
            alerta.setContentText("Debe introducir un número entero para telefono y CP ");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

    }

    /**
     * Este método se encarga de borrar los campos de texto en la ventana de
     * Eliminar Mascota.
     */
    private void limpiarCampos() {
        field_nombreCliente.setText("");
        field_apellidoUnoCliente.setText("");
        field_apellidoDosCliente.setText("");
        field_dniCliente.setText("");
        fechanacCliente.setValue(null);
        field_seguroCliente.setText("");
        field_telefonoCliente.setText("");
        field_direccionCliente.setText("");
        field_cpCliente.setText("");
        field_PoblacionCliente.setText("");
        field_correoCliente.setText("");
        suscripcionCliente.setValue(null);
    }

}
