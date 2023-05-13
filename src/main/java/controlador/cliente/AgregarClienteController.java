package controlador.cliente;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cliente;
import modelo.dao.ClienteDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de agregar clientes, la cual permite agregar un
 * nuevo cliente a la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class AgregarClienteController {

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

    public ClienteDAO clienteDAO;

    public AgregarClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Inicializa los elementos de la vista, como el ComboBox de suscripción.
     */
    public void initialize() {
        ObservableList<Cliente.Suscripcion> opciones = FXCollections.observableArrayList(Cliente.Suscripcion.values());
        suscripcionCliente.setItems(opciones);
    }

    /**
     * Controlador de la ventana de Agregar clientes. Este controlador maneja
     * los eventos de la ventana de agregar clientes y realiza la acción de
     * agregarlos a través de la interacción con la base de datos mediante el
     * objeto ClienteDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws ClassNotFoundException si la clase ClienteDAO no se encuentra en
     * el classpath.
     */
    public void handleAgregarClientes(ActionEvent event) throws ClassNotFoundException {

        // Verificar si algún campo está vacío
        if (field_nombreCliente.getText().isEmpty()
                || field_apellidoUnoCliente.getText().isEmpty()
                || field_apellidoDosCliente.getText().isEmpty()
                || field_dniCliente.getText().isEmpty()
                || fechanacCliente.getValue() == null
                || field_seguroCliente.getText().isEmpty()
                || field_telefonoCliente.getText().isEmpty()
                || field_direccionCliente.getText().isEmpty()
                || field_cpCliente.getText().isEmpty()
                || field_PoblacionCliente.getText().isEmpty()
                || field_correoCliente.getText().isEmpty()
                || suscripcionCliente.getValue() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar cliente");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene todos los campos obligatorios");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        // si la fecha no es válida
        if (!Utils.validarFechaDatePicker(fechanacCliente)) {
            Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
            return;
        }

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

        // Validar que el cliente existe en la base de datos
        try {
            int telefonoInt = Integer.parseInt(telefono);
            int cpInt = Integer.parseInt(cp);
            // Verificar si el cliente existe en la base de datos
            if (clienteDAO.clienteExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al agregar cliente");
                alerta.setHeaderText("El cliente está registrado");
                alerta.setContentText("El cliente con el DNI " + dni + " está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();

            } else {
                // Agregar el cliente si no existe previamente
                Cliente cliente = new Cliente();
                cliente.setNombre(nombre);
                cliente.setApellidoUno(apellidoUno);
                cliente.setApellidoDos(apellidoDos);
                cliente.setDni(dni);
                cliente.setFechaNac(java.sql.Date.valueOf(fechaNac));
                cliente.setSeguro(seguro);
                cliente.setTelefono(telefonoInt);
                cliente.setDireccion(direccion);
                cliente.setCp(cpInt);
                cliente.setPoblacion(poblacion);
                cliente.setEmail(correo);
                cliente.setSuscripcion(Cliente.Suscripcion.valueOf(suscripcion.toString()));
                clienteDAO.agregarCliente(cliente);
                limpiarCampos();

            }

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(AlertType.ERROR);
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
     * Agregar Cliente.
     */
    @FXML
    public void limpiarCampos() {
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
