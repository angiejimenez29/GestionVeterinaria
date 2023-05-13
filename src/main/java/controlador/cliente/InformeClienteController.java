package controlador.cliente;

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
import modelo.Cliente;
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
 * de clientes.
 *
 * @author Nuria Vázquez 
 */
public class InformeClienteController {

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, Integer> colIdCliente;
    @FXML
    private TableColumn<Cliente, String> colDNI;
    @FXML
    private TableColumn<Cliente, String> colSeguroCliente;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colApellidoUno;
    @FXML
    private TableColumn<Cliente, String> colApellidoDos;
    @FXML
    private TableColumn<Cliente, java.sql.Date> colFechaNacimiento;
    @FXML
    private TableColumn<Cliente, Integer> colTelefono;
    @FXML
    private TableColumn<Cliente, String> colDireccion;
    @FXML
    private TableColumn<Cliente, Integer> colCP;
    @FXML
    private TableColumn<Cliente, String> colPoblacion;
    @FXML
    private TableColumn<Cliente, String> colEmail;
    @FXML
    private TableColumn<Cliente, Cliente.Suscripcion> colSuscripcion;

    @FXML
    private TextField buscarCliente;

    public ClienteDAO clienteDAO;

    /**
     * Controlador de la ventana de búsqueda de clientes. Este controlador
     * maneja los eventos de la ventana de búsqueda de clientes y realiza la
     * búsqueda de clientes a través de la interacción con la base de datos
     * mediante el objeto ClienteDAO. Los resultados de la búsqueda se muestran
     * en la tabla de la ventana de búsqueda.
     *
     * @param event Evento que dispara la acción.
     */
    @FXML
    public void handleBuscarClientes(ActionEvent event) {

        // si el campo esta vacio
        if (buscarCliente.getText().isEmpty()) {

            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error al buscar al cliente");
            alerta.setHeaderText("Faltan campos obligatorios");
            alerta.setContentText("Por favor, rellene el campo dni del cliente");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        String dniCliente = buscarCliente.getText().trim();

        // si el dni no tiene 8 números enteros y una letra en mayúscula.
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {

            clienteDAO = new ClienteDAO();
            // si el cliente existe en la base de datos
            if (clienteDAO.clienteExiste(dniCliente)) {
                List<Cliente> listaClientes = clienteDAO.buscarCliente(dniCliente);
                ObservableList<Cliente> clienteData = FXCollections.observableArrayList(listaClientes);

                tablaClientes.setItems(clienteData);
                colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
                colDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
                colSeguroCliente.setCellValueFactory(new PropertyValueFactory<>("seguro"));
                colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colApellidoUno.setCellValueFactory(new PropertyValueFactory<>("apellidoUno"));
                colApellidoDos.setCellValueFactory(new PropertyValueFactory<>("apellidoDos"));
                colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
                colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
                colCP.setCellValueFactory(new PropertyValueFactory<>("cp"));
                colPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));
                colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                colSuscripcion.setCellValueFactory(new PropertyValueFactory<>("suscripcion"));

                // ajustar el ancho de las columnas al contenido
                tablaClientes.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

                TableColumn[] columnas = {colIdCliente, colDNI, colSeguroCliente, colNombre, colApellidoUno, colApellidoDos, colFechaNacimiento, colTelefono, colDireccion, colCP, colPoblacion, colEmail, colSuscripcion};
                double[] anchos = {0.05, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.1, 0.2, 0.08, 0.7, 0.7, 0.05};

                for (int i = 0; i < columnas.length; i++) {
                    columnas[i].prefWidthProperty().bind(tablaClientes.widthProperty().multiply(anchos[i]));
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al buscar cliente");
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
     * Crea el origen de datos para el informe de clientes utilizando el cliente
     * seleccionado como fuente de datos.
     *
     * @param clienteSeleccionado El cliente seleccionado para generar el
     * informe.
     * @return Un objeto JRBeanCollectionDataSource que contiene la lista de
     * clientes, con el cliente seleccionado como única entrada.
     */
    private JRBeanCollectionDataSource crearOrigenDatos(Cliente clienteSeleccionado) {
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(clienteSeleccionado);
        JRBeanCollectionDataSource origenDatos = new JRBeanCollectionDataSource(listaClientes);
        return origenDatos;
    }

    /**
     *
     * Genera un informe de un cliente seleccionado en la tabla de clientes.
     *
     * @param event el evento que desencadena la generación del informe.
     * @throws JRException si hay un error al generar el informe con
     * JasperReports.
     */
    @FXML
    public void handleGenerarInformeClientes(ActionEvent event) throws JRException {

        Cliente clienteSeleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (clienteSeleccionado == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error a la hora seleccionar un cliente");
            alerta.setHeaderText("Error en la selección del cliente");
            alerta.setContentText("Debe seleccionar un cliente de la tabla.");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;
        }

        try {
            // cargar el archivo de reporte Jasper

            InputStream inputStream = getClass().getResourceAsStream("/reports/informeCliente.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            // crear un mapa de parámetros para el reporte
            Map<String, Object> parametros = new HashMap<>();

            parametros.put("dni", clienteSeleccionado.getDni());
            parametros.put("nombre", clienteSeleccionado.getNombre());
            parametros.put("apellidoUno", clienteSeleccionado.getApellidoUno());
            parametros.put("apellidoDos", clienteSeleccionado.getApellidoDos());
            parametros.put("seguro", clienteSeleccionado.getSeguro());
            parametros.put("fechaNac", clienteSeleccionado.getFechaNac());
            parametros.put("telefono", clienteSeleccionado.getTelefono());
            parametros.put("direccion", clienteSeleccionado.getDireccion());
            parametros.put("cp", clienteSeleccionado.getCp());
            parametros.put("poblacion", clienteSeleccionado.getPoblacion());
            parametros.put("email", clienteSeleccionado.getEmail());
            parametros.put("suscripcion", clienteSeleccionado.getSuscripcion());

            // crear el origen de datos del reporte
            JRBeanCollectionDataSource origenDatos = crearOrigenDatos(clienteSeleccionado);

            // generar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, origenDatos);

            // visualizar el informe en una ventana de diálogo
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Informe - Cliente " + clienteSeleccionado.getNombre());
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
