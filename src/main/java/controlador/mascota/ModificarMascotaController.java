package controlador.mascota;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import modelo.Mascota;
import modelo.dao.MascotaDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de modificar mascotas, la cual permite la
 * gestión de la modificación de mascotas de un cliente de la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class ModificarMascotaController implements Initializable {

    private MascotaDAO mascotaDAO;

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
    private TableColumn<Mascota, String> colDniCliente;

    @FXML
    private TextField field_nombreMascota;

    @FXML
    private ComboBox<Mascota.Sexo> sexoMascotas;

    @FXML
    private TextField razaMascota;

    @FXML
    private TextField field_especieMascota;

    @FXML
    private DatePicker fechanacMascota;

    @FXML
    private TextField dnicliMascota;

    private ObservableList<Mascota> mascotaData;

    /**
     * Inicializa los elementos de la vista de cargando las mascotas.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Mascota.Sexo> opciones = FXCollections.observableArrayList(Mascota.Sexo.values());
        sexoMascotas.setItems(opciones);

        mascotaDAO = new MascotaDAO();
        List<Mascota> mascotas = mascotaDAO.obtenerMascotas();
        mascotaData = FXCollections.observableArrayList(mascotas);
        tablaMascotas.setItems(mascotaData);
        colIdMascota.setCellValueFactory(new PropertyValueFactory<>("idMascota"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colDniCliente.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
        tablaMascotas.setItems(FXCollections.observableArrayList(mascotas));
    }

    /**
     *
     * Maneja el evento de selección de una mascota en la tablaMascotas.
     * Actualiza los campos de texto y los campos de selección en la vista para
     * mostrar la información de la mascota seleccionada.
     *
     * @param event el evento de selección del mouse
     */
    @FXML
    private void seleccionar(MouseEvent event) {
        Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();
        // los campos no son nulos
        if (mascotaSeleccionada != null) {
            field_nombreMascota.setText(mascotaSeleccionada.getNombre());
            razaMascota.setText(mascotaSeleccionada.getRaza());
            field_especieMascota.setText(mascotaSeleccionada.getEspecie());
            dnicliMascota.setText(mascotaSeleccionada.getDniCliente());
            sexoMascotas.setValue(mascotaSeleccionada.getSexo());
            fechanacMascota.setValue(mascotaSeleccionada.getFechaNacimiento().toLocalDate());

        }
    }

    /**
     * Controlador de la ventana de modificar mascotas. Este controlador maneja
     * los eventos de la ventana de modificar los camposde las mascotas a través
     * de la interacción con la base de datos mediante el objeto MascotaDAO.
     *
     * @param event Evento que dispara la acción.
     */
    public void handleModificarMascotas(ActionEvent event) {
        Mascota mascotaSeleccionada = tablaMascotas.getSelectionModel().getSelectedItem();

        try {

            //obtengo los datos del formulario
            String nombre = field_nombreMascota.getText();
            String especie = field_especieMascota.getText();
            Mascota.Sexo sexo = sexoMascotas.getValue();
            String raza = razaMascota.getText();
            LocalDate fechaNac = fechanacMascota.getValue();
            String dniCliente = dnicliMascota.getText().trim();

            // Verifica que todos los campos sean null
            if (nombre == null || nombre.isEmpty() || especie == null || especie.isEmpty() || sexo == null || raza == null || raza.isEmpty() || fechaNac == null || dniCliente == null || dniCliente.isEmpty()) {

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al modificar mascota");
                alerta.setHeaderText("Campos vacios");
                alerta.setContentText("Seleccione una fila o rellene todos los campos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            // si la fecha no es válida
            if (!Utils.validarFechaLocalDate(fechaNac)) {
                Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
                return;
            }

            // si el dni no tiene 8 números enteros y una letra en mayúsculas
            if (!Utils.validarDni(dniCliente)) {
                Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
                return;
            }

            // Verifica que el cliente con el DNI no exista en la base de datos
            if (!mascotaDAO.clienteExiste(dniCliente)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al modificar mascota");
                alerta.setHeaderText("Cliente no registrado");
                alerta.setContentText("El cliente con DNI " + dniCliente + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();
                return;
            }

            mascotaSeleccionada.setNombre(nombre);
            mascotaSeleccionada.setRaza(raza);
            mascotaSeleccionada.setEspecie(especie);
            mascotaSeleccionada.setSexo(sexo);
            java.sql.Date nuevaFechaNacimiento = java.sql.Date.valueOf(fechaNac);
            mascotaSeleccionada.setFechaNacimiento(nuevaFechaNacimiento);
            mascotaSeleccionada.setDniCliente(dniCliente);
            mascotaDAO.modificarMascota(mascotaSeleccionada);
            limpiarCampos();
            tablaMascotas.refresh();


        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }

    }

    /**
     * Este método se encarga de borrar los campos de texto en la ventana de
     * Modificar Mascota.
     */
    @FXML
    public void limpiarCampos() {
        field_nombreMascota.setText("");
        field_especieMascota.setText("");
        razaMascota.setText("");
        fechanacMascota.setValue(null);
        dnicliMascota.setText("");
        sexoMascotas.setValue(null);
    }
}
