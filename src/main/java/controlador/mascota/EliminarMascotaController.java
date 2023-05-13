package controlador.mascota;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
 * Esta clase controla la vista de eliminar mascotas, la cual permite la gestión
 * de la eliminación de mascotas de un cliente de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class EliminarMascotaController {

    private MascotaDAO mascotaDAO;

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

    @FXML
    private TextField campoDniCliente;

    /**
     * Controlador de la ventana de listado de mascotas.Este controlador maneja
     * los eventos de la ventana de obtener las mascotas a través de la
     * interacción con la base de datos mediante el objeto MascotaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws java.lang.ClassNotFoundException
     */
    @FXML
    public void handleObtenenerMascotasCliente(ActionEvent event) throws ClassNotFoundException {

        // si el campo esta vacio
        if (campoDniCliente.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al eliminar la mascota");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del cliente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniCliente = campoDniCliente.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {
            mascotaDAO = new MascotaDAO();
            // Validar que el cliente no existe en la base de datos
            if (!mascotaDAO.clienteExiste(dniCliente)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar mascota");
                alerta.setHeaderText("Cliente no registrado");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;

            }
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
        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar mascota");
            alerta.setHeaderText("Búsqueda no realizada");
            alerta.setContentText("Se produjo un error al buscar la mascota en la base de datos: " + e.getMessage());
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            e.printStackTrace();
        }
    }

    /**
     * Controlador de la ventana de eliminar mascotas. Este controlador maneja
     * los eventos de la ventana de eliminar mascotas y realiza la búsqueda de
     * mascotas a través de la interacción con la base de datos mediante el
     * objeto MascotaDAO.
     *
     * @param event Evento que dispara la acción.
     */
    public void handleEliminarMascotas(ActionEvent event) {

        Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();
        // Verifica que se haya seleccionado una fila
        if (mascotaSeleccionada != null) {
            // Muestra una ventana de alerta de confirmación antes de eliminar la mascota
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar mascota");
            alert.setHeaderText("¿Está seguro de que desea eliminar la mascota?");
            alert.setContentText("La mascota " + mascotaSeleccionada.getNombre() + " será eliminada de forma permanente.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Elimina la mascota seleccionada de la lista de mascotas y de la tabla
                try {
                    mascotaDAO = new MascotaDAO();
                    mascotaDAO.eliminarMascota(mascotaSeleccionada);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mascota eliminada");
                    alert.setHeaderText("Mascota eliminada.");
                    alert.setContentText("La mascota " + mascotaSeleccionada.getNombre() + " ha sido eliminada correctamente.");
                    stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Resultado de la excepción: " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al eliminar mascota");
                    alert.setHeaderText("Ha ocurrido un error al eliminar la mascota.");
                    alert.setContentText("Detalles del error: " + e.getMessage());
                    stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                    stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                    alert.showAndWait();
                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ninguna mascota seleccionada");
            alert.setHeaderText("Error al eliminar la mascota");
            alert.setContentText("Por favor, seleccione una mascota de la tabla.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alert.showAndWait();
        }
    }

}
