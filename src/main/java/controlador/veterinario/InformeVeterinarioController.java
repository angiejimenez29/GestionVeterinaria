package controlador.veterinario;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import utils.Alertas;
import utils.Utils;

/**
 *
 * Esta clase controla la vista de la crear el informe a través de la búsqueda
 * de veterinarios.
 *
 * @author Nuria Vázquez 
 */
public class InformeVeterinarioController {

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

    /**
     * Crea el origen de datos para el informe de veterinarios utilizando el
     * veterinario seleccionado como fuente de datos.
     *
     * @param veterinarioSeleccionado El veterinario seleccionado para generar
     * el informe.
     * @return Un objeto JRBeanCollectionDataSource que contiene la lista de
     * veterinarios, con el veterinario seleccionado como única entrada.
     */
    private JRBeanCollectionDataSource crearOrigenDatos(Veterinario veterinarioSeleccionado) {
        List<Veterinario> listaVeterinarios = new ArrayList<>();
        listaVeterinarios.add(veterinarioSeleccionado);
        JRBeanCollectionDataSource origenDatos = new JRBeanCollectionDataSource(listaVeterinarios);
        return origenDatos;
    }

    /**
     *
     * Genera un informe de un veterinario seleccionado de la tabla de
     * veterinarios.
     *
     * @param event el evento que desencadena la generación del informe.
     * @throws JRException si hay un error al generar el informe con
     * JasperReports.
     */
    @FXML
    public void handleGenerarInformeVeterinarios(ActionEvent event) throws JRException {

        Veterinario veterinarioSeleccionado = tablaVeterinarios.getSelectionModel().getSelectedItem();
        if (veterinarioSeleccionado == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora seleccionar un veterinario");
            alerta.setHeaderText("Error en la selección del veterinario");
            alerta.setContentText("Debe seleccionar un veterinario de la tabla.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        try {
            // cargar el archivo de reporte Jasper

            InputStream inputStream = getClass().getResourceAsStream("/reports/informeVeterinario.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // crear un mapa de parámetros para el reporte
            Map<String, Object> parametros = new HashMap<>();

            parametros.put("dni", veterinarioSeleccionado.getDni());
            parametros.put("nombre", veterinarioSeleccionado.getNombre());
            parametros.put("apellidoUno", veterinarioSeleccionado.getApellidoUno());
            parametros.put("apellidoDos", veterinarioSeleccionado.getApellidoDos());
            parametros.put("especialidad", veterinarioSeleccionado.getEspecialidad());
            parametros.put("salario", veterinarioSeleccionado.getSalario());
            parametros.put("fechaNac", veterinarioSeleccionado.getFechaNac());
            parametros.put("telefono", veterinarioSeleccionado.getTelefono());
            parametros.put("direccion", veterinarioSeleccionado.getDireccion());
            parametros.put("cp", veterinarioSeleccionado.getCp());
            parametros.put("email", veterinarioSeleccionado.getEmail());
            parametros.put("turno", veterinarioSeleccionado.getTurno());

            // crear el origen de datos del reporte
            JRBeanCollectionDataSource origenDatos = crearOrigenDatos(veterinarioSeleccionado);

            // generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, origenDatos);

            // visualizar el informe en una ventana de diálogo
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Informe - Veterinario " + veterinarioSeleccionado.getNombre());
            viewer.setVisible(true);

        } catch (JRException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora de generar el informe");
            alerta.setHeaderText("Error en el informe");
            alerta.setContentText("Error al generar el informe");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Error al generar el informe " + e.getMessage());
            e.printStackTrace();
        }

    }

}
