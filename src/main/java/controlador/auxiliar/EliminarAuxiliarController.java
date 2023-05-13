package controlador.auxiliar;

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
import modelo.Auxiliar;
import modelo.dao.AuxiliarDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de eliminar auxiliares, la cual permite la
 * gestión de la eliminación de auxiliares de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class EliminarAuxiliarController {

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

    @FXML
    private TextField buscarAuxiliar;

    public AuxiliarDAO auxiliarDAO;

    public EliminarAuxiliarController() {
    }

    public EliminarAuxiliarController(AuxiliarDAO auxiliarDAO) {
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

        // si el campo esta vacio
        if (buscarAuxiliar.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al eliminar al auxiliar");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del auxiliar");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniAuxiliar = buscarAuxiliar.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniAuxiliar)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {
            auxiliarDAO = new AuxiliarDAO();
            // si el auxiliar no existe en la base de datos
            if (!auxiliarDAO.auxiliarExiste(dniAuxiliar)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar veterinario/a");
                alerta.setHeaderText("Veterinario/a no registrado/a");
                alerta.setContentText("El/la veterinario/a con DNI " + dniAuxiliar + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;

            }

            List<Auxiliar> auxiliares = auxiliarDAO.buscarAuxiliares(dniAuxiliar);
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

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

    /**
     * Controlador de la ventana de eliminar auxiliares. Este controlador maneja
     * los eventos de la ventana de eliminar auxiliares y realiza la búsqueda de
     * auxiliares a través de la interacción con la base de datos mediante el
     * objeto AuxiliarDAO.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleEliminarAuxiliares(ActionEvent event) {

        Auxiliar auxiliarSeleccionado = tablaAuxiliares.getSelectionModel().getSelectedItem();
        // Verifica que se haya seleccionado una fila
        if (auxiliarSeleccionado != null) {
            // Muestra ventana de alerta para confirmar eliminación del auxiliar
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar auxiliar");
            alert.setHeaderText("¿Está seguro de que desea eliminar al auxiliar?");
            alert.setContentText("El auxiliar " + auxiliarSeleccionado.getDni() + " será eliminado de forma permanente.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Elimina el auxiliar seleccionado de la lista de auxiliares y de la tabla
                try {
                    auxiliarDAO = new AuxiliarDAO();
                    auxiliarDAO.eliminarAuxiliar(auxiliarSeleccionado);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ningún auxiliar seleccionado");
            alert.setHeaderText("Error en la selección del auxiliar");
            alert.setContentText("Por favor, seleccione un auxiliar de la tabla.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alert.showAndWait();
        }

    }

}
