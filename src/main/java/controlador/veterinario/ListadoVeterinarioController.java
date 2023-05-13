package controlador.veterinario;

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
import modelo.Veterinario;
import modelo.dao.VeterinarioDAO;

/**
 * Esta clase controla la vista de obtener un listado de veterinarios, la cual
 * permite la visualización en la ventana de los veterinarios guardados en a
 * basee de datos.
 *
 * @author Nuria Vázquez 
 */
public class ListadoVeterinarioController {

    @FXML
    private TableView<Veterinario> tablaVeterinarios;
    @FXML
    private TableColumn<Veterinario, Integer> colIdVeterinario;
    @FXML
    private TableColumn<Veterinario, String> colEspecialidad;
    @FXML
    private TableColumn<Veterinario, BigDecimal> colSalario;
    @FXML
    private TableColumn<Veterinario, String> colNombre;
    @FXML
    private TableColumn<Veterinario, String> colApellidoUno;
    @FXML
    private TableColumn<Veterinario, String> colApellidoDos;
    @FXML
    private TableColumn<Veterinario, String> colDNI;
    @FXML
    private TableColumn<Veterinario, java.sql.Date> colFechaNacimiento;
    @FXML
    private TableColumn<Veterinario, String> colDireccion;
    @FXML
    private TableColumn<Veterinario, Integer> colCP;
    @FXML
    private TableColumn<Veterinario, Integer> colTelefono;
    @FXML
    private TableColumn<Veterinario, String> colEmail;
    @FXML
    private TableColumn<Veterinario, Veterinario.Turno> colTurno;

    public VeterinarioDAO veterinarioDAO;

    public ListadoVeterinarioController() {
    }

    public ListadoVeterinarioController(VeterinarioDAO veterinarioDAO) {
        this.veterinarioDAO = veterinarioDAO;
    }

    /**
     * Controlador de la ventana de listado de veterinarios. Este controlador
     * maneja los eventos de la ventana de obtener a los veterinarios a través
     * de la interacción con la base de datos mediante el objeto VeterinarioDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.lang.ClassNotFoundException
     */
    public void handleObtenerVeterinarios(ActionEvent event) throws ClassNotFoundException {

        try {
            veterinarioDAO = new VeterinarioDAO();
            List<Veterinario> veterinarios = veterinarioDAO.obtenerVeterinarios();
            ObservableList<Veterinario> veterinarioData = FXCollections.observableArrayList(veterinarios);
            tablaVeterinarios.setItems(veterinarioData);
            colIdVeterinario.setCellValueFactory(new PropertyValueFactory<>("IdVeterinario"));
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
            tablaVeterinarios.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

            TableColumn[] columnas = {colIdVeterinario, colEspecialidad, colSalario,
                colNombre, colApellidoUno, colApellidoDos, colDNI, colFechaNacimiento,
                colDireccion, colCP, colTelefono, colEmail, colTurno};
            double[] anchos = {0.05, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.08, 0.1, 0.7, 0.05};

            for (int i = 0; i < columnas.length; i++) {
                columnas[i].prefWidthProperty().bind(tablaVeterinarios.widthProperty().multiply(anchos[i]));
            }

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error con veterinario/a");
            alerta.setHeaderText("Ha ocurrido un error con los veterinario/as");
            alerta.setContentText("No se han podido mostrar todos los veterinarios/as.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

}
