package intelipet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * La clase App es la clase principal de la aplicación Intelipet que extiende la
 * clase Application de JavaFX.
 *
 * @author Nuria Vázquez 
 */
public class App extends Application {

    private static Scene scene;

    /**
     *
     * Inicia la aplicación de JavaFX.
     *
     * @param stage el escenario principal de la aplicación de JavaFX
     * @throws IOException si el archivo FXML no se puede cargar
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/vista/inicio/SeleccionPerfil"), 800, 600);
        stage.setTitle("Intelipet | Software de Gestión");
        stage.getIcons().add(new Image("file:src/main/resources/img/logo_intelipet.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }

    /**
     *
     * Carga un archivo FXML.
     *
     * @param fxml la ruta del archivo FXML que se va a cargar
     * @return el elemento raíz del archivo FXML cargado
     * @throws IOException si el archivo FXML no se puede cargar
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *
     * El método principal de la aplicación.
     *
     * @param args los argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        launch();
    }
}
