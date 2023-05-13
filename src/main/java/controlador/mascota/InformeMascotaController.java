package controlador.mascota;

import java.io.File;
import java.io.InputStream;
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
import modelo.Mascota;
import modelo.dao.MascotaDAO;
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
 * de mascotas.
 *
 * @author Nuria Vázquez 
 */
public class InformeMascotaController {

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

    /**
     * Crea el origen de datos para el informe de mascotas utilizando la mascota
     * seleccionada como fuente de datos.
     *
     * @param mascotaSeleccionada La mascota seleccionada para generar el
     * informe.
     * @return Un objeto JRBeanCollectionDataSource que contiene la lista de
     * mascotas, con la mascota seleccionada como única entrada.
     */
    private JRBeanCollectionDataSource crearOrigenDatos(Mascota mascotaSeleccionada) {
        List<Mascota> listaMascotas = new ArrayList<>();
        listaMascotas.add(mascotaSeleccionada);
        JRBeanCollectionDataSource origenDatos = new JRBeanCollectionDataSource(listaMascotas);
        return origenDatos;
    }

    /**
     *
     * Genera un informe de una mascota seleccionada de la tabla de mascotas.
     *
     * @param event el evento que desencadena la generación del informe.
     * @throws JRException si hay un error al generar el informe con
     * JasperReports.
     */
    @FXML
    public void handleGenerarInformeMascotas(ActionEvent event) throws JRException {

        Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();
        if (mascotaSeleccionada == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora seleccionar una mascota");
            alerta.setHeaderText("Error en la selección de la mascota");
            alerta.setContentText("Debe seleccionar una mascota de la tabla.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        try {
            // cargar el archivo de reporte Jasper
InputStream inputStream = getClass().getResourceAsStream("/reports/informeMascota.jrxml");
JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);


            // crear un mapa de parámetros para el reporte
            Map<String, Object> parametros = new HashMap<>();

            parametros.put("nombre", mascotaSeleccionada.getNombre());
            parametros.put("sexo", mascotaSeleccionada.getSexo());
            parametros.put("fechaNacimiento", mascotaSeleccionada.getFechaNacimiento());
            parametros.put("raza", mascotaSeleccionada.getRaza());
            parametros.put("especie", mascotaSeleccionada.getEspecie());
            parametros.put("dniCliente", mascotaSeleccionada.getDniCliente());

            // crear el origen de datos del reporte
            JRBeanCollectionDataSource origenDatos = crearOrigenDatos(mascotaSeleccionada);

            // generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, origenDatos);

            // visualizar el informe en una ventana de diálogo
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Informe - Mascota " + mascotaSeleccionada.getNombre());
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
