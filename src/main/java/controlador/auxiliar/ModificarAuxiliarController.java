package controlador.auxiliar;

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
import modelo.Auxiliar;
import modelo.dao.AuxiliarDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de modificar auxiliares, la cual permite la
 * gestión de la modificación de auxiliares de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class ModificarAuxiliarController {

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
    private TextField field_dniAuxiliar;
    @FXML
    private TextField field_especialidadAuxiliar;
    @FXML
    private TextField field_salarioAuxiliar;
    @FXML
    private TextField field_nombreAuxiliar;
    @FXML
    private TextField field_apellidoUnoAuxiliar;
    @FXML
    private TextField field_apellidoDosAuxiliar;
    @FXML
    private DatePicker fechanacAuxiliar;
    @FXML
    private ComboBox<Auxiliar.Turno> turnoAuxiliar;
    @FXML
    private TextField field_telefonoAuxiliar;
    @FXML
    private TextField field_direccionAuxiliar;
    @FXML
    private TextField field_cpAuxiliar;
    @FXML
    private TextField field_correoAuxiliar;


    public AuxiliarDAO auxiliarDAO;

    /**
     * Inicializa los elementos de la vista de cargando los auxiliares.
     */
    public void initialize() {
        ObservableList<Auxiliar.Turno> opciones = FXCollections.observableArrayList(Auxiliar.Turno.values());
        turnoAuxiliar.setItems(opciones);

        auxiliarDAO = new AuxiliarDAO();
        List<Auxiliar> auxiliares = auxiliarDAO.obtenerAuxiliares();
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
    }

    /**
     *
     * Maneja el evento de selección de un auxiliar en la tablaAuxiliares.
     * Actualiza los campos de texto y los campos de selección en la vista para
     * mostrar la información del auxiliar seleccionado.
     *
     * @param event el evento de selección del mouse
     */
    @FXML
    private void seleccionar(MouseEvent event) {
        Auxiliar auxiliarSeleccionado = tablaAuxiliares.getSelectionModel().getSelectedItem();
        if (auxiliarSeleccionado != null) {
            field_especialidadAuxiliar.setText(auxiliarSeleccionado.getEspecialidad());
            field_dniAuxiliar.setText(auxiliarSeleccionado.getDni());
            field_salarioAuxiliar.setText(String.valueOf(auxiliarSeleccionado.getSalario()));
            field_nombreAuxiliar.setText(auxiliarSeleccionado.getNombre());
            field_apellidoUnoAuxiliar.setText(auxiliarSeleccionado.getApellidoUno());
            field_apellidoDosAuxiliar.setText(auxiliarSeleccionado.getApellidoDos());
            fechanacAuxiliar.setValue(auxiliarSeleccionado.getFechaNac().toLocalDate());
            turnoAuxiliar.setValue(auxiliarSeleccionado.getTurno());
            field_telefonoAuxiliar.setText(String.valueOf(auxiliarSeleccionado.getTelefono()));
            field_direccionAuxiliar.setText(auxiliarSeleccionado.getDireccion());
            field_cpAuxiliar.setText(String.valueOf(auxiliarSeleccionado.getCp()));
            field_correoAuxiliar.setText(auxiliarSeleccionado.getEmail());

        }
    }

    /**
     * Controlador de la ventana de modificar auxiliar. Este controlador maneja
     * los eventos de la ventana de modificar los campos de los auxiliares a
     * través de la interacción con la base de datos mediante el objeto
     * AuxiliarDAO.
     *
     * @param event Evento que dispara la acción.
     */
    public void handleModificarAuxiliares(ActionEvent event) {

        Auxiliar auxiliarSeleccionado = tablaAuxiliares.getSelectionModel().getSelectedItem();

        try {
            //obtengo los datos del formulario
            String dni = field_dniAuxiliar.getText();
            String especialidad = field_especialidadAuxiliar.getText();
            String salario = field_salarioAuxiliar.getText();
            String nombre = field_nombreAuxiliar.getText();
            String apellidoUno = field_apellidoUnoAuxiliar.getText();
            String apellidoDos = field_apellidoDosAuxiliar.getText();
            LocalDate fechaNac = fechanacAuxiliar.getValue();
            Auxiliar.Turno turno = turnoAuxiliar.getValue();
            String telefono = field_telefonoAuxiliar.getText();
            String direccion = field_direccionAuxiliar.getText();
            String cp = field_cpAuxiliar.getText();
            String correo = field_correoAuxiliar.getText();

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
                alerta.setTitle("Error al modificar auxiliar");
                alerta.setHeaderText("Campos vacios");
                alerta.setContentText("Todos los campos deben estar rellenados");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            // si la fecha no es válida
            if (!Utils.validarFechaDatePicker(fechanacAuxiliar)) {
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

            // Verifica que el auxiliar no existe en la base de datos
            if (!auxiliarDAO.auxiliarExiste(dni)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al modificar auxiliar");
                alerta.setHeaderText("Auxiliar no registrado");
                alerta.setContentText("El auxilair con DNI " + dni + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            int telefonoInt = Integer.parseInt(telefono);
            int cpInt = Integer.parseInt(cp);
            BigDecimal salarioDecimal = new BigDecimal(salario);
            auxiliarSeleccionado.setDni(dni);
            auxiliarSeleccionado.setEspecialidad(especialidad);
            auxiliarSeleccionado.setSalario(salarioDecimal);
            auxiliarSeleccionado.setNombre(nombre);
            auxiliarSeleccionado.setApellidoUno(apellidoUno);
            auxiliarSeleccionado.setApellidoDos(apellidoDos);
            auxiliarSeleccionado.setFechaNac(java.sql.Date.valueOf(fechaNac));
            auxiliarSeleccionado.setTurno(Auxiliar.Turno.valueOf(turno.toString()));
            auxiliarSeleccionado.setTelefono(telefonoInt);
            auxiliarSeleccionado.setDireccion(direccion);
            auxiliarSeleccionado.setCp(cpInt);
            auxiliarSeleccionado.setEmail(correo);

            auxiliarDAO.modificarAuxiliar(auxiliarSeleccionado);
            limpiarCampos();
            tablaAuxiliares.refresh();

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
     * Modificar Auxiliar.
     */
    @FXML
    public void limpiarCampos() {
        field_dniAuxiliar.setText("");
        field_especialidadAuxiliar.setText("");
        field_salarioAuxiliar.setText("");
        field_nombreAuxiliar.setText("");
        field_apellidoUnoAuxiliar.setText("");
        field_apellidoDosAuxiliar.setText("");
        fechanacAuxiliar.setValue(null);
        turnoAuxiliar.setValue(null);
        field_telefonoAuxiliar.setText("");
        field_direccionAuxiliar.setText("");
        field_cpAuxiliar.setText("");
        field_correoAuxiliar.setText("");
    }

}
