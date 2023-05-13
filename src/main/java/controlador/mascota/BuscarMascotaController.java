package controlador.mascota;


import java.sql.SQLException;
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
import modelo.Mascota;
import modelo.dao.MascotaDAO;
import utils.Alertas;
import utils.Utils;

/**
 *
 * Esta clase controla la vista de la búsqueda de mascotas.
 *
 * @author Nuria Vázquez 
 */
public class BuscarMascotaController {

    public MascotaDAO mascotaDAO;

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
    private TableColumn<Mascota, Integer> colDniCliente;
    @FXML
    private TextField buscarMascota;

    /**
     * Controlador de la ventana de búsqueda de mascotas. Este controlador
     * maneja los eventos de la ventana de búsqueda de mascotas y realiza la
     * búsqueda de mascotas a través de la interacción con la base de datos
     * mediante el objeto MascotaDAO. Los resultados de la búsqueda se muestran
     * en la tabla de la ventana de búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarMascotas(ActionEvent event) {

        // si el campo esta vacio
        if (buscarMascota.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar la mascota");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del cliente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }
        String dniCliente = buscarMascota.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {

            mascotaDAO = new MascotaDAO();
            // Validar que el cliente existe en la base de datos
            if (mascotaDAO.clienteExiste(dniCliente)) {
                List<Mascota> listaMascotas = mascotaDAO.buscarMascota(dniCliente);
                ObservableList<Mascota> mascotaData = FXCollections.observableArrayList(listaMascotas);
                tablaMascotas.setItems(mascotaData);
                colIdMascota.setCellValueFactory(new PropertyValueFactory<>("idMascota"));
                colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
                colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
                colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
                colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
                colDniCliente.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
                tablaMascotas.setItems(FXCollections.observableArrayList(listaMascotas));
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar mascota");
                alerta.setHeaderText("Cliente no encontrado");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no está registrado en la base de datos.");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
            }

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }



}
