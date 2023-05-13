package controlador.auxiliar;

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
import modelo.Auxiliar;
import modelo.dao.AuxiliarDAO;

/**
 * Esta clase controla la vista de obtener un listado de auxiliares, la cual
 * permite la visualización en la ventana de los auxiliares guardados en a basee
 * de datos.
 *
 * @author Nuria Vázquez 
 */
public class ListadoAuxiliarController {

    @FXML
    private TableView<Auxiliar> tablaAuxiliares;
    @FXML
    private TableColumn<Auxiliar, Integer> colIdAuxiliar;
    @FXML
    private TableColumn<Auxiliar, String> colEspecialidad;
    @FXML
    private TableColumn<Auxiliar, BigDecimal> colSalario;
    @FXML
    private TableColumn<Auxiliar, String> colNombre;
    @FXML
    private TableColumn<Auxiliar, String> colApellidoUno;
    @FXML
    private TableColumn<Auxiliar, String> colApellidoDos;
    @FXML
    private TableColumn<Auxiliar, String> colDNI;
    @FXML
    private TableColumn<Auxiliar, java.sql.Date> colFechaNacimiento;
    @FXML
    private TableColumn<Auxiliar, String> colDireccion;
    @FXML
    private TableColumn<Auxiliar, Integer> colCP;
    @FXML
    private TableColumn<Auxiliar, Integer> colTelefono;
    @FXML
    private TableColumn<Auxiliar, String> colEmail;
    @FXML
    private TableColumn<Auxiliar, Auxiliar.Turno> colTurno;


    public AuxiliarDAO auxiliarDAO;

    public ListadoAuxiliarController() {
    }

    public ListadoAuxiliarController(AuxiliarDAO auxiliarDAO) {
        this.auxiliarDAO = auxiliarDAO;
    }

    /**
     * Controlador de la ventana de listado de auxiliares. Este controlador
     * maneja los eventos de la ventana de obtener a los auxiliares a través de
     * la interacción con la base de datos mediante el objeto AuxiliarDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.lang.ClassNotFoundException
     */
    public void handleObtenerAuxiliares(ActionEvent event) throws ClassNotFoundException {

        try {
            auxiliarDAO = new AuxiliarDAO();
            List<Auxiliar> auxiliares = auxiliarDAO.obtenerAuxiliares();
            ObservableList<Auxiliar> auxiliarData = FXCollections.observableArrayList(auxiliares);
            tablaAuxiliares.setItems(auxiliarData);

            colIdAuxiliar.setCellValueFactory(new PropertyValueFactory<>("idAuxiliar"));
            colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
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
            tablaAuxiliares.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

            TableColumn[] columnas = {colIdAuxiliar, colEspecialidad, colSalario,
                colNombre, colApellidoUno, colApellidoDos, colDNI, colFechaNacimiento,
                colDireccion, colCP, colTelefono, colEmail, colTurno};
            double[] anchos = {0.05, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.08, 0.1, 0.7, 0.05, 0.07};

            for (int i = 0; i < columnas.length; i++) {
                columnas[i].prefWidthProperty().bind(tablaAuxiliares.widthProperty().multiply(anchos[i]));
            }

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error con auxiliar");
            alerta.setHeaderText("Ha ocurrido un error con los auxiliares");
            alerta.setContentText("No se han podido mostrar todos los auxiliares.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

}
