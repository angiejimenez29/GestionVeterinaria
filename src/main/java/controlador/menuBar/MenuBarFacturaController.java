package controlador.menuBar;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;

/**
 * FXML Controller class
 *
 * @author Nuria Vázquez 
 */
public class MenuBarFacturaController  {

    HelpBroker helpBroker ;
    HelpSet helpSet ;
    

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
            

        } catch (javax.help.UnsupportedOperationException| MalformedURLException| HelpSetException ex) {
            // Mostrar un mensaje de error si no se puede cargar la ayuda
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se puede cargar la ayuda");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            ex.printStackTrace();
        }finally{

            System.out.println("La ventana de la ayuda se cerró" );
        }

    }

    /**
     * Este método maneja el evento de clic en el botón de salir de la
     * aplicación. Muestra una alerta de confirmación para preguntar al usuario
     * si desea salir de la aplicación. Si el usuario confirma cerrará la
     * aplicación.
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
            Platform.exit(); // cerrar la aplicación
        }
    }
    
}
