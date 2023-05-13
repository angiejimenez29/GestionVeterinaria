package controlador.veterinario;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Veterinario;
import modelo.dao.VeterinarioDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de agregar veterinarios/as, la cual permite
 * agregar un nuevo veterinario/a a la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class AgregarVeterinarioController {

    @FXML
    private TextField field_dniVeterinario;
    @FXML
    private TextField field_especialidadVeterinario;
    @FXML
    private TextField field_salarioVeterinario;
    @FXML
    private TextField field_nombreVeterinario;
    @FXML
    private TextField field_apellidoUnoVeterinario;
    @FXML
    private TextField field_apellidoDosVeterinario;
    @FXML
    private DatePicker fechanacVeterinario;
    @FXML
    private ComboBox<Veterinario.Turno> turnoVeterinario;
    @FXML
    private TextField field_telefonoVeterinario;
    @FXML
    private TextField field_direccionVeterinario;
    @FXML
    private TextField field_cpVeterinario;
    @FXML
    private TextField field_correoVeterinario;

    public VeterinarioDAO veterinarioDAO;

    public AgregarVeterinarioController() {
        this.veterinarioDAO = new VeterinarioDAO();
    }

    /**
     * Inicializa los elementos de la vista, como el ComboBox de turno.
     */
    public void initialize() {
        ObservableList<Veterinario.Turno> opciones = FXCollections.observableArrayList(Veterinario.Turno.values());
        turnoVeterinario.setItems(opciones);
    }

    /**
     * Controlador de la ventana de Agregar veterinarios/as. Este controlador
     * maneja los eventos de la ventana de agregar veterinarios/as y realiza la
     * acción de agregarlos a través de la interacción con la base de datos
     * mediante el objeto VeterinarioDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws ClassNotFoundException si la clase VeterinarioDAO no se encuentra
     * en el classpath.
     */
    public void handleAgregarVeterinarios(ActionEvent event) throws ClassNotFoundException {


        // Verificar si algún campo está vacío
        if (field_dniVeterinario.getText().isEmpty()
                || field_especialidadVeterinario.getText().isEmpty()
                || field_salarioVeterinario.getText().isEmpty()
                || field_nombreVeterinario.getText().isEmpty()
                || field_apellidoUnoVeterinario.getText().isEmpty()
                || field_apellidoDosVeterinario.getText().isEmpty()
                || fechanacVeterinario.getValue() == null
                || turnoVeterinario.getValue() == null
                || field_telefonoVeterinario.getText().isEmpty()
                || field_direccionVeterinario.getText().isEmpty()
                || field_cpVeterinario.getText().isEmpty()
                || field_correoVeterinario.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar veterinario/a");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene todos los campos obligatorios");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        // si la fecha no es válida
        if (!Utils.validarFechaDatePicker(fechanacVeterinario)) {
            Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
            return;
        }

        String dni = field_dniVeterinario.getText();
        String especialidad = field_especialidadVeterinario.getText();
        String salario = field_salarioVeterinario.getText();
        String nombre = field_nombreVeterinario.getText();
        String apellidoUno = field_apellidoUnoVeterinario.getText();
        String apellidoDos = field_apellidoDosVeterinario.getText();
        LocalDate fechaNac = fechanacVeterinario.getValue();
        Veterinario.Turno turno = turnoVeterinario.getValue();
        String telefono = field_telefonoVeterinario.getText();
        String direccion = field_direccionVeterinario.getText();
        String cp = field_cpVeterinario.getText();
        String correo = field_correoVeterinario.getText();

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

        if (!Utils.validarSalario(salario)) {
            Alertas.alertSalarioIncorrecto("Debes introducir un salario con sólo 2 decimales o ninguno");
            return;
        }

        // Validar que el veterinario/a existe en la base de datos
        try {
            BigDecimal salarioDecimal = new BigDecimal(salario);
            int telefonoInt = Integer.parseInt(telefono);
            int cpInt = Integer.parseInt(cp);
            // Verificar si el cliente existe en la base de datos
            if (veterinarioDAO.veterinarioExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al agregar veterinario/a");
                alerta.setHeaderText("El/la veterinario/a está registrado");
                alerta.setContentText("El/la veterinario/a con el DNI " + dni + " está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();

            } else {
                // Agregar el veterinario/a si no existe previamente
                Veterinario veterinario = new Veterinario();

                veterinario.setDni(dni);
                veterinario.setEspecialidad(especialidad);
                veterinario.setSalario(salarioDecimal);
                veterinario.setNombre(nombre);
                veterinario.setApellidoUno(apellidoUno);
                veterinario.setApellidoDos(apellidoDos);
                veterinario.setFechaNac(java.sql.Date.valueOf(fechaNac));
                veterinario.setTurno(Veterinario.Turno.valueOf(turno.toString()));
                veterinario.setTelefono(telefonoInt);
                veterinario.setDireccion(direccion);
                veterinario.setCp(cpInt);
                veterinario.setEmail(correo);
                veterinarioDAO.agregarVeterinario(veterinario);
                limpiarCampos();

            }

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        } catch (NumberFormatException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Error en campos numéricos");
            alerta.setContentText("Debe introducir un número entero para telefono y CP y decimal para el salario");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

    }

    /**
     * Este método se encarga de borrar los campos de texto en la ventana de
     * Agregar Veterinario/a.
     */
    @FXML
    public void limpiarCampos() {
        field_dniVeterinario.setText("");
        field_especialidadVeterinario.setText("");
        field_salarioVeterinario.setText("");
        field_nombreVeterinario.setText("");
        field_apellidoUnoVeterinario.setText("");
        field_apellidoDosVeterinario.setText("");
        fechanacVeterinario.setValue(null);
        turnoVeterinario.setValue(null);
        field_telefonoVeterinario.setText("");
        field_direccionVeterinario.setText("");
        field_cpVeterinario.setText("");
        field_correoVeterinario.setText("");
    }
}
