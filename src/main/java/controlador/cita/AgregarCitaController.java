package controlador.cita;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.dao.CitaDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de agregar citas, la cual permite agregar una
 * nueva cita a la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class AgregarCitaController {

    @FXML
    private TextField field_diagnosticoCita;

    @FXML
    private TextField field_medicaciónCita;

    @FXML
    private TextField field_nombreMascota;

    @FXML
    private TextField field_tipoConsulta;

    @FXML
    private TextField field_tratamientoCita;

    @FXML
    private DatePicker fechaCita;

    @FXML
    private TextField field_horaCita;

    @FXML
    private TextField field_precioCita;

    @FXML
    private TextField field_nombreVeterinario;

    @FXML
    private TextField field_apellidoUnoVeterinario;

    @FXML
    private TextField field_apellidoDosVeterinario;

    @FXML
    private TextField field_dniClienteCita;

    @FXML
    private TextField field_nombreRecepcion;

    public CitaDAO citaDAO;

    public AgregarCitaController() {
        this.citaDAO = new CitaDAO();
    }

    /**
     * Controlador de la ventana de Agregar citas.Este controlador maneja los
     * eventos de la ventana de agregar citas y realiza la acción de agregarlos
     * a través de la interacción con la base de datos mediante el objeto
     * CitaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws ClassNotFoundException si la clase CitaDAO no se encuentra en el
     * classpath.
     * @throws java.sql.SQLException
     */
    public void handleAgregarCitas(ActionEvent event) throws ClassNotFoundException, SQLException {

        if (field_diagnosticoCita.getText().isEmpty()
                || field_nombreRecepcion.getText().isEmpty()
                || field_dniClienteCita.getText().isEmpty()
                || field_medicaciónCita.getText().isEmpty()
                || field_nombreMascota.getText().isEmpty()
                || field_tipoConsulta.getText().isEmpty()
                || field_tratamientoCita.getText().isEmpty()
                || fechaCita.getValue() == null
                || field_horaCita.getText().isEmpty()
                || field_precioCita.getText().isEmpty()
                || field_nombreVeterinario.getText().isEmpty()
                || field_apellidoUnoVeterinario.getText().isEmpty()
                || field_apellidoDosVeterinario.getText().isEmpty()) {
            // Todas las variables están vacías
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar cita");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene todos los campos obligatorios");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        // si la fecha no es válida
        if (!Utils.validarFechaCita(fechaCita)) {
            Alertas.alertFechaIncorrecta("La fecha de la cita debe ser posterior a la fecha actual");
            return;
        }

        String dniCliente = field_dniClienteCita.getText().trim();
        String nombreRecepcion = field_nombreRecepcion.getText().trim();
        String diagnosticoCita = field_diagnosticoCita.getText();
        String medicacionCita = field_medicaciónCita.getText();
        String nombreMascota = field_nombreMascota.getText().trim();
        String tipoConsulta = field_tipoConsulta.getText();
        String tratamientoCita = field_tratamientoCita.getText();
        LocalDate fechaCitaVet = fechaCita.getValue();
        String horaIngresada = field_horaCita.getText().trim();
        String precioCita = field_precioCita.getText();
        String nombreVeterinario = field_nombreVeterinario.getText().trim();
        String apellidoUnoVeterinario = field_apellidoUnoVeterinario.getText().trim();
        String apellidoDosVeterinario = field_apellidoDosVeterinario.getText().trim();

        // si la hora de la cita no es válida
        if (!Utils.validarHoraCita(horaIngresada)) {
            Alertas.alertHoraIncorrecta("La hora de la cita debe estar entre las 09:00 y 21:00");
            return;
        }

        // si el dni no tiene 8 números enteros y una letra en mayúsculas
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        if (!citaDAO.existeVeterinario(nombreVeterinario, apellidoUnoVeterinario, apellidoDosVeterinario)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar cita");
            alerta.setHeaderText("El veterinario no está registrado");
            alerta.setContentText("El veterinario no está registrado en la base de datos");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        if (!citaDAO.clienteCitas(dniCliente)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar cita");
            alerta.setHeaderText("El cliente no está registrado");
            alerta.setContentText("El cliente con el DNI " + dniCliente + " NO está registrado en la base de datos");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        if (!citaDAO.existeRecepcionista(nombreRecepcion)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar cita");
            alerta.setHeaderText("El recepcionista no está registrado");
            alerta.setContentText("El recepcionista no está registrado en la base de datos");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        if (!citaDAO.existeMascota(nombreMascota, dniCliente)) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al agregar cita");
            alerta.setHeaderText("La mascota no está registrada");
            alerta.setContentText("La mascota no está registrada en la base de datos");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        try {
            BigDecimal precioDecimal = new BigDecimal(precioCita);
            Time hora = Time.valueOf(horaIngresada + ":00");

            if (citaDAO.existeCita(fechaCitaVet, hora, nombreVeterinario, apellidoUnoVeterinario, apellidoDosVeterinario)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al agregar cita");
                alerta.setHeaderText("La cita no está disponible");
                alerta.setContentText("El veterinario tiene una cita a la misma hora y fecha en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            Cita cita = new Cita();
            // Añadir campos a la cita mediante los métodos set

            cita.setNombreMascota(nombreMascota);
            cita.setNombreVeterinario(nombreVeterinario);
            cita.setApellidoUnoVeterinario(apellidoUnoVeterinario);
            cita.setApellidoDosVeterinario(apellidoDosVeterinario);
            cita.setDniCliente(dniCliente);
            cita.setNombreRecepcion(nombreRecepcion);
            cita.setHoraCita(hora);
            cita.setTipoConsulta(tipoConsulta);
            cita.setDiagnostico(diagnosticoCita);
            cita.setTratamiento(tratamientoCita);
            cita.setMedicacion(medicacionCita);
            cita.setPrecio(precioDecimal);
            cita.setFechaCita(java.sql.Date.valueOf(fechaCitaVet));

            citaDAO.agregarCita(cita);
            limpiarCampos();

        } catch (Exception e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción handleAgregarCitas: " + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Este método se encarga de borrar los campos de texto en la ventana de
     * Agregar Cita.
     */
    @FXML
    public void limpiarCampos() {
        field_diagnosticoCita.setText("");
        field_medicaciónCita.setText("");
        field_nombreMascota.setText("");
        field_tipoConsulta.setText("");
        field_tratamientoCita.setText("");
        fechaCita.setValue(null);
        field_horaCita.setText("");
        field_precioCita.setText("");
        field_nombreVeterinario.setText("");
        field_apellidoUnoVeterinario.setText("");
        field_apellidoDosVeterinario.setText("");
        field_dniClienteCita.setText("");
        field_nombreRecepcion.setText("");
    }

}
