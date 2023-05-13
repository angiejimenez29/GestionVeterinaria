package controlador.recepcionista;

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
import modelo.Recepcionista;
import modelo.dao.RecepcionistaDAO;

/**
 * Esta clase controla la vista de obtener un listado de recepcionistas, la cual
 * permite la visualización en la ventana de los recepcionistas guardados en la
 * basee de datos.
 *
 * @author Nuria Vázquez 
 */
public class ListadoRecepcionistaController {

    @FXML
    private TableView<Recepcionista> tablaRecepcionistas;
    @FXML
    private TableColumn<Recepcionista, Integer> colIdRecepcionista;
    @FXML
    private TableColumn<Recepcionista, BigDecimal> colSalario;
    @FXML
    private TableColumn<Recepcionista, String> colNombre;
    @FXML
    private TableColumn<Recepcionista, String> colApellidoUno;
    @FXML
    private TableColumn<Recepcionista, String> colApellidoDos;
    @FXML
    private TableColumn<Recepcionista, String> colDNI;
    @FXML
    private TableColumn<Recepcionista, java.sql.Date> colFechaNacimiento;
    @FXML
    private TableColumn<Recepcionista, String> colDireccion;
    @FXML
    private TableColumn<Recepcionista, Integer> colCP;
    @FXML
    private TableColumn<Recepcionista, Integer> colTelefono;
    @FXML
    private TableColumn<Recepcionista, String> colEmail;
    @FXML
    private TableColumn<Recepcionista, Recepcionista.Turno> colTurno;

    public RecepcionistaDAO recepcionistaDAO;

    public ListadoRecepcionistaController() {
    }

    public ListadoRecepcionistaController(RecepcionistaDAO recepcionistaDAO) {
        this.recepcionistaDAO = recepcionistaDAO;
    }

    /**
     * Controlador de la ventana de listado de recepcionistas. Este controlador
     * maneja los eventos de la ventana de obtener a los recepcionistas a través de
     * la interacción con la base de datos mediante el objeto RecepcionistaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.lang.ClassNotFoundException
     */
    public void handleObtenerRecepcionistas(ActionEvent event) throws ClassNotFoundException {

        try {
            recepcionistaDAO = new RecepcionistaDAO();
            List<Recepcionista> recepcionistas = recepcionistaDAO.obtenerRecepcionistas();
            ObservableList<Recepcionista> recepcionistaData = FXCollections.observableArrayList(recepcionistas);
            tablaRecepcionistas.setItems(recepcionistaData);
            colIdRecepcionista.setCellValueFactory(new PropertyValueFactory<>("idRecepcionista"));
            colSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colApellidoUno.setCellValueFactory(new PropertyValueFactory<>("apellidoUno"));
            colApellidoDos.setCellValueFactory(new PropertyValueFactory<>("apellidoDos"));
            colDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
            colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
            colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
            colCP.setCellValueFactory(new PropertyValueFactory<>("cp"));
            colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));

            // ajustar el ancho de las columnas al contenido
            tablaRecepcionistas.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

            TableColumn[] columnas = {colIdRecepcionista, colSalario,
                colNombre, colApellidoUno, colApellidoDos, colDNI, colFechaNacimiento,
                colDireccion, colCP, colTelefono, colEmail, colTurno};
            double[] anchos = {0.05, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.08, 0.1, 0.7, 0.05, 0.07};

            for (int i = 0; i < columnas.length; i++) {
                columnas[i].prefWidthProperty().bind(tablaRecepcionistas.widthProperty().multiply(anchos[i]));
            }

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error con recepcionistas");
            alerta.setHeaderText("Ha ocurrido un error con los recepcionistas");
            alerta.setContentText("No se han podido mostrar todos los recepcionistas.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

}
