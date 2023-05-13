package controlador.login;

import controlador.seleccionTablas.SeleccionTablasDireccionController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.dao.conexion.Conexion;
import static modelo.dao.conexion.Conexion.connection;

/**
 * Clase que representa el controlador de la vista de inicio de sesión con Login
 * director
 *
 * @author Nuria Vázquez 
 */
public class LoginDireccionController {

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;

    @FXML
    private ToggleButton mostrarPassword;

    /**
     *
     * Controla el evento de hacer clic en el botón de inicio de sesión.
     *
     * Valida las credenciales ingresadas y redirige al usuario a la vista
     * correspondiente.
     *
     * @param event El evento de acción generado por el usuario.
     *
     * @throws IOException si ocurre un error de entrada/salida al cargar la
     * vista correspondiente.
     *
     * @throws ClassNotFoundException si la clase del controlador de la vista
     * correspondiente no puede ser encontrada.
     */
    @FXML
    public void handleLogin(ActionEvent event) throws IOException, ClassNotFoundException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {

            if (username.equals("director") && password.equals("director1234")) {

                connection = Conexion.conectarDirector();
                if (connection == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de conexión");
                    alert.setHeaderText("No se pudo establecer conexión con la base de datos");
                    alert.setContentText("Por favor, verifica la conexión a la red y/o al servidor de la base de datos");
                    alert.showAndWait();
                    return;
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/seleccionTablas/SeleccionTablasDireccion.fxml"));
                Parent root = (Parent) loader.load();
                SeleccionTablasDireccionController controller = loader.getController();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exito");
                alert.setHeaderText("Autenticación exitosa");
                alert.setContentText("Bienvenido " + username);
                Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();
                stageAlert.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.showAndWait();
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                // Si el usuario o la contraseña no son válidos, muestra un mensaje de error
                Alert alert = new Alert(Alert.AlertType.ERROR);
                // Cargar la imagen del icono
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
                alert.setTitle("Error de autenticación");
                alert.setHeaderText("Usuario o contraseña incorrectos");
                alert.setContentText("Credenciales inválidas");
                alert.showAndWait();
            }
        } catch (IOException ex) {
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    /**
     *
     * Controla el evento de hacer clic en el botón de eliminar nombre de
     * usuario. Borra el contenido del campo de texto del nombre de usuario.
     *
     * @param event el evento del ratón generado al hacer clic en el botón
     */
    @FXML
    private void handleEliminarUserNameClick(MouseEvent event) {
        usernameField.setText("");
    }

    /**
     *
     * Controla el evento de hacer clic en el botón de eliminar contraseña.
     * Borra el contenido del campo de texto de la contraseña.
     *
     * @param event el evento del ratón generado al hacer clic en el botón
     */
    @FXML
    private void handleEliminarPasswordClick(MouseEvent event) {
        passwordField.setText("");
    }

    /**
     *
     * Controla el evento de hacer clic en el botón de mostrar/ocultar
     * contraseña. Si el botón está seleccionado, cambia el campo de texto de
     * contraseña para mostrar su contenido, de lo contrario, oculta su
     * contenido.
     *
     * @param event el evento generado al hacer clic en el botón
     */
    @FXML
    private void handleMostrarPassword(ActionEvent event) {
        if (mostrarPassword.isSelected()) {
            passwordField.getStyleClass().add("show-password");
            passwordField.setPromptText(passwordField.getText());
            passwordField.setText("");
        } else {
            passwordField.getStyleClass().remove("show-password");
            passwordField.setText(passwordField.getPromptText());
            passwordField.setPromptText("");
        }
    }

}
