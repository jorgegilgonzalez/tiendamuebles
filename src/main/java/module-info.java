module com.registro.nuke {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;// para que cargue la dependencia de conexion mysql
    requires java.base;
    opens com.registro.nuke to javafx.fxml;
    exports com.registro.nuke;//para que pueda acceder el fxml a las clases
    exports modelo;//para que pueda acceder el fxml a las clases
    
}
