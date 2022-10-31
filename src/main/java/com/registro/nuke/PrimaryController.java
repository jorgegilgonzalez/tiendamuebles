/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.registro.nuke;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Base;
import modelo.BDConexionSingleton;
import modelo.Operaciones;
import modelo.Personal;
import modelo.Rango;

/**
 * FXML Controller class
 *
 * @author donjo
 */
public class PrimaryController implements Initializable {

    @FXML
    private Button btGuardar;

    @FXML
    private Button btModificar;

    @FXML
    private Button btBuscar;

    @FXML
    private Button btEliminar;

    @FXML
    private Button btNuevo;

    @FXML
    private Button btDeshacer;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellido;

    @FXML
    private ToggleGroup GrupoEnActivo;

    @FXML
    private RadioButton rdSi;

    @FXML
    private RadioButton rdNo;

    @FXML
    private ComboBox<Rango> comboRango;

    private ObservableList<Rango> listaRangos;//crea una lista con la que alimentar el combobox

    @FXML
    private ComboBox<Base> comboBase;

    private ObservableList<Base> listaBases;//crea una lista con la que alimentar el combobox

    @FXML
    private TableView<Personal> tableviewInfoPersonal;

    private ObservableList<Personal> listaPersonal;//crea una lista con la que alimentar el combobox

    @FXML
    private TableColumn<Personal, String> columnaNombre;
    @FXML
    private TableColumn<Personal, String> columnaApellido;
    @FXML
    private TableColumn<Personal, Boolean> columnaActivo;
    @FXML
    private TableColumn<Personal, Rango> columnaRango;
    @FXML
    private TableColumn<Personal, Base> columnaBase;

    Personal personaRetenida = new Personal(); //guardara el ultimo registro eliminado

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ;//accedo a la conexion singleton mediante el metodo estatico getinstance

        listaRangos = FXCollections.observableArrayList();//inicializa de esta manera porque observablelist es una interfaz y no se puede instanciar
        Operaciones.informacionRangos(BDConexionSingleton.getInstancia(), listaRangos);//llamo al estatico que me devuelve la info desde la BD
        comboRango.setItems(listaRangos);//asigna o enlaza la lista al combobox

        listaBases = FXCollections.observableArrayList();
        Operaciones.informacionBases(BDConexionSingleton.getInstancia(), listaBases);
        comboBase.setItems(listaBases);

        listaPersonal = FXCollections.observableArrayList();
        if (!busqueda) {//si no esta buscando muestra todo

            tableviewInfoPersonal.setItems(listaPersonal);
            Operaciones.InformacionTabla(BDConexionSingleton.getInstancia(), listaPersonal);

        } else {//si esta buscando pasa true como parametro para que en el metodo cargue los datos de la busqueda

            tableviewInfoPersonal.setItems(listaPersonal);
            Operaciones.InformacionTabla(BDConexionSingleton.getInstancia(), listaPersonal);

        }


        /*enlazar columnas con cada atributo*/
        columnaNombre.setCellValueFactory(new PropertyValueFactory<Personal, String>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<Personal, String>("apellido"));
        columnaActivo.setCellValueFactory(new PropertyValueFactory<Personal, Boolean>("esActivo"));
        columnaRango.setCellValueFactory(new PropertyValueFactory<Personal, Rango>("rango"));
        columnaBase.setCellValueFactory(new PropertyValueFactory<Personal, Base>("base"));

        recuperarFilaAction();
    }

    public void recuperarFilaAction() {
        tableviewInfoPersonal.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Personal>() {

            @Override
            public void changed(ObservableValue<? extends Personal> ov, Personal anterior, Personal seleccion) {

                if (seleccion != null) {//controlo que haya alguno seleccionado
                    tfId.setText(String.valueOf(seleccion.getId()));
                    tfNombre.setText(seleccion.getNombre());
                    tfApellido.setText(seleccion.getApellido());

                    if (seleccion.getEsActivo()) {
                        rdSi.setSelected(true);
                    } else {
                        rdNo.setSelected(true);
                    }

                    comboRango.setValue(seleccion.getRango());
                    comboBase.setValue(seleccion.getBase());

                    btGuardar.setDisable(true);
                    btModificar.setDisable(false);
                    btEliminar.setDisable(false);
                }
            }
        }
        );
    }

    @FXML

    public void limpiarCampos() {

        tfId.setText("");
        tfNombre.setText("");
        tfApellido.setText("");
        rdSi.setSelected(true);
        rdNo.setSelected(false);
        comboBase.setValue(null);
        comboRango.setValue(null);

        btGuardar.setDisable(false);
        btModificar.setDisable(true);
        btEliminar.setDisable(true);
        listaPersonal.clear();
        Operaciones.InformacionTabla(BDConexionSingleton.getInstancia(), listaPersonal);
    }

    @FXML

    public void guardarRegistro() {
        /* creara una instancia de Personal y llama al metodo insertarRegistro*/

        Personal personal = new Personal(
                0,
                tfNombre.getText(),//creo personal desde la info almacenada en los componentes
                tfApellido.getText(),
                rdSi.isSelected(),
                new Rango(comboRango.getSelectionModel().getSelectedItem().getidCargo(), comboRango.getSelectionModel().getSelectedItem().getCargo(), comboRango.getSelectionModel().getSelectedItem().getNivelSeguridad()),
                new Base(comboBase.getSelectionModel().getSelectedItem().getIdCiudad(), comboBase.getSelectionModel().getSelectedItem().getCiudad(), comboBase.getSelectionModel().getSelectedItem().getCapacidad()));

        if (Operaciones.insertarRegistro(BDConexionSingleton.getInstancia(), personal)) {//si es correcto devuelve true

            listaPersonal.add(personal);//al anadir el registro actualiza el tableview

            Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
            aviso.setTitle("Nueva inserción en la Base de Datos");
            aviso.setHeaderText("Procedimiento correcto: ");
            aviso.setContentText("Un nuevo registro se ha añadido");;
            aviso.show();
        }
    }

    public void actualizarRegistro() {
        /* creara una instancia de Personal y llama al metodo modificarRegistro*/

        Personal personal = new Personal(
                Integer.parseInt(tfId.getText()),
                tfNombre.getText(),//creo personal desde la info almacenada en los componentes
                tfApellido.getText(),
                rdSi.isSelected(),
                new Rango(comboRango.getSelectionModel().getSelectedItem().getidCargo(), comboRango.getSelectionModel().getSelectedItem().getCargo(), comboRango.getSelectionModel().getSelectedItem().getNivelSeguridad()),
                new Base(comboBase.getSelectionModel().getSelectedItem().getIdCiudad(), comboBase.getSelectionModel().getSelectedItem().getCiudad(), comboBase.getSelectionModel().getSelectedItem().getCapacidad()));

        if (Operaciones.modificarRegistro(BDConexionSingleton.getInstancia(), personal)) {//si es correcto devuelve true

            listaPersonal.set(tableviewInfoPersonal.getSelectionModel().getSelectedIndex(), personal);//al actualizar el registro actualiza el tableview. Al ser un update se usa .set

            Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
            aviso.setTitle("Nueva modificación en la Base de Datos");
            aviso.setHeaderText("Procedimiento correcto: ");
            aviso.setContentText("Un nuevo registro se ha modificado");;
            aviso.show();
        }
    }

    public void eliminarRegistro() {
        /* creara una instancia de Personal y llama al metodo borrarRegistro*/

        Personal personal = new Personal(
                Integer.parseInt(tfId.getText()),
                tfNombre.getText(),//creo personal desde la info almacenada en los componentes
                tfApellido.getText(),
                rdSi.isSelected(),
                new Rango(comboRango.getSelectionModel().getSelectedItem().getidCargo(), comboRango.getSelectionModel().getSelectedItem().getCargo(), comboRango.getSelectionModel().getSelectedItem().getNivelSeguridad()),
                new Base(comboBase.getSelectionModel().getSelectedItem().getIdCiudad(), comboBase.getSelectionModel().getSelectedItem().getCiudad(), comboBase.getSelectionModel().getSelectedItem().getCapacidad()));

        personaRetenida = Operaciones.retenerUltimoRegistro(personal);//conserva el ultimo registro antes de borrarlo

        if (Operaciones.borrarRegistro(BDConexionSingleton.getInstancia(), personal)) {//si es correcto devuelve true

            listaPersonal.remove(tableviewInfoPersonal.getSelectionModel().getSelectedIndex());//al borrar el registro actualiza el tableview. solo nmecesito el indice. Al ser un update se usa .remove

            Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
            aviso.setTitle("Nueva eliminación en la Base de Datos");
            aviso.setHeaderText("Procedimiento correcto: ");
            aviso.setContentText("Un nuevo registro se ha borrado");
            aviso.show();
        }
    }

    public void recuperarUltimoRegistro() {

        if (personaRetenida != null) {//controlo que se haya borrado ya un registro para que no lance si no hay ninguna almacenada

            if (Operaciones.insertarRegistro(BDConexionSingleton.getInstancia(), personaRetenida)) {//si es correcto devuelve true

                listaPersonal.add(personaRetenida);//al anadir el registro actualiza el tableview
                personaRetenida = null;//vacia la variable para que no se pueda reinsertar mas de una vez

                Alert aviso = new Alert(Alert.AlertType.INFORMATION);//lanza aviso 
                aviso.setTitle("Nueva inserción en la Base de Datos");
                aviso.setHeaderText("Procedimiento correcto: ");
                aviso.setContentText("Se ha recuperado el ultimo registro borrado");
                aviso.show();
            }
        }

    }

    //cambio de escenas
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void cambiarEscena2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginscreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //implementar metodo buscar
    Boolean busqueda = false;

    public void buscarRegistro() {

        listaPersonal.clear();
        
        Base base = new Base();
        
       
        if (comboBase.getSelectionModel().getSelectedItem()!=null){
        
        base.setIdCiudad(comboBase.getSelectionModel().getSelectedItem().getIdCiudad());
        base.setCiudad(comboBase.getSelectionModel().getSelectedItem().getCiudad());
        base.setCapacidad(comboBase.getSelectionModel().getSelectedItem().getCapacidad());
        System.out.println("impimiento objeto base" + base.toString());
        } else {
            
        base.setIdCiudad(0);
        base.setCiudad("");
        base.setCapacidad(0);
        //System.out.println("imprimiendo objeto base VACIO" + base.toString());
        
        }
        
        Rango rango = new Rango();
        
       
        if (comboRango.getSelectionModel().getSelectedItem()!=null){
        
        rango.setIdCargo(comboRango.getSelectionModel().getSelectedItem().getidCargo());
        rango.setCargo(comboRango.getSelectionModel().getSelectedItem().getCargo());
        rango.setNivelSeguridad(comboRango.getSelectionModel().getSelectedItem().getNivelSeguridad());
        System.out.println("impimiento objeto rango" + base.toString());
        } else {
            
        rango.setIdCargo(0);
        rango.setCargo("");
        rango.setNivelSeguridad(0);
        //System.out.println("imprimiendo objeto rango VACIO" + base.toString());
            
        }
        
        Personal personal = new Personal(
                0,//para la busqueda no voy a usar el id, pero necesito un valor para la creacion del objeto, le asigno 0.
                tfNombre.getText(),//creo personal desde la info almacenada en los componentes
                tfApellido.getText(),
                rdSi.isSelected(),
                rango,
                base);
                //new Base(comboBase.getSelectionModel().getSelectedItem().getIdCiudad(), comboBase.getSelectionModel().getSelectedItem().getCiudad(), comboBase.getSelectionModel().getSelectedItem().getCapacidad()));

       
        /*Base base = new Base();
        base.setIdCiudad(comboBase.getSelectionModel().getSelectedItem().getIdCiudad());
        base.setCiudad(comboBase.getSelectionModel().getSelectedItem().getCiudad());
        base.setCapacidad(comboBase.getSelectionModel().getSelectedItem().getCapacidad());*/

        System.out.println("personal creado" + personal.toString());
        //tableviewInfoPersonal.setItems(listaPersonal);
        Operaciones.InformacionBusqueda(BDConexionSingleton.getInstancia(), listaPersonal, personal);

    }

}
