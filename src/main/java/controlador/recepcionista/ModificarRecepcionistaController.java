package controlador.recepcionista;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Recepcionista;
import modelo.dao.RecepcionistaDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de modificar recepcionistas, la cual permite la
 * gestión de la modificación de recepcionistas de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class ModificarRecepcionistaController {

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

    @FXML
    private TextField field_dniRecepcionista;
    @FXML
    private TextField field_salarioRecepcionista;
    @FXML
    private TextField field_nombreRecepcionista;
    @FXML
    private TextField field_apellidoUnoRecepcionista;
    @FXML
    private TextField field_apellidoDosRecepcionista;
    @FXML
    private DatePicker fechanacRecepcionista;
    @FXML
    private ComboBox<Recepcionista.Turno> turnoRecepcionista;
    @FXML
    private TextField field_telefonoRecepcionista;
    @FXML
    private TextField field_direccionRecepcionista;
    @FXML
    private TextField field_cpRecepcionista;
    @FXML
    private TextField field_correoRecepcionista;

    public RecepcionistaDAO recepcionistaDAO;

    /**
     * Inicializa los elementos de la vista de cargando los recepcionistas.
     */
    public void initialize() {
        ObservableList<Recepcionista.Turno> opciones = FXCollections.observableArrayList(Recepcionista.Turno.values());
        turnoRecepcionista.setItems(opciones);

        recepcionistaDAO = new RecepcionistaDAO();
        List<Recepcionista> recepcionistas = recepcionistaDAO.obtenerRecepcionistas();
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
    }

    /**
     *
     * Maneja el evento de selección de un recepcionista en la
     * tablaRecepcionistas. Actualiza los campos de texto y los campos de
     * selección en la vista para mostrar la información del recepcionista
     * seleccionado.
     *
     * @param event el evento de selección del mouse
     */
    @FXML
    private void seleccionar(MouseEvent event) {
        Recepcionista recepcionistaSeleccionado = tablaRecepcionistas.getSelectionModel().getSelectedItem();
        if (recepcionistaSeleccionado != null) {
            field_dniRecepcionista.setText(recepcionistaSeleccionado.getDni());
            field_salarioRecepcionista.setText(String.valueOf(recepcionistaSeleccionado.getSalario()));
            field_nombreRecepcionista.setText(recepcionistaSeleccionado.getNombre());
            field_apellidoUnoRecepcionista.setText(recepcionistaSeleccionado.getApellidoUno());
            field_apellidoDosRecepcionista.setText(recepcionistaSeleccionado.getApellidoDos());
            fechanacRecepcionista.setValue(recepcionistaSeleccionado.getFechaNac().toLocalDate());
            turnoRecepcionista.setValue(recepcionistaSeleccionado.getTurno());
            field_telefonoRecepcionista.setText(String.valueOf(recepcionistaSeleccionado.getTelefono()));
            field_direccionRecepcionista.setText(recepcionistaSeleccionado.getDireccion());
            field_cpRecepcionista.setText(String.valueOf(recepcionistaSeleccionado.getCp()));
            field_correoRecepcionista.setText(recepcionistaSeleccionado.getEmail());

        }
    }

    /**
     * Controlador de la ventana de modificar recepcionistas. Este controlador
     * maneja los eventos de la ventana de modificar los campos de los
     * recepcionistas a través de la interacción con la base de datos mediante
     * el objeto RecepcionistaDAO.
     *
     * @param event Evento que dispara la acción.
     */
    public void handleModificarRecepcionistas(ActionEvent event) {

        Recepcionista recepcionistaSeleccionado = tablaRecepcionistas.getSelectionModel().getSelectedItem();

        try {
            //obtengo los datos del formulario
            String dni = field_dniRecepcionista.getText();
            String salario = field_salarioRecepcionista.getText();
            String nombre = field_nombreRecepcionista.getText();
            String apellidoUno = field_apellidoUnoRecepcionista.getText();
            String apellidoDos = field_apellidoDosRecepcionista.getText();
            LocalDate fechaNac = fechanacRecepcionista.getValue();
            Recepcionista.Turno turno = turnoRecepcionista.getValue();
            String telefono = field_telefonoRecepcionista.getText();
            String direccion = field_direccionRecepcionista.getText();
            String cp = field_cpRecepcionista.getText();
            String correo = field_correoRecepcionista.getText();

            if (dni == null || dni.isEmpty()
                    || salario == null || salario.isEmpty()
                    || nombre == null || nombre.isEmpty()
                    || apellidoUno == null || apellidoUno.isEmpty()
                    || apellidoDos == null || apellidoDos.isEmpty()
                    || fechaNac == null
                    || turno == null
                    || telefono == null || telefono.isEmpty()
                    || direccion == null || direccion.isEmpty()
                    || cp == null || cp.isEmpty()
                    || correo == null || correo.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al modificar recepcionista");
                alerta.setHeaderText("Campos vacios");
                alerta.setContentText("Todos los campos deben estar rellenados");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            // si la fecha no es válida
            if (!Utils.validarFechaDatePicker(fechanacRecepcionista)) {
                Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
                return;
            }

            // si el dni no tiene 8 números enteros y una letra en mayúsculas
            if (!Utils.validarDni(dni)) {
                Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
                return;
            }

            // Verifica que el teléfono no tiene 9 dígitos    
            if (!Utils.validarTelefono(telefono)) {
                Alertas.alertTelefonoIncorrecto("Debes introducir un teléfono válido con 9 dígitos");
                return;
            }

            // Verifica que el cp no tiene 5 dígitos    
            if (!Utils.validarCp(cp)) {
                Alertas.alertCpIncorrecto("Debes introducir un cp válido con 5 dígitos");
                return;
            }

            // Verifica que el correo no tiene el formato esperado  
            if (!Utils.validarEmail(correo)) {
                Alertas.alertEmailIncorrecto("Por favor, ingrese un correo electrónico válido.");
                return;
            }

            if (!Utils.validarSalario(salario)) {
                Alertas.alertSalarioIncorrecto("Debes introducir un salario con sólo 2 decimales o ninguno");
                return;
            }

            // Verifica que el cliente no existe en la base de datos
            if (!recepcionistaDAO.recepcionistaExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al modificar recepcionista");
                alerta.setHeaderText("Recepcionista no registrado");
                alerta.setContentText("El recepcionista con DNI " + dni + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            int telefonoInt = Integer.parseInt(telefono);
            int cpInt = Integer.parseInt(cp);
            BigDecimal salarioDecimal = new BigDecimal(salario);
            recepcionistaSeleccionado.setDni(dni);
            recepcionistaSeleccionado.setSalario(salarioDecimal);
            recepcionistaSeleccionado.setNombre(nombre);
            recepcionistaSeleccionado.setApellidoUno(apellidoUno);
            recepcionistaSeleccionado.setApellidoDos(apellidoDos);
            recepcionistaSeleccionado.setFechaNac(java.sql.Date.valueOf(fechaNac));
            recepcionistaSeleccionado.setTurno(Recepcionista.Turno.valueOf(turno.toString()));
            recepcionistaSeleccionado.setTelefono(telefonoInt);
            recepcionistaSeleccionado.setDireccion(direccion);
            recepcionistaSeleccionado.setCp(cpInt);
            recepcionistaSeleccionado.setEmail(correo);

            recepcionistaDAO.modificarRecepcionista(recepcionistaSeleccionado);
            limpiarCampos();
            tablaRecepcionistas.refresh();

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("Error en campos numéricos");
            alerta.setContentText("Debe introducir un número entero para telefono y CP ");
            Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
            stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            alerta.showAndWait();
            return;

        }

    }

    /**
     * Este método se encarga de borrar los campos de texto en la ventana de
     * Modificar Recepcionista.
     */
    @FXML
    public void limpiarCampos() {
        field_dniRecepcionista.setText("");
        field_salarioRecepcionista.setText("");
        field_nombreRecepcionista.setText("");
        field_apellidoUnoRecepcionista.setText("");
        field_apellidoDosRecepcionista.setText("");
        fechanacRecepcionista.setValue(null);
        turnoRecepcionista.setValue(null);
        field_telefonoRecepcionista.setText("");
        field_direccionRecepcionista.setText("");
        field_cpRecepcionista.setText("");
        field_correoRecepcionista.setText("");
    }

}
