package utils;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * Esta clase contiene métodos estáticos para mostrar alertas personalizadas en
 * la aplicación.
 *
 * Los métodos de esta clase devuelven una instancia de la clase Alert.
 *
 * @author Nuria Vázquez 
 */
public class Alertas {

    /**
     *
     * Muestra una alerta de error con un mensaje personalizado en caso de
     * SQLException.
     *
     * @param mensaje El mensaje personalizado que se mostrará en la alerta.
     * @return Una instancia de la clase Alert.
     */
    public static Alert alertSqlException(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error en la base de datos");
        alerta.setHeaderText("SQLException");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;
    }

    /**
     *
     * Muestra una alerta de información con un mensaje personalizado en caso de
     * autenticación exitosa.
     *
     * @param mensaje El mensaje personalizado que se mostrará en la alerta.
     * @return Una instancia de la clase Alert.
     */
    public static Alert AlertLogin(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setHeaderText("Autenticación exitosa");
        alert.setContentText("Bienvenido " + mensaje);
        Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("/img/logo_intelipet.png"));
        alert.showAndWait();
        return alert;
    }

    /**
     *
     * Muestra una alerta de error con un mensaje personalizado en caso de error
     * de autenticación.
     *
     * @param mensaje El mensaje personalizado que se mostrará en la alerta.
     * @return Una instancia de la clase Alert.
     */
    public static Alert AlertErrorLogin(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("/img/logo_intelipet.png"));
        alert.setTitle("Error de autenticación");
        alert.setHeaderText("Usuario o contraseña incorrectos");
        alert.setContentText(mensaje);
        alert.showAndWait();
        return alert;
    }

    /**
     *
     * Muestra una alerta de error con un mensaje personalizado en caso de que
     * falten campos obligatorios.
     *
     * @param mensaje El mensaje personalizado que se mostrará en la alerta.
     * @return Una instancia de la clase Alert.
     */
    public static Alert alertCamposVacios(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Faltan campos obligatorios");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;
    }

    /**
     *
     * Muestra una alerta de error con un mensaje personalizado en caso de que
     * la fecha de nacimiento no sea válida.
     *
     * @param mensaje El mensaje personalizado que se mostrará en la alerta.
     * @return Una instancia de la clase Alert.
     */
    public static Alert alertFechaIncorrecta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error en la fecha de nacimiento");
        alerta.setHeaderText("La fecha no es válida");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;
    }

    public static Alert alertHoraIncorrecta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error en la hora");
        alerta.setHeaderText("La hora no es correcta");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;
    }

    /**
     *
     * Crea una alerta de error para DNI inválido.
     *
     * @param mensaje el mensaje de error que se mostrará en la alerta.
     * @return la alerta creada.
     */
    public static Alert alertDniIncorrecto(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error en el DNI");
        alerta.setHeaderText("El DNI no es válido");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;
    }

    /**
     *
     * Crea una alerta de error para número de teléfono inválido.
     *
     * @param mensaje el mensaje de error que se mostrará en la alerta.
     * @return la alerta creada.
     */
    public static Alert alertTelefonoIncorrecto(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al modificar cliente");
        alerta.setHeaderText("Teléfono no válido");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;
    }

    /**
     *
     * Crea una alerta de error para dirección de correo electrónico inválida.
     *
     * @param mensaje el mensaje de error que se mostrará en la alerta.
     * @return la alerta creada.
     */
    public static Alert alertEmailIncorrecto(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error al agregar el correo");
        alert.setHeaderText("Formato de correo electrónico inválido");
        alert.setContentText(mensaje);
        Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alert.showAndWait();
        return alert;
    }

    /**
     *
     * Crea una alerta de error para código postal inválido.
     *
     * @param mensaje el mensaje de error que se mostrará en la alerta.
     * @return la alerta creada.
     */
    public static Alert alertCpIncorrecto(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al modificar cliente");
        alerta.setHeaderText("Cp no válido");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;
    }

    /**
     *
     * Crea una alerta de error para salario inválido.
     *
     * @param mensaje el mensaje de error que se mostrará en la alerta.
     * @return la alerta creada.
     */
    public static Alert alertSalarioIncorrecto(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error al modificar veterinario/a");
        alerta.setHeaderText("Salario no válido");
        alerta.setContentText(mensaje);
        Stage stageAlert = (Stage) alerta.getDialogPane().getScene().getWindow();
        stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        alerta.showAndWait();
        return alerta;

    }

}
