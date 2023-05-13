package controlador.menuBar;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import modelo.dao.conexion.Conexion;

/**
 * Controlador de la barra de menú de Veterinarios. Maneja los eventos de los
 * elementos de menú de la barra de menú de veterinarios, como agregar,
 * modificar, borrar, buscar y listar veterinarios.
 *
 * @author Nuria Vázquez 
 */
public class MenuBarVeterinarioController {

    @FXML
    private AnchorPane mainPane;

    HelpBroker helpBroker;
    HelpSet helpSet;

    /**
     * Este método maneja el evento de clic en el botón de ayuda manual. Crea
     * una instancia de la clase JFXJavaHelp y muestra su contenido en una nueva
     * ventana.
     *
     * @param event el evento que ha desencadenado el método.
     */
    public void handleManualAyuda(ActionEvent event) {

        try {
File file = new File("help/help_set.hs");
            URL helpSetURL = file.toURI().toURL();
            helpSet = new HelpSet(null, helpSetURL);

            // Configurar el ayudante para que muestre la ayuda en el item del menú de ayuda
            helpBroker = helpSet.createHelpBroker();

            helpBroker.setDisplayed(true);

        } catch (javax.help.UnsupportedOperationException | MalformedURLException | HelpSetException ex) {
            // Mostrar un mensaje de error si no se puede cargar la ayuda
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se puede cargar la ayuda");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        } finally {

            System.out.println("La ventana de la ayuda se cerró");
        }

    }

    /**
     * Este método maneja el evento de clic en el botón de salir de la
     * aplicación. Muestra una alerta de confirmación para preguntar al usuario
     * si desea salir de la aplicación. Si el usuario confirma, se desconectará
     * de la base de datos y cerrará la aplicación.
     *
     * @param event el evento que ha desencadenado el método.
     */
    public void handleSalirApp(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("¿Está seguro que desea salir de la aplicación?");
        alert.setContentText("Si sale ahora, perderá cualquier cambio no guardado.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // El usuario hizo clic en "Aceptar"
            Conexion.desconectar(); // desconectar de la base de datos
            Platform.exit(); // cerrar la aplicación
        }
    }

    /**
     *
     * Maneja el evento de agregar una veterinario. Carga la vista de
     * AgregarVeterinario.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Agregar
     * veterinario.
     */
    public void handleAgregarVeterinarios(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/veterinario/AgregarVeterinario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento de modificar un veterinario. Carga la vista de
     * ModificarVeterinario.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Modificar
     * veterinario.
     */
    public void handleModificarVeterinarios(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/veterinario/ModificarVeterinario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento de borrar una veterinario. Carga la vista de
     * EliminarVeterinario.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Borrar
     * veterinario.
     */
    public void handleBorrarVeterinarios(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/veterinario/EliminarVeterinario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento de buscar un veterinario. Carga la vista de
     * BuscarVeterinario.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Buscar
     * veterinario.
     */
    public void handleBuscarVeterinarios(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/veterinario/BuscarVeterinario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento de listar a los veterinarios. Carga la vista de
     * ListadoVeterinario.fxml y la muestra en la escena principal
     *
     * @param event El evento que se dispara cuando se selecciona la opción de
     * listar veterinarios en el menú.
     */
    public void handleListVeterinarios(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/veterinario/ListadoVeterinario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento de crear el informe de los veterinarios. Carga la vista
     * de InformeVeterinario.fxml y la muestra en la escena principal
     *
     * @param event El evento que se dispara cuando se selecciona la opción de
     * mostrar informe veterinarios en el menú.
     */
    public void handleGenerarInformeVeterinarios(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/veterinario/InformeVeterinario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error al cargar la vista" + ex.getMessage());
        }

    }

}
