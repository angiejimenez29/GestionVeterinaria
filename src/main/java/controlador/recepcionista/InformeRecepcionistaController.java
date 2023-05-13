package controlador.recepcionista;

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
import modelo.Recepcionista;
import modelo.dao.RecepcionistaDAO;
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
 * de recepcionistas.
 *
 * @author Nuria Vázquez 
 */
public class InformeRecepcionistaController {

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

    /**
     * Controlador de la ventana de búsqueda de recepcionistas. Este controlador
     * maneja los eventos de la ventana de búsqueda de recepcionistas y realiza
     * la búsqueda de rececpionista a través de la interacción con la base de
     * datos mediante el objeto RecepcionistaDAO. Los resultados de la búsqueda
     * se muestran en la tabla de la ventana de búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarRecepcionistas(ActionEvent event) {

        // si el campo esta vacio
        if (buscarRecepcionista.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar el recepcionista");
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
            // si el veterinario/a existe en la base de datos
            if (recepcionistaDAO.recepcionistaExiste(dniRecepcionista)) {

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
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar recepcionista");
                alerta.setHeaderText("Recepcionista no encontrado");
                alerta.setContentText("El recepcionista con DNI " + dniRecepcionista + " no está registrado en la base de datos.");
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
     * Crea el origen de datos para el informe de recepcionistas utilizando el
     * recepcionista seleccionado como fuente de datos.
     *
     * @param recepcionistaSeleccionado El recepcionista seleccionado para
     * generar el informe.
     * @return Un objeto JRBeanCollectionDataSource que contiene la lista de
     * recepcionistas, con el recepcionista seleccionado como única entrada.
     */
    private JRBeanCollectionDataSource crearOrigenDatos(Recepcionista recepcionistaSeleccionado) {
        List<Recepcionista> listaRecepcionistas = new ArrayList<>();
        listaRecepcionistas.add(recepcionistaSeleccionado);
        JRBeanCollectionDataSource origenDatos = new JRBeanCollectionDataSource(listaRecepcionistas);
        return origenDatos;
    }

    /**
     *
     * Genera un informe de un recepcionista seleccionado de la tabla de
     * recepcionistas.
     *
     * @param event el evento que desencadena la generación del informe.
     * @throws JRException si hay un error al generar el informe con
     * JasperReports.
     */
    @FXML
    public void handleGenerarInformeRecepcionistas(ActionEvent event) throws JRException {

        Recepcionista recepcionistaSeleccionado = tablaRecepcionistas.getSelectionModel().getSelectedItem();
        if (recepcionistaSeleccionado == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora seleccionar un recepcionista");
            alerta.setHeaderText("Error en la selección del recepcionista");
            alerta.setContentText("Debe seleccionar un recepcionista de la tabla.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        try {
            // cargar el archivo de reporte Jasper
            InputStream inputStream = getClass().getResourceAsStream("/reports/informeRecepcionista.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // crear un mapa de parámetros para el reporte
            Map<String, Object> parametros = new HashMap<>();

            parametros.put("dni", recepcionistaSeleccionado.getDni());
            parametros.put("nombre", recepcionistaSeleccionado.getNombre());
            parametros.put("apellidoUno", recepcionistaSeleccionado.getApellidoUno());
            parametros.put("apellidoDos", recepcionistaSeleccionado.getApellidoDos());
            parametros.put("salario", recepcionistaSeleccionado.getSalario());
            parametros.put("fechaNac", recepcionistaSeleccionado.getFechaNac());
            parametros.put("telefono", recepcionistaSeleccionado.getTelefono());
            parametros.put("direccion", recepcionistaSeleccionado.getDireccion());
            parametros.put("cp", recepcionistaSeleccionado.getCp());
            parametros.put("email", recepcionistaSeleccionado.getEmail());
            parametros.put("turno", recepcionistaSeleccionado.getTurno());

            // crear el origen de datos del reporte
            JRBeanCollectionDataSource origenDatos = crearOrigenDatos(recepcionistaSeleccionado);

            // generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, origenDatos);

            // visualizar el informe en una ventana de diálogo
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Informe - Recepcionista " + recepcionistaSeleccionado.getNombre());
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
