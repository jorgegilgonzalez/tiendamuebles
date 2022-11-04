/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.registro.nuke;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.BDConexionSingleton;

/**
 * FXML Controller class
 *
 * @author donjo
 */
public class loginscreenController implements Initializable {

    @FXML
    private Button btCancelar;
    @FXML
    private Button btEntrar;
    @FXML
    private TextField tfUser;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Label lbMensajes;

    @FXML
    private void btCancelarOnAction(ActionEvent e) {
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btEntrarOnAction(ActionEvent e) {

        if (tfUser.getText().isBlank() == true || tfPass.getText().isBlank() == true) {//si alguno de los campos esta vacio
            lbMensajes.setText("Introduce usuario y contraseña");

        } else {//si estan completos intenta entrar

            lbMensajes.setText("Entrando al sistema...");
            if (login(e)) {//si el resultado de login es true se le pasa el evento del click del boton

            }

        }
    }

    public boolean login(ActionEvent event) {

        Boolean logeado = false;
        BDConexionSingleton conexion = BDConexionSingleton.getInstancia();//abre conexion
        conexion.crearConexion();

        String verificacion = "SELECT count(*) FROM CuentasUsuario "
                + "WHERE Usuario LIKE " + "\"" + tfUser.getText() + "\"" + " AND Password LIKE " + "\"" + tfPass.getText() + "\"";

        try {//verifica si hay un login y solo uno creado con estos datos
            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(verificacion);

            while (rs.next()) {
                if (rs.getInt(1) == 1)//comprueba que solo devuelve un resultado, si no devuelve ninguno no hay corespondencia
                {
                    lbMensajes.setText("Bienvenido al sistema");
                    logeado = true;
                    cambiarEscena1(event);
                    /*FXMLLoader loader = new FXMLLoader(getClass().getResource("\\com\\registro\\nuke\\primary.fxml"));//crea el cargador
                    Parent root = loader.load();
                    loginscreenController controller = loader.getController();//asocia el controlador que quiero
                    Scene scene = new Scene(root);//crea la escena
                    Stage stage = new Stage();//crea la pantalla
                    stage.initModality(Modality.APPLICATION_MODAL);//la inicia tipo modal(no deja volver a la anterior a nos ser que se acabe)
                    stage.setScene(scene);
                    stage.show();// la abre */
 /*Stage stage = new Stage();
                    stage.getScene().setRoot(App.loadFXML("C:\\Users\\donjo\\OneDrive - Educacyl\\DAM2223\\PPS\\Practicas\\Nuke\\src\\main\\resources\\com\\registro\\nuke\\primary.fxml"));
                     */
                } else {
                    lbMensajes.setText("Login incorrecto. Intentelo de nuevo");
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return logeado;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return logeado;
    }

//cambio de escenas
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void cambiarEscena1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // TODO
    }

}
