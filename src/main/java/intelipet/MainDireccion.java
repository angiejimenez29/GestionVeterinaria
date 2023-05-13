package intelipet;

import controlador.login.LoginDireccionController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * La clase MainDireccion es la clase principal de la aplicación de dirección de
 * Intelipet que extiende de la clase Application de JavaFX.
 *
 * Carga la vista de inicio de sesión de dirección y muestra la escena en el
 * escenario principal.
 *
 * @author Nuria Vázquez 
 */
public class MainDireccion extends Application {

    /**
     *
     * Inicia la aplicación de JavaFX con LoginDirección.
     *
     * @param primaryStage el escenario principal de la aplicación de JavaFX
     * @throws Exception si la vista de inicio de sesión no se puede cargar
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/login/LoginDireccion.fxml"));
        Parent root = (Parent) loader.load();
        LoginDireccionController loginDireccionController = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        loginDireccionController.handleLogin(new ActionEvent()); // Llamamos al método handleLogin() con un evento vacío
    }

    /**
     *
     * El método principal de la aplicación.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch(args); // Este método es necesario si estás utilizando JavaFX
    }
}
