package controlador.recepcionista;

import java.math.BigDecimal;
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
import modelo.Recepcionista;
import modelo.dao.RecepcionistaDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de eliminar recepcionistas, la cual permite la
 * gestión de la eliminación de recepcionistas de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class EliminarRecepcionistaController {

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
    @FXML
    private TextField buscarRecepcionista;

    public EliminarRecepcionistaController() {
    }

    public EliminarRecepcionistaController(RecepcionistaDAO recepcionistaDAO) {
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

        // si el campo esta vacio
        if (buscarRecepcionista.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al eliminar al recepcionista");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del recepcionista");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniRecepcionista = buscarRecepcionista.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniRecepcionista)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {
            recepcionistaDAO = new RecepcionistaDAO();
            // si el recepcionista no existe en la base de datos
            if (!recepcionistaDAO.recepcionistaExiste(dniRecepcionista)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar Recepcionista");
                alerta.setHeaderText("Recepcionista registrado");
                alerta.setContentText("Recepcionista con DNI " + dniRecepcionista + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;

            }

            List<Recepcionista> recepcionistas = recepcionistaDAO.buscarRecepcionistas(dniRecepcionista);
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

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

    /**
     * Controlador de la ventana de eliminar recepcionistas. Este controlador maneja
     * los eventos de la ventana de eliminar recepcionistas y realiza la búsqueda de
     * recepcionistas a través de la interacción con la base de datos mediante el
     * objeto RecepcionistaDAO.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleEliminarRecepcionistas(ActionEvent event) {

        Recepcionista recepcionistaSeleccionado = tablaRecepcionistas.getSelectionModel().getSelectedItem();
        // Verifica que se haya seleccionado una fila
        if (recepcionistaSeleccionado != null) {
            // Muestra ventana de alerta para confirmar eliminación del recepcionista
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar recepcionista");
            alert.setHeaderText("¿Está seguro de que desea eliminar al recepcionista?");
            alert.setContentText("El recepcionista " + recepcionistaSeleccionado.getDni() + " será eliminado de forma permanente.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Elimina el recepcionista seleccionado de la lista de recepcionistas y de la tabla
                try {
                    recepcionistaDAO = new RecepcionistaDAO();
                    recepcionistaDAO.eliminarRecepcionista(recepcionistaSeleccionado);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ningún recepcionista seleccionado");
            alert.setHeaderText("Error en la selección del recepcionista");
            alert.setContentText("Por favor, seleccione un recepcionista de la tabla.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alert.showAndWait();
        }

    }

}
