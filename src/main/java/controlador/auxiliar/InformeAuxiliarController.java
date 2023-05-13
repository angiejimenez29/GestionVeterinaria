package controlador.auxiliar;

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
import modelo.Auxiliar;
import modelo.dao.AuxiliarDAO;
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
 * de auxiliares.
 *
 * @author Nuria Vázquez 
 */
public class InformeAuxiliarController {

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

    /**
     * Controlador de la ventana de búsqueda de auxiliares. Este controlador
     * maneja los eventos de la ventana de búsqueda de auxiliares y realiza la
     * búsqueda de auxiliares a través de la interacción con la base de datos
     * mediante el objeto AuxiliarDAO. Los resultados de la búsqueda se muestran
     * en la tabla de la ventana de búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarAuxiliares(ActionEvent event) {

        // si el campo esta vacio
        if (buscarAuxiliar.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar el auxiliar");
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
            // si el auxiliar existe en la base de datos
            if (auxiliarDAO.auxiliarExiste(dniAuxiliar)) {

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
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar auxiliar");
                alerta.setHeaderText("Auxiliar no encontrado");
                alerta.setContentText("El auxiliar con DNI " + dniAuxiliar + " no está registrado en la base de datos.");
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
     * Crea el origen de datos para el informe de auxiliares utilizando el
     * auxiliar seleccionado como fuente de datos.
     *
     * @param auxiliarSeleccionado El auxiliar seleccionado para generar el
     * informe.
     * @return Un objeto JRBeanCollectionDataSource que contiene la lista de
     * auxiliares, con el auxiliar seleccionado como única entrada.
     */
    private JRBeanCollectionDataSource crearOrigenDatos(Auxiliar auxiliarSeleccionado) {
        List<Auxiliar> listaAuxiliares = new ArrayList<>();
        listaAuxiliares.add(auxiliarSeleccionado);
        JRBeanCollectionDataSource origenDatos = new JRBeanCollectionDataSource(listaAuxiliares);
        return origenDatos;
    }

    /**
     *
     * Genera un informe de un auxiliar seleccionado en la tabla de auxiliares.
     *
     * @param event el evento que desencadena la generación del informe.
     * @throws JRException si hay un error al generar el informe con
     * JasperReports.
     */
    @FXML
    public void handleGenerarInformeAuxiliares(ActionEvent event) throws JRException {

        Auxiliar auxiliarSeleccionado = tablaAuxiliares.getSelectionModel().getSelectedItem();
        if (auxiliarSeleccionado == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora seleccionar un auxiliar");
            alerta.setHeaderText("Error en la selección del auxiliar");
            alerta.setContentText("Debe seleccionar un auxiliar de la tabla.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        try {
            // cargar el archivo de reporte Jasper

            InputStream inputStream = getClass().getResourceAsStream("/reports/informeAuxiliar.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // crear un mapa de parámetros para el reporte
            Map<String, Object> parametros = new HashMap<>();

            parametros.put("dni", auxiliarSeleccionado.getDni());
            parametros.put("nombre", auxiliarSeleccionado.getNombre());
            parametros.put("apellidoUno", auxiliarSeleccionado.getApellidoUno());
            parametros.put("apellidoDos", auxiliarSeleccionado.getApellidoDos());
            parametros.put("especialidad", auxiliarSeleccionado.getEspecialidad());
            parametros.put("salario", auxiliarSeleccionado.getSalario());
            parametros.put("fechaNac", auxiliarSeleccionado.getFechaNac());
            parametros.put("telefono", auxiliarSeleccionado.getTelefono());
            parametros.put("direccion", auxiliarSeleccionado.getDireccion());
            parametros.put("cp", auxiliarSeleccionado.getCp());
            parametros.put("email", auxiliarSeleccionado.getEmail());
            parametros.put("turno", auxiliarSeleccionado.getTurno());

            // crear el origen de datos del reporte
            JRBeanCollectionDataSource origenDatos = crearOrigenDatos(auxiliarSeleccionado);

            // generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, origenDatos);

            // visualizar el informe en una ventana de diálogo
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Informe - Auxiliar " + auxiliarSeleccionado.getNombre());
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
