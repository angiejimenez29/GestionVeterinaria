package controlador.auxiliar;

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
import modelo.Auxiliar;
import modelo.dao.AuxiliarDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de agregar auxiliares, la cual permite agregar
 * un nuevo auxiliar a la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class AgregarAuxiliarController {

    @FXML
    private TextField field_dniAuxiliar;
    @FXML
    private TextField field_especialidadAuxiliar;
    @FXML
    private TextField field_salarioAuxiliar;
    @FXML
    private TextField field_nombreAuxiliar;
    @FXML
    private TextField field_apellidoUnoAuxiliar;
    @FXML
    private TextField field_apellidoDosAuxiliar;
    @FXML
    private DatePicker fechanacAuxiliar;
    @FXML
    private ComboBox<Auxiliar.Turno> turnoAuxiliar;
    @FXML
    private TextField field_telefonoAuxiliar;
    @FXML
    private TextField field_direccionAuxiliar;
    @FXML
    private TextField field_cpAuxiliar;
    @FXML
    private TextField field_correoAuxiliar;

    public AuxiliarDAO auxiliarDAO;

    public AgregarAuxiliarController() {
        this.auxiliarDAO = new AuxiliarDAO();
    }

    /**
     * Inicializa los elementos de la vista, como el ComboBox de turno.
     */
    public void initialize() {
        ObservableList<Auxiliar.Turno> opciones = FXCollections.observableArrayList(Auxiliar.Turno.values());
        turnoAuxiliar.setItems(opciones);
    }

    /**
     * Controlador de la ventana de Agregar auxiliares. Este controlador maneja
     * los eventos de la ventana de agregar auxiliares y realiza la acción de
     * agregarlos a través de la interacción con la base de datos mediante el
     * objeto AuxiliarDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws ClassNotFoundException si la clase AuxiliarDAO no se encuentra en
     * el classpath.
     */
    public void handleAgregarAuxiliares(ActionEvent event) throws ClassNotFoundException {

        // Verificar si algún campo está vacío
        if (field_dniAuxiliar.getText().isEmpty()
                || field_especialidadAuxiliar.getText().isEmpty()
                || field_salarioAuxiliar.getText().isEmpty()
                || field_nombreAuxiliar.getText().isEmpty()
                || field_apellidoUnoAuxiliar.getText().isEmpty()
                || field_apellidoDosAuxiliar.getText().isEmpty()
                || fechanacAuxiliar.getValue() == null
                || turnoAuxiliar.getValue() == null
                || field_telefonoAuxiliar.getText().isEmpty()
                || field_direccionAuxiliar.getText().isEmpty()
                || field_cpAuxiliar.getText().isEmpty()
                || field_correoAuxiliar.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar auxiliar");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene todos los campos obligatorios");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        // si la fecha no es válida
        if (!Utils.validarFechaDatePicker(fechanacAuxiliar)) {
            Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
            return;
        }

        String dni = field_dniAuxiliar.getText();
        String especialidad = field_especialidadAuxiliar.getText();
        String salario = field_salarioAuxiliar.getText();
        String nombre = field_nombreAuxiliar.getText();
        String apellidoUno = field_apellidoUnoAuxiliar.getText();
        String apellidoDos = field_apellidoDosAuxiliar.getText();
        LocalDate fechaNac = fechanacAuxiliar.getValue();
        Auxiliar.Turno turno = turnoAuxiliar.getValue();
        String telefono = field_telefonoAuxiliar.getText();
        String direccion = field_direccionAuxiliar.getText();
        String cp = field_cpAuxiliar.getText();
        String correo = field_correoAuxiliar.getText();

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
            if (auxiliarDAO.auxiliarExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al agregar auxiliar");
                alerta.setHeaderText("El auxiliar está registrado");
                alerta.setContentText("El auxiliar con el DNI " + dni + " está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();

            } else {
                // Agregar el auxiliar si no existe previamente
                Auxiliar auxiliar = new Auxiliar();

                auxiliar.setDni(dni);
                auxiliar.setEspecialidad(especialidad);
                auxiliar.setSalario(salarioDecimal);
                auxiliar.setNombre(nombre);
                auxiliar.setApellidoUno(apellidoUno);
                auxiliar.setApellidoDos(apellidoDos);
                auxiliar.setFechaNac(java.sql.Date.valueOf(fechaNac));
                auxiliar.setTurno(Auxiliar.Turno.valueOf(turno.toString()));
                auxiliar.setTelefono(telefonoInt);
                auxiliar.setDireccion(direccion);
                auxiliar.setCp(cpInt);
                auxiliar.setEmail(correo);
                auxiliarDAO.agregarAuxiliar(auxiliar);
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
     * Agregar Auxiliar.
     */
    @FXML
    public void limpiarCampos() {
        field_dniAuxiliar.setText("");
        field_especialidadAuxiliar.setText("");
        field_salarioAuxiliar.setText("");
        field_nombreAuxiliar.setText("");
        field_apellidoUnoAuxiliar.setText("");
        field_apellidoDosAuxiliar.setText("");
        fechanacAuxiliar.setValue(null);
        turnoAuxiliar.setValue(null);
        field_telefonoAuxiliar.setText("");
        field_direccionAuxiliar.setText("");
        field_cpAuxiliar.setText("");
        field_correoAuxiliar.setText("");
    }

}
