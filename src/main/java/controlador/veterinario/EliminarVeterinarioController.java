package controlador.veterinario;

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
import modelo.Veterinario;
import modelo.dao.VeterinarioDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de eliminar veterinarios/as, la cual permite la
 * gestión de la eliminación de veterinarios/as de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class EliminarVeterinarioController {

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

    @FXML
    private TextField buscarVeterinario;

    public VeterinarioDAO veterinarioDAO;

    public EliminarVeterinarioController() {
    }

    public EliminarVeterinarioController(VeterinarioDAO veterinarioDAO) {
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

        // si el campo esta vacio
        if (buscarVeterinario.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al eliminar al veterinario/a");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del veterinario/a");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniVeterinario = buscarVeterinario.getText().trim();
        
        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniVeterinario)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {
            veterinarioDAO = new VeterinarioDAO();
            // si el veterinario/a no existe en la base de datos
            if (!veterinarioDAO.veterinarioExiste(dniVeterinario)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar veterinario/a");
                alerta.setHeaderText("Veterinario/a no registrado/a");
                alerta.setContentText("El/la veterinario/a con DNI " + dniVeterinario + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;

            }

            List<Veterinario> listaVeterinarios = veterinarioDAO.buscarVeterinarios(dniVeterinario);
            ObservableList<Veterinario> veterinarioData = FXCollections.observableArrayList(listaVeterinarios);

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

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }
    }

    /**
     * Controlador de la ventana de eliminar veterinarios/as. Este controlador
     * maneja los eventos de la ventana de eliminar veterinarios/as y realiza la
     * búsqueda de veterinarios/as a través de la interacción con la base de
     * datos mediante el objeto VeterinarioDAO.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleEliminarVeterinarios(ActionEvent event) {

        Veterinario veterinarioSeleccionado = tablaVeterinarios.getSelectionModel().getSelectedItem();
        // Verifica que se haya seleccionado una fila
        if (veterinarioSeleccionado != null) {
            // Muestra ventana de alerta para confirmar eliminación del veterinario/a
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar veterinario/a");
            alert.setHeaderText("¿Está seguro de que desea eliminar al veterinario/a?");
            alert.setContentText("El/la veterinario/a " + veterinarioSeleccionado.getDni() + " será eliminado/a de forma permanente.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Elimina el veterinario/a seleccionado/a de la lista de veterinarios/as y de la tabla
                try {
                    veterinarioDAO = new VeterinarioDAO();
                    veterinarioDAO.eliminarVeterinario(veterinarioSeleccionado);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Veterinario/a eliminado/a");
                    alert.setHeaderText("Veterinario/a eliminado/a");
                    alert.setContentText("El/la veterinario/a " + veterinarioSeleccionado.getDni() + " ha sido eliminado/a correctamente.");
                    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Resultado de la excepción: " + e.getMessage());
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error al eliminar veterinario/a");
                    alert.setHeaderText("Ha ocurrido un error al eliminar el veterinario/a.");
                    alert.setContentText("Detalles del error: " + e.getMessage());
                    stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                    alert.showAndWait();
                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ningún veterinario/a seleccionado/a");
            alert.setHeaderText("Error en la selección del veterinario/a");
            alert.setContentText("Por favor, seleccione un veterinario/a de la tabla.");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alert.showAndWait();
        }

    }

}
