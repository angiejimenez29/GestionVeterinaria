package controlador.mascota;

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
import modelo.Mascota;
import modelo.dao.MascotaDAO;

/**
 * Esta clase controla la vista de obtener un listado de mascotas, la cual
 * permite la visualización en la ventana de los mascotas guardadas en a basee
 * de datos.
 *
 * @author Nuria Vázquez 
 */
public class ListadoMascotaController {

    @FXML
    private TableView<Mascota> tablaMascotas;

    @FXML
    private TableColumn<Mascota, Integer> colIdMascota;

    @FXML
    private TableColumn<Mascota, String> colNombre;

    @FXML
    private TableColumn<Mascota, Character> colSexo;

    @FXML
    private TableColumn<Mascota, java.sql.Date> colFechaNacimiento;

    @FXML
    private TableColumn<Mascota, String> colRaza;

    @FXML
    private TableColumn<Mascota, String> colEspecie;

    @FXML
    private TableColumn<Mascota, String> colDniCliente;

    public MascotaDAO mascotaDAO;

    public ListadoMascotaController() {
    }

    public ListadoMascotaController(MascotaDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
    }

    /**
     * Controlador de la ventana de listado de mascotas.Este controlador maneja
     * los eventos de la ventana de obtener las mascotas a través de la
     * interacción con la base de datos mediante el objeto MascotaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.lang.ClassNotFoundException
     */
    public void handleObtenerMascotas(ActionEvent event) throws ClassNotFoundException {

        try {
            mascotaDAO = new MascotaDAO();
            List<Mascota> mascotas = mascotaDAO.obtenerMascotas();
            ObservableList<Mascota> mascotaData = FXCollections.observableArrayList(mascotas);
            tablaMascotas.setItems(mascotaData);
            colIdMascota.setCellValueFactory(new PropertyValueFactory<>("idMascota"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
            colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
            colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
            colDniCliente.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
            tablaMascotas.setItems(FXCollections.observableArrayList(mascotas));
            // ajustar el ancho de las columnas al contenido
            tablaMascotas.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error con Mascota");
            alerta.setHeaderText("Ha ocurrido un error con las mascotas");
            alerta.setContentText("No se han podido mostrar todas las mascotas.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

}
