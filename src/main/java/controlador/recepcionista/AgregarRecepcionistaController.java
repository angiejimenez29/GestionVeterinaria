package controlador.recepcionista;

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
import modelo.Recepcionista;
import modelo.dao.RecepcionistaDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de agregar recepcionistas, la cual permite
 * agregar un nuevo recepcionista a la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class AgregarRecepcionistaController {

    @FXML
    private TextField field_dniRecepcionista;
    @FXML
    private TextField field_salarioRecepcionista;
    @FXML
    private TextField field_nombreRecepcionista;
    @FXML
    private TextField field_apellidoUnoRecepcionista;
    @FXML
    private TextField field_apellidoDosRecepcionista;
    @FXML
    private DatePicker fechanacRecepcionista;
    @FXML
    private ComboBox<Recepcionista.Turno> turnoRecepcionista;
    @FXML
    private TextField field_telefonoRecepcionista;
    @FXML
    private TextField field_direccionRecepcionista;
    @FXML
    private TextField field_cpRecepcionista;
    @FXML
    private TextField field_correoRecepcionista;

    public RecepcionistaDAO recepcionistaDAO;

    public AgregarRecepcionistaController() {
        this.recepcionistaDAO = new RecepcionistaDAO();
    }

    /**
     * Inicializa los elementos de la vista, como el ComboBox de turno.
     */
    public void initialize() {
        ObservableList<Recepcionista.Turno> opciones = FXCollections.observableArrayList(Recepcionista.Turno.values());
        turnoRecepcionista.setItems(opciones);
    }

    /**
     * Controlador de la ventana de Agregar recepcionistas. Este controlador
     * maneja los eventos de la ventana de agregar recepcionistas y realiza la
     * acción de agregarlos a través de la interacción con la base de datos
     * mediante el objeto RecepcionistaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws ClassNotFoundException si la clase RecepcionistaDAO no se
     * encuentra en el classpath.
     */
    public void handleAgregarRecepcionistas(ActionEvent event) throws ClassNotFoundException {

        // Verificar si algún campo está vacío
        if (field_dniRecepcionista.getText().isEmpty()
                || field_salarioRecepcionista.getText().isEmpty()
                || field_nombreRecepcionista.getText().isEmpty()
                || field_apellidoUnoRecepcionista.getText().isEmpty()
                || field_apellidoDosRecepcionista.getText().isEmpty()
                || fechanacRecepcionista.getValue() == null
                || turnoRecepcionista.getValue() == null
                || field_telefonoRecepcionista.getText().isEmpty()
                || field_direccionRecepcionista.getText().isEmpty()
                || field_cpRecepcionista.getText().isEmpty()
                || field_correoRecepcionista.getText().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar recepcionista");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene todos los campos obligatorios");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        // si la fecha no es válida
        if (!Utils.validarFechaDatePicker(fechanacRecepcionista)) {
            Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
            return;
        }

        String dni = field_dniRecepcionista.getText();
        String salario = field_salarioRecepcionista.getText();
        String nombre = field_nombreRecepcionista.getText();
        String apellidoUno = field_apellidoUnoRecepcionista.getText();
        String apellidoDos = field_apellidoDosRecepcionista.getText();
        LocalDate fechaNac = fechanacRecepcionista.getValue();
        Recepcionista.Turno turno = turnoRecepcionista.getValue();
        String telefono = field_telefonoRecepcionista.getText();
        String direccion = field_direccionRecepcionista.getText();
        String cp = field_cpRecepcionista.getText();
        String correo = field_correoRecepcionista.getText();

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
            // Verificar si el recepcionista existe en la base de datos
            if (recepcionistaDAO.recepcionistaExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al agregar recepcionista");
                alerta.setHeaderText("El recepcionista está registrado");
                alerta.setContentText("El recepcionista con el DNI " + dni + " está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();

            } else {
                // Agregar el recepcionista si no existe previamente
                Recepcionista recepcionista = new Recepcionista();

                recepcionista.setDni(dni);
                recepcionista.setSalario(salarioDecimal);
                recepcionista.setNombre(nombre);
                recepcionista.setApellidoUno(apellidoUno);
                recepcionista.setApellidoDos(apellidoDos);
                recepcionista.setFechaNac(java.sql.Date.valueOf(fechaNac));
                recepcionista.setTurno(Recepcionista.Turno.valueOf(turno.toString()));
                recepcionista.setTelefono(telefonoInt);
                recepcionista.setDireccion(direccion);
                recepcionista.setCp(cpInt);
                recepcionista.setEmail(correo);
                recepcionistaDAO.agregarRecepcionista(recepcionista);
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
     * Agregar recepcionista.
     */
    @FXML
    public void limpiarCampos() {
        field_dniRecepcionista.setText("");
        field_salarioRecepcionista.setText("");
        field_nombreRecepcionista.setText("");
        field_apellidoUnoRecepcionista.setText("");
        field_apellidoDosRecepcionista.setText("");
        fechanacRecepcionista.setValue(null);
        turnoRecepcionista.setValue(null);
        field_telefonoRecepcionista.setText("");
        field_direccionRecepcionista.setText("");
        field_cpRecepcionista.setText("");
        field_correoRecepcionista.setText("");
    }

}
