package controlador.veterinario;

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
import modelo.Veterinario;
import modelo.dao.VeterinarioDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de modificar veterinarios/as, la cual permite la
 * gestión de la modificación de veterinarios/as de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class ModificarVeterinarioController {

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
    private TextField field_dniVeterinario;
    @FXML
    private TextField field_especialidadVeterinario;
    @FXML
    private TextField field_salarioVeterinario;
    @FXML
    private TextField field_nombreVeterinario;
    @FXML
    private TextField field_apellidoUnoVeterinario;
    @FXML
    private TextField field_apellidoDosVeterinario;
    @FXML
    private DatePicker fechanacVeterinario;
    @FXML
    private ComboBox<Veterinario.Turno> turnoVeterinario;
    @FXML
    private TextField field_telefonoVeterinario;
    @FXML
    private TextField field_direccionVeterinario;
    @FXML
    private TextField field_cpVeterinario;
    @FXML
    private TextField field_correoVeterinario;

    public VeterinarioDAO veterinarioDAO;

    /**
     * Inicializa los elementos de la vista de cargando los veterinarios/as.
     */
    public void initialize() {
        ObservableList<Veterinario.Turno> opciones = FXCollections.observableArrayList(Veterinario.Turno.values());
        turnoVeterinario.setItems(opciones);

        veterinarioDAO = new VeterinarioDAO();
        List<Veterinario> veterinarios = veterinarioDAO.obtenerVeterinarios();
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
    }

    /**
     *
     * Maneja el evento de selección de un veterinario/a en la
     * tablaVeterinarios. Actualiza los campos de texto y los campos de
     * selección en la vista para mostrar la información del veterinario/a
     * seleccionado.
     *
     * @param event el evento de selección del mouse
     */
    @FXML
    private void seleccionar(MouseEvent event) {
        Veterinario veterinarioSeleccionado = tablaVeterinarios.getSelectionModel().getSelectedItem();
        if (veterinarioSeleccionado != null) {
            field_especialidadVeterinario.setText(veterinarioSeleccionado.getEspecialidad());
            field_dniVeterinario.setText(veterinarioSeleccionado.getDni());
            field_salarioVeterinario.setText(String.valueOf(veterinarioSeleccionado.getSalario()));
            field_nombreVeterinario.setText(veterinarioSeleccionado.getNombre());
            field_apellidoUnoVeterinario.setText(veterinarioSeleccionado.getApellidoUno());
            field_apellidoDosVeterinario.setText(veterinarioSeleccionado.getApellidoDos());
            fechanacVeterinario.setValue(veterinarioSeleccionado.getFechaNac().toLocalDate());
            turnoVeterinario.setValue(veterinarioSeleccionado.getTurno());
            field_telefonoVeterinario.setText(String.valueOf(veterinarioSeleccionado.getTelefono()));
            field_direccionVeterinario.setText(veterinarioSeleccionado.getDireccion());
            field_cpVeterinario.setText(String.valueOf(veterinarioSeleccionado.getCp()));
            field_correoVeterinario.setText(veterinarioSeleccionado.getEmail());

        }
    }

    /**
     * Controlador de la ventana de modificar veterinario/a. Este controlador
     * maneja los eventos de la ventana de modificar los campos de los
     * veterinarios/as a través de la interacción con la base de datos mediante
     * el objeto VeterinarioDAO.
     *
     * @param event Evento que dispara la acción.
     */
    public void handleModificarVeterinarios(ActionEvent event) {

        Veterinario veterinarioSeleccionado = tablaVeterinarios.getSelectionModel().getSelectedItem();

        try {
            //obtengo los datos del formulario
            String dni = field_dniVeterinario.getText();
            String especialidad = field_especialidadVeterinario.getText();
            String salario = field_salarioVeterinario.getText();
            String nombre = field_nombreVeterinario.getText();
            String apellidoUno = field_apellidoUnoVeterinario.getText();
            String apellidoDos = field_apellidoDosVeterinario.getText();
            LocalDate fechaNac = fechanacVeterinario.getValue();
            Veterinario.Turno turno = turnoVeterinario.getValue();
            String telefono = field_telefonoVeterinario.getText();
            String direccion = field_direccionVeterinario.getText();
            String cp = field_cpVeterinario.getText();
            String correo = field_correoVeterinario.getText();

            if (dni == null || dni.isEmpty()
                    || especialidad == null || especialidad.isEmpty()
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
                alerta.setTitle("Error al modificar veterinario/a");
                alerta.setHeaderText("Campos vacios");
                alerta.setContentText("Todos los campos deben estar rellenados");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            // si la fecha no es válida
            if (!Utils.validarFechaDatePicker(fechanacVeterinario)) {
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
            if (!veterinarioDAO.veterinarioExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al modificar veterinario/a");
                alerta.setHeaderText("Veterinario/a no registrado/a");
                alerta.setContentText("El/la veterinario/a con DNI " + dni + " no está registrado/a en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            int telefonoInt = Integer.parseInt(telefono);
            int cpInt = Integer.parseInt(cp);
            BigDecimal salarioDecimal = new BigDecimal(salario);
            veterinarioSeleccionado.setDni(dni);
            veterinarioSeleccionado.setEspecialidad(especialidad);
            veterinarioSeleccionado.setSalario(salarioDecimal);
            veterinarioSeleccionado.setNombre(nombre);
            veterinarioSeleccionado.setApellidoUno(apellidoUno);
            veterinarioSeleccionado.setApellidoDos(apellidoDos);
            veterinarioSeleccionado.setFechaNac(java.sql.Date.valueOf(fechaNac));
            veterinarioSeleccionado.setTurno(Veterinario.Turno.valueOf(turno.toString()));
            veterinarioSeleccionado.setTelefono(telefonoInt);
            veterinarioSeleccionado.setDireccion(direccion);
            veterinarioSeleccionado.setCp(cpInt);
            veterinarioSeleccionado.setEmail(correo);

            veterinarioDAO.modificarVeterinario(veterinarioSeleccionado);
            limpiarCampos();
            tablaVeterinarios.refresh();


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
     * Modificar Veterinario/a.
     */
    @FXML
    public void limpiarCampos() {
        field_dniVeterinario.setText("");
        field_especialidadVeterinario.setText("");
        field_salarioVeterinario.setText("");
        field_nombreVeterinario.setText("");
        field_apellidoUnoVeterinario.setText("");
        field_apellidoDosVeterinario.setText("");
        fechanacVeterinario.setValue(null);
        turnoVeterinario.setValue(null);
        field_telefonoVeterinario.setText("");
        field_direccionVeterinario.setText("");
        field_cpVeterinario.setText("");
        field_correoVeterinario.setText("");
    }

}
