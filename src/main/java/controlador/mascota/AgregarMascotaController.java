package controlador.mascota;

import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Mascota;
import modelo.dao.MascotaDAO;
import utils.Alertas;
import utils.Utils;

/**
 * Esta clase controla la vista de agregar mascotas, la cual permite agregar una
 * nueva mascota a la base de datos.
 *
 * @author Nuria Vázquez 
 */
public class AgregarMascotaController {

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

    public MascotaDAO mascotaDAO;

    public AgregarMascotaController() {
        this.mascotaDAO = new MascotaDAO();
    }

    /**
     * Inicializa los elementos de la vista, como el ComboBox de sexo.
     */
    public void initialize() {
        ObservableList<Mascota.Sexo> opciones = FXCollections.observableArrayList(Mascota.Sexo.values());
        sexoMascotas.setItems(opciones);
    }

    /**
     * Controlador de la ventana de Agregar mascotas. Este controlador maneja
     * los eventos de la ventana de agregar mascotas y realiza la acción de
     * agregarlas a través de la interacción con la base de datos mediante el
     * objeto MascotaDAO.
     *
     * @param event Evento que dispara la acción.
     * @throws ClassNotFoundException si la clase MascotaDAO no se encuentra en
     * el classpath.
     */
    public void handleAgregarMascotas(ActionEvent event) throws ClassNotFoundException {

        // si algunos de los campos estan vacios
        if (field_nombreMascota.getText().isEmpty()
                || field_especieMascota.getText().isEmpty()
                || sexoMascotas.getValue() == null
                || razaMascota.getText().isEmpty()
                || fechanacMascota.getValue() == null
                || dnicliMascota.getText().isEmpty()) {
            Alertas.alertCamposVacios("Por favor, rellene todos los campos obligatorios");
            return;
        }

        // si la fecha no es válida
        if (!Utils.validarFechaDatePicker(fechanacMascota)) {
            Alertas.alertFechaIncorrecta("La fecha de nacimiento tiene que ser anterior a la fecha actual");
            return;
        }

        String nombre = field_nombreMascota.getText();
        String especie = field_especieMascota.getText();
        Mascota.Sexo sexo = sexoMascotas.getValue();
        String raza = razaMascota.getText();
        LocalDate fechaNac = fechanacMascota.getValue();
        String dniCliente = dnicliMascota.getText();

        // si el dni no tiene 8 números enteros y una letra en mayúsculas
        if (!Utils.validarDni(dniCliente)) {
            Alertas.alertDniIncorrecto("El DNI debe tener 8 números enteros y una letra en mayúscula");
            return;
        }

        try {
            // Validar que el cliente no existe en la base de datos
            if (!mascotaDAO.clienteExiste(dniCliente)) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error al agregar mascota");
                alerta.setHeaderText("El cliente no está registrado");
                alerta.setContentText("El cliente con el DNI " + dniCliente + " no está registrado en la base de datos");
                Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alerta.showAndWait();

            } else {
                // Agregar la mascota si no existe previamente
                Mascota mascota = new Mascota();
                mascota.setNombre(nombre);
                mascota.setEspecie(especie);
                mascota.setSexo(Mascota.Sexo.valueOf(sexo.toString()));
                mascota.setFechaNacimiento(java.sql.Date.valueOf(fechaNac));
                mascota.setRaza(raza);
                mascota.setDniCliente(dniCliente);
                mascotaDAO.agregarMascota(mascota);
                limpiarCampos();

            }

        } catch (SQLException e) {
            Alertas.alertSqlException("Error durante la conexión, acceso o manipulación de la base de datos");
            System.out.println("Resultado de la excepción: " + e.getMessage());
        }

    }

    /**
     * Este método se encarga de borrar los campos de texto en la ventana de
     * Agregar Mascota.
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
