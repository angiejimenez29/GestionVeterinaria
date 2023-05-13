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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cita;
import modelo.dao.CitaDAO;
import modelo.dao.ClienteDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de la búsqueda de Citas.
 *
 * @author Nuria Vázquez 
 */
public class BuscarCitaController {

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
    public ClienteDAO clienteDAO;

    @FXML
    private TextField buscarCita;

    /**
     * Controlador de la ventana de búsqueda de citas. Este controlador maneja
     * los eventos de la ventana de búsqueda de citas y realiza la búsqueda de
     * citas a través de la interacción con la base de datos mediante el objeto
     * CitaDAO. Los resultados de la búsqueda se muestran en la tabla de la
     * ventana de búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarCitas(ActionEvent event) {

        // si el campo esta vacio
        if (buscarCita.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar la cita");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del cliente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniCliente = buscarCita.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {

            citaDAO = new CitaDAO();
            clienteDAO = new ClienteDAO();
            // si el cliente/a no existe en la base de datos
            if (!clienteDAO.clienteExiste(dniCliente)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar cita");
                alerta.setHeaderText("Cita no encontrada");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no está registrado en la base de datos.");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            if (citaDAO.clienteCitas(dniCliente)) {

                List<Cita> citas = citaDAO.buscarCitas(dniCliente);
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

            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar cita");
                alerta.setHeaderText("Cita no encontrada");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no tiene asignada ninguna cita.");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
            }

        } catch (Exception e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }

    }

}
