package controlador.seleccionTablas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Controlador para la selección de tablas de Dirección
 *
 * @author Nuria Vázquez 
 */
public class SeleccionTablasDireccionController {

    /**
     * Maneja el evento del botón Mascotas
     *
     * @param event el evento de acción generado por el botón Mascotas
     */
    @FXML
    public void handleMascotas(ActionEvent event) {

        try {
            // Carga la vista de ListadoMascota.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/mascota/ListadoMascota.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento del botón Clientes
     *
     * @param event el evento de acción generado por el botón Clientes
     */
    @FXML
    public void handleClientes(ActionEvent event) {

        try {
            // Carga la vista de ListadoCliente.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cliente/ListadoCliente.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("handleClientes-Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento del botón Veterinarios
     *
     * @param event el evento de acción generado por el botón Veterinarios
     */
    @FXML
    public void handleVeterinarios(ActionEvent event) {

        try {
            // Carga la vista de ListadoVeterinario.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/veterinario/ListadoVeterinario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("handleVeterinarios-Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento del botón Auxiliar
     *
     * @param event el evento de acción generado por el botón Auxiliar
     */
    @FXML
    public void handleAuxiliares(ActionEvent event) {

        try {
            // Carga la vista de ListadoAuxiliar.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/auxiliar/ListadoAuxiliar.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("handleAuxiliares-Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento del botón Recepción
     *
     * @param event el evento de acción generado por el botón Recepción
     */
    @FXML
    public void handleRecepcionistas(ActionEvent event) {

        try {
            // Carga la vista de ListadoRecepcionista.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/recepcionista/ListadoRecepcionista.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("handleRecepcionistas-Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento del botón Cita
     *
     * @param event el evento de acción generado por el botón Recepción
     */
    @FXML
    public void handleCitas(ActionEvent event) {

        try {
            // Carga la vista de ListadoRecepcionista.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/cita/ListadoCita.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("handleCitas-Error al cargar la vista" + ex.getMessage());
        }

    }

    /**
     * Maneja el evento del botón Facturas
     *
     * @param event el evento de acción generado por el botón Facturas
     */
    @FXML
    public void handleFacturas(ActionEvent event) {

        try {
            // Carga la vista de ListadoRecepcionista.fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/factura/GenerarFactura.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Obtener la ventana actual y establecer la nueva escena
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("handleFacturas-Error al cargar la vista" + ex.getMessage());
        }

    }

}
