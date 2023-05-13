package controlador.cita;

import java.math.BigDecimal;
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
import modelo.Cita;
import modelo.dao.CitaDAO;

/**
 * Esta clase controla la vista de obtener un listado de citas, la cual permite
 * la visualización en la ventana de las citas guardadas en la basee de datos.
 *
 * @author Nuria Vázquez 
 */
public class ListadoCitaController {

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

    public CitaDAO citaDAO;

    public ListadoCitaController() {
    }

    public ListadoCitaController(CitaDAO citaDAO) {
        this.citaDAO = citaDAO;
    }

    /**
     * Controlador de la ventana de listado de citas. Este controlador maneja
     * los eventos de la ventana de obtener a las citas a través de la
     * interacción con la base de datos mediante el objeto CitaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.lang.ClassNotFoundException
     */
    public void handleObtenerCitas(ActionEvent event) throws ClassNotFoundException {

        try {
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
            colTipoConsulta.setCellValueFactory(new PropertyValueFactory<>("tipoConsulta"));
            colDiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));
            colTratamiento.setCellValueFactory(new PropertyValueFactory<>("tratamiento"));
            colMedicacion.setCellValueFactory(new PropertyValueFactory<>("medicacion"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

            // ajustar el ancho de las columnas al contenido
            tablaCitas.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
            TableColumn[] columnas = {colIdCita, colNombreVet, colApellidoUnoVet, colApellidoDosVet, colNombreMascota,
                colFechaCita, colHoraCita, colTipoConsulta, colDiagnostico,
                colTratamiento, colMedicacion, colPrecio};
            double[] anchos = {0.1, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.3, 0.3, 0.4, 0.4, 0.2};

            for (int i = 0; i < columnas.length; i++) {
                columnas[i].prefWidthProperty().bind(tablaCitas.widthProperty().multiply(anchos[i]));
            }

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error con citas");
            alerta.setHeaderText("Ha ocurrido con las citas");
            alerta.setContentText("No se han podido mostrar todas las citas.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

}
