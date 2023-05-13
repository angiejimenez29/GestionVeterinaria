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
 * Controlador de la barra de menú de Cliente. Maneja los eventos de los
 * elementos de menú de la barra de menú de cliente, como agregar, modificar,
 * borrar, buscar y listar clientes.
 *
 * @author Nuria Vázquez 
 */
public class MenuBarClienteController {

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
     * Maneja el evento de agregar un cliente. Carga la vista de
     * AgregarCliente.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Agregar
     * cliente.
     */
    public void handleAgregarClientes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cliente/AgregarCliente.fxml"));
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
     * Maneja el evento de modificar un cliente. Carga la vista de
     * ModificarCliente.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Modificar
     * cliente.
     */
    public void handleModificarClientes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cliente/ModificarCliente.fxml"));
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
     * Maneja el evento de borrar un cliente. Carga la vista de
     * EliminarCliente.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Borrar
     * cliente.
     */
    public void handleBorrarClientes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cliente/EliminarCliente.fxml"));
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
     * Maneja el evento de buscar un cliente. Carga la vista de
     * BuscarCliente.fxml y la muestra en la escena principal.
     *
     * @param event El evento de hacer clic en el elemento de menú Buscar
     * cliente.
     */
    public void handleBuscarClientes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cliente/BuscarCliente.fxml"));
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
     * Maneja el evento de listar los clientes. Carga la vista de
     * ListadoCliente.fxml y la muestra en la escena principal
     *
     * @param event El evento que se dispara cuando se selecciona la opción de
     * listar clientes en el menú.
     */
    public void handleListClientes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cliente/ListadoCliente.fxml"));
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
     * Maneja el evento de crear el informe de los clientes. Carga la vista de
     * InformeCliente.fxml y la muestra en la escena principal
     *
     * @param event El evento que se dispara cuando se selecciona la opción de
     * mostrar informe clientes en el menú.
     */
    public void handleGenerarInformeClientes(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cliente/InformeCliente.fxml"));
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
