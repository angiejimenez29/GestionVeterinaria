package controlador.login;

import controlador.seleccionTablas.SeleccionTablasDireccionController;
import controlador.seleccionTablas.SeleccionTablasRecepcionController;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.dao.conexion.Conexion;
import static modelo.dao.conexion.Conexion.connection;

/**
 * Clase que representa el controlador de la vista de inicio de sesión con Login
 *
 * @author Nuria Vázquez 
 */
public class Login {

    //Campos de texto y botón del formulario de inicio de sesión
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ToggleButton mostrarPassword;

    /**
     *
     * Controla el evento de inicio de sesión del usuario. Verifica si el
     * usuario y contraseña ingresados son válidos.
     *
     * @param event el evento que disparó la acción de inicio de sesión
     * @throws IOException si ocurre un error al cargar la vista de selección de
     * tablas
     * @throws ClassNotFoundException si ocurre un error al cargar el
     * controlador de la vista de selección de tablas
     */
    @FXML
    private void handleLogin(ActionEvent event) throws IOException, ClassNotFoundException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            if (username.equals("director") && password.equals("director1234")) {
                connection = Conexion.conectarDirector();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/seleccionTablas/SeleccionTablasDireccion.fxml"));
                Parent root = (Parent) loader.load();
                SeleccionTablasDireccionController controller = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else if (username.equals("recepcionista") && password.equals("recepcionista1234")) {
                connection = Conexion.conectarRecepcionista();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/seleccionTablas/SeleccionTablasRecepcion.fxml"));
                Parent root = (Parent) loader.load();
                SeleccionTablasRecepcionController controller = loader.getController();
                Scene scene = new Scene(root);
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } else {

                return;
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
