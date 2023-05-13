package controlador.veterinario;

import java.math.BigDecimal;
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
import modelo.Veterinario;
import modelo.dao.VeterinarioDAO;
import utils.Alertas;
import utils.Utils;

/**
 *
 * Esta clase controla la vista de la búsqueda de Veterinarios/as.
 *
 * @author Nuria Vázquez 
 */
public class BuscarVeterinarioController {

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

    /**
     * Controlador de la ventana de búsqueda de veterinarios/as. Este
     * controlador maneja los eventos de la ventana de búsqueda de
     * veterinarios/as y realiza la búsqueda de veterinrios/as a través de la
     * interacción con la base de datos mediante el objeto VeterinarioDAO. Los
     * resultados de la búsqueda se muestran en la tabla de la ventana de
     * búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarVeterinarios(ActionEvent event) {

        // si el campo esta vacio
        if (buscarVeterinario.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar al veterinario/a");
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
            // si el veterinario/a existe en la base de datos
            if (veterinarioDAO.veterinarioExiste(dniVeterinario)) {

                List<Veterinario> veterinarios = veterinarioDAO.buscarVeterinarios(dniVeterinario);
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
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar veterinario/a");
                alerta.setHeaderText("Veterinario/a no encontrado");
                alerta.setContentText("El/la veterinario/a con DNI " + dniVeterinario + " no está registrado en la base de datos.");
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
