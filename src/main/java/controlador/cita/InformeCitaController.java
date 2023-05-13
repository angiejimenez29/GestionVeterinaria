package controlador.cita;

import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
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
import modelo.Cita;
import modelo.dao.CitaDAO;
import modelo.dao.ClienteDAO;
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
 * de citas.
 *
 * @author Nuria Vázquez 
 */
public class InformeCitaController {

    @FXML
    private TableView<Cita> tablaCitas;
    @FXML
    private TableColumn<Cita, Integer> colIdCita;
    @FXML
    private TableColumn<Cita, java.sql.Date> colFechaCita;
    @FXML
    private TableColumn<Cita, java.sql.Time> colHoraCita;
    @FXML
    private TableColumn<Cita, String> colTipoConsulta;
    @FXML
    private TableColumn<Cita, String> colDiagnostico;
    @FXML
    private TableColumn<Cita, String> colTratamiento;
    @FXML
    private TableColumn<Cita, String> colMedicacion;
    @FXML
    private TableColumn<Cita, BigDecimal> colPrecio;
    @FXML
    private TableColumn<Cita, String> colNombreVet;
    @FXML
    private TableColumn<Cita, String> colApellidoUnoVet;
    @FXML
    private TableColumn<Cita, String> colApellidoDosVet;
    @FXML
    private TableColumn<Cita, String> colNombreMascota;

    public CitaDAO citaDAO;
    public ClienteDAO clienteDAO;

    @FXML
    private TextField buscarCita;

    /**
     * Controlador de la ventana de búsqueda de citas. Este controlador maneja
     * los eventos de la ventana de búsqueda de citas y realiza la búsqueda de
     * citas a través de la interacción con la base de datos mediante el objeto
     * CitaDAO. Los resultados de la búsqueda se muestran en la tabla de la
     * ventana de búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarCitas(ActionEvent event) {

        // si el campo esta vacio
        if (buscarCita.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar la cita");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del cliente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniCliente = buscarCita.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {

            citaDAO = new CitaDAO();
            clienteDAO = new ClienteDAO();
            // si el cliente/a existe en la base de datos
            if (!clienteDAO.clienteExiste(dniCliente)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar cita");
                alerta.setHeaderText("Cita no encontrada");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no está registrado en la base de datos.");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            if (citaDAO.clienteCitas(dniCliente)) {

                List<Cita> citas = citaDAO.buscarCitas(dniCliente);
                ObservableList<Cita> citaData = FXCollections.observableArrayList(citas);
                tablaCitas.setItems(citaData);

                colIdCita.setCellValueFactory(new PropertyValueFactory<>("idCita"));
                colNombreVet.setCellValueFactory(new PropertyValueFactory<>("nombreVeterinario"));
                colApellidoUnoVet.setCellValueFactory(new PropertyValueFactory<>("apellidoUnoVeterinario"));
                colApellidoDosVet.setCellValueFactory(new PropertyValueFactory<>("apellidoDosVeterinario"));
                colNombreMascota.setCellValueFactory(new PropertyValueFactory<>("nombreMascota"));
                colFechaCita.setCellValueFactory(new PropertyValueFactory<>("fechaCita"));
                colHoraCita.setCellValueFactory(new PropertyValueFactory<>("horaCita"));
                colTipoConsulta.setCellValueFactory(new PropertyValueFactory<>("tipoConsulta"));
                colDiagnostico.setCellValueFactory(new PropertyValueFactory<>("diagnostico"));
                colTratamiento.setCellValueFactory(new PropertyValueFactory<>("tratamiento"));
                colMedicacion.setCellValueFactory(new PropertyValueFactory<>("medicacion"));
                colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

                // ajustar el ancho de las columnas al contenido
                tablaCitas.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                TableColumn[] columnas = {colIdCita, colNombreVet, colApellidoUnoVet, colApellidoDosVet, colNombreMascota,
                    colFechaCita, colHoraCita, colTipoConsulta, colDiagnostico,
                    colTratamiento, colMedicacion, colPrecio};
                double[] anchos = {0.1, 0.2, 0.2, 0.2, 0.2, 0.2, 0.2, 0.3, 0.3, 0.4, 0.4, 0.2};

                for (int i = 0; i < columnas.length; i++) {
                    columnas[i].prefWidthProperty().bind(tablaCitas.widthProperty().multiply(anchos[i]));
                }

            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar cita");
                alerta.setHeaderText("Cita no encontrada");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no tiene asignada ninguna cita.");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
            }

        } catch (Exception e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }

    }

    /**
     * Crea el origen de datos para el informe de citas utilizando la cita
     * seleccionada como fuente de datos.
     *
     * @param citaSeleccionada La cita seleccionada para generar el informe.
     * @return Un objeto JRBeanCollectionDataSource que contiene la lista de
     * citas, con la cita seleccionada como única entrada.
     */
    private JRBeanCollectionDataSource crearOrigenDatos(Cita citaSeleccionada) {
        List<Cita> listaCitas = new ArrayList<>();
        listaCitas.add(citaSeleccionada);
        JRBeanCollectionDataSource origenDatos = new JRBeanCollectionDataSource(listaCitas);
        return origenDatos;
    }

    /**
     *
     * Genera un informe de una cita seleccionada en la tabla de citas.
     *
     * @param event el evento que desencadena la generación del informe.
     * @throws JRException si hay un error al generar el informe con
     * JasperReports.
     */
    @FXML
    public void handleGenerarInformeCitas(ActionEvent event) throws JRException {

        Cita citaSeleccionada = tablaCitas.getSelectionModel().getSelectedItem();
        if (citaSeleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora seleccionar una cita");
            alerta.setHeaderText("Error en la selección de la cita");
            alerta.setContentText("Debe seleccionar una cita de la tabla.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        try {
            // cargar el archivo de reporte Jasper

            InputStream inputStream = getClass().getResourceAsStream("/reports/informeCita.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // crear un mapa de parámetros para el reporte
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("dniCliente", citaSeleccionada.getDniCliente());
            parametros.put("nombreCliente", citaSeleccionada.getNombreCliente());
            parametros.put("apellidoUnoCliente", citaSeleccionada.getApellidoUnoCliente());
            parametros.put("apellidoDosCliente", citaSeleccionada.getApellidoDosCliente());
            parametros.put("nombreVeterinario", citaSeleccionada.getNombreVeterinario());
            parametros.put("apellidoUnoVeterinario", citaSeleccionada.getApellidoUnoVeterinario());
            parametros.put("apellidoDosVeterinario", citaSeleccionada.getApellidoDosVeterinario());
            parametros.put("nombreMascota", citaSeleccionada.getNombreMascota());
            parametros.put("fechaCita", citaSeleccionada.getFechaCita());
            parametros.put("horaCita", citaSeleccionada.getHoraCita());
            parametros.put("tipoConsulta", citaSeleccionada.getTipoConsulta());
            parametros.put("diagnostico", citaSeleccionada.getDiagnostico());
            parametros.put("tratamiento", citaSeleccionada.getTratamiento());
            parametros.put("medicacion", citaSeleccionada.getMedicacion());
            parametros.put("precio", citaSeleccionada.getPrecio());

            // crear el origen de datos del reporte
            JRBeanCollectionDataSource origenDatos = crearOrigenDatos(citaSeleccionada);

            // generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, origenDatos);

            // visualizar el informe en una ventana de diálogo
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Factura - Cita " + citaSeleccionada.getIdCita());
            viewer.setVisible(true);

        } catch (JRException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora de generar la cita");
            alerta.setHeaderText("Error en la cita");
            alerta.setContentText("Error al generar la cita");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            System.out.println("Error al generar la cita " + e.getMessage());
            e.printStackTrace();
        }

    }

}
