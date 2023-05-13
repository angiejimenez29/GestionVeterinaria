package controlador.cita;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.dao.CitaDAO;
import utils.Alertas;
import utils.Utils;

/**
 * FXML Controller class
 *
 * @author Nuria Vázquez 
 */
public class ModificarCitaController implements Initializable {

    @FXML
    private TableView<Cita> tablaCitas;

    @FXML
    private TableColumn<Cita, Integer> colIdCita;
    @FXML
    private TableColumn<Cita, java.sql.Date> colFechaCita;
    @FXML
    private TableColumn<Cita, java.sql.Time> colHoraCita;
    @FXML
    private TableColumn<Cita, String> colTipoConsulta;
    @FXML
    private TableColumn<Cita, String> colDiagnostico;
    @FXML
    private TableColumn<Cita, String> colTratamiento;
    @FXML
    private TableColumn<Cita, String> colMedicacion;
    @FXML
    private TableColumn<Cita, BigDecimal> colPrecio;
    @FXML
    private TableColumn<Cita, String> colNombreVet;
    @FXML
    private TableColumn<Cita, String> colApellidoUnoVet;
    @FXML
    private TableColumn<Cita, String> colApellidoDosVet;
    @FXML
    private TableColumn<Cita, String> colNombreMascota;
    @FXML
    private TableColumn<Cita, String> colDniCliente;

    @FXML
    private TextField field_diagnosticoCita;

    @FXML
    private TextField field_medicaciónCita;


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

    private CitaDAO citaDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        citaDAO = new CitaDAO();
        List<Cita> citas = citaDAO.obtenerCitas();
        ObservableList<Cita> citaData = FXCollections.observableArrayList(citas);
        tablaCitas.setItems(citaData);

        colIdCita.setCellValueFactory(new PropertyValueFactory<>("idCita"));
        colNombreVet.setCellValueFactory(new PropertyValueFactory<>("nombreVeterinario"));
        colApellidoUnoVet.setCellValueFactory(new PropertyValueFactory<>("apellidoUnoVeterinario"));
        colApellidoDosVet.setCellValueFactory(new PropertyValueFactory<>("apellidoDosVeterinario"));
        colNombreMascota.setCellValueFactory(new PropertyValueFactory<>("nombreMascota"));
        colFechaCita.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
        colHoraCita.setCellValueFactory(new PropertyValueFactory<>("horaCita"));
        colDniCliente.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
        colTipoConsulta.setCellValueFactory(new PropertyValueFactory<>("tipoConsulta"));
        colDiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));
        colTratamiento.setCellValueFactory(new PropertyValueFactory<>("tratamiento"));
        colMedicacion.setCellValueFactory(new PropertyValueFactory<>("medicacion"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // ajustar el ancho de las columnas al contenido
        tablaCitas.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        TableColumn[] columnas = {colIdCita, colNombreVet, colApellidoUnoVet, colApellidoDosVet, colNombreMascota,
            colDniCliente, colFechaCita, colHoraCita, colTipoConsulta, colDiagnostico,
            colTratamiento, colMedicacion, colPrecio};
        double[] anchos = {0.1, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.3, 0.3, 0.4, 0.4, 0.2};

        for (int i = 0; i < columnas.length; i++) {
            columnas[i].prefWidthProperty().bind(tablaCitas.widthProperty().multiply(anchos[i]));
        }
    }

    /**
     *
     * Maneja el evento de selección de una cita en la tablaCitas. Actualiza los
     * campos de texto y los campos de selección en la vista para mostrar la
     * información de la cita seleccionada.
     *
     * @param event el evento de selección del mouse
     */
    @FXML
    private void seleccionar(MouseEvent event) {
        Cita citaSeleccionado = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionado != null) {

            field_diagnosticoCita.setText(citaSeleccionado.getDiagnostico());
            field_medicaciónCita.setText(citaSeleccionado.getMedicacion());
            field_tipoConsulta.setText(citaSeleccionado.getTipoConsulta());
            field_tratamientoCita.setText(citaSeleccionado.getTratamiento());
            fechaCita.setValue(citaSeleccionado.getFechaCita().toLocalDate());
            field_horaCita.setText(String.valueOf(citaSeleccionado.getHoraCita()));
            field_precioCita.setText(String.valueOf(citaSeleccionado.getPrecio()));
      
        }
    }

    /**
     * Controlador de la ventana de modificar cita.Este controlador maneja los
     * eventos de la ventana de modificar los campos de las citas a través de la
     * interacción con la base de datos mediante el objeto CitaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.sql.SQLException
     */
    public void handleModificarCitas(ActionEvent event) throws SQLException {

        Cita citaSeleccionado = tablaCitas.getSelectionModel().getSelectedItem();

        //obtengo los datos del formulario
        String diagnosticoCita = field_diagnosticoCita.getText();
        String medicacionCita = field_medicaciónCita.getText();
        String tipoConsulta = field_tipoConsulta.getText();
        String tratamientoCita = field_tratamientoCita.getText();
        LocalDate fechaCitaVet = fechaCita.getValue();
        String horaIngresada = field_horaCita.getText().trim();
        String precioCita = field_precioCita.getText();

        // los campos no son nulos ni vacios
        if (field_diagnosticoCita.getText().isEmpty()
                || field_medicaciónCita.getText().isEmpty()
                || field_tipoConsulta.getText().isEmpty()
                || field_tratamientoCita.getText().isEmpty()
                || fechaCita.getValue() == null
                || field_horaCita.getText().isEmpty()
                || field_precioCita.getText().isEmpty()) {
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

        // si la hora de la cita no es válida
        if (!Utils.validarHoraCita(horaIngresada)) {
            Alertas.alertHoraIncorrecta("La hora de la cita debe estar entre las 09:00 y 21:00");
            return;
        }

        try {

            BigDecimal precioDecimal = new BigDecimal(precioCita);
            Time hora = Time.valueOf(horaIngresada + ":00");
            // Modificamos el cliente
            citaSeleccionado.setHoraCita(hora);
            citaSeleccionado.setTipoConsulta(tipoConsulta);
            citaSeleccionado.setDiagnostico(diagnosticoCita);
            citaSeleccionado.setTratamiento(tratamientoCita);
            citaSeleccionado.setMedicacion(medicacionCita);
            citaSeleccionado.setPrecio(precioDecimal);
            citaSeleccionado.setFechaCita(java.sql.Date.valueOf(fechaCitaVet));

            citaDAO.modificarCita(citaSeleccionado);
            limpiarCampos();
            tablaCitas.refresh();

        } catch (Exception e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
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
        field_tipoConsulta.setText("");
        field_tratamientoCita.setText("");
        fechaCita.setValue(null);
        field_horaCita.setText("");
        field_precioCita.setText("");
    }

}
