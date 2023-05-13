/* doesn't work with source level 1.8:*/
module intelipet {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.xml;
    requires javahelp;
    requires java.logging;
    requires javafx.web;
    requires jasperreports;
    requires mysql.connector.java;
 
 
    
    opens intelipet to javafx.fxml;
    opens modelo to javafx.base;
    opens controlador.login to javafx.fxml;
    opens controlador.seleccionTablas to javafx.fxml;
    opens controlador.menuBar to javafx.fxml;
    opens controlador.mascota to javafx.fxml;
    opens controlador.cliente to javafx.fxml;
    opens controlador.veterinario to javafx.fxml;
    opens controlador.auxiliar to javafx.fxml;
    opens controlador.recepcionista to javafx.fxml;
    opens controlador.cita to javafx.fxml;
    opens controlador.factura to javafx.fxml;

    exports intelipet;
    exports modelo;
    exports controlador.menuBar to javafx.fxml;
    exports controlador.seleccionTablas to javafx.fxml;
    exports controlador.mascota to javafx.fxml;
    exports controlador.login to javafx.fxml;
    exports controlador.cliente to javafx.fxml;
    exports controlador.veterinario to javafx.fxml;
    exports controlador.auxiliar to javafx.fxml;
    exports controlador.recepcionista to javafx.fxml;
    exports controlador.cita to javafx.fxml;
    exports controlador.factura;
    

}
