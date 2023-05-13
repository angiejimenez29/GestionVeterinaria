package intelipet;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * La clase SeleccionPerfilController es el controlador para la vista
 * SeleccionPerfil que maneja la selección de perfil de los usuarios y cambia a
 * la pantalla de inicio de sesión del perfil seleccionado.
 *
 * @author Nuria Vázquez 
 */
public class SeleccionPerfilController {

    /**
     *
     * Maneja la selección del perfil de dirección y cambia a la pantalla de
     * inicio de sesión de dirección.
     *
     * @throws IOException si la vista de inicio de sesión de dirección no se
     * puede cargar
     */
    @FXML
    private void handleAbrirDireccion() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vista/login/LoginDireccion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        stage.setScene(scene);
        stage.show();

    }

    /**
     *
     * Maneja la selección del perfil de recepción y cambia a la pantalla de
     * inicio de sesión de recepción.
     *
     * @throws IOException si la vista de inicio de sesión de recepción no se
     * puede cargar
     */
    @FXML
    private void handleAbrirRecepcion() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/login/LoginRecepcion.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        stage.setScene(scene);
        stage.show();
    }

}
