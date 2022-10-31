package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class BDConexionSingleton {

    static String bd = "";
    static String login = "";
    static String password = "";
    static String driver = "";
    static String host = "";
    static String puerto = "";
    static String url = "";
    static Connection conexion; // atributo para  guardar el objeto connection.
    private static BDConexionSingleton instancia = null;

        
   
   
   

    private BDConexionSingleton() {
    }

    /**
     * Metodo para retorna una instancia de la conexion. Si no esta creada la
     * crea, y si esta creada la retorna
     *
     * @return retorna una instancia de la conexion a la base de datos
     */
    public synchronized static BDConexionSingleton getInstancia() {
        if (instancia == null) {
            instancia = new BDConexionSingleton();
        }
        return instancia;
    }

    /* devuelve true si se ha creado correctamente */
    public boolean crearConexion() {//mediante acceso a fichero properties

        Properties properties = new Properties();
        
        try {
            properties.load(new FileInputStream(new File("JDBC.properties")));
            //System.out.println("properties conexion cargadas");
            driver = String.valueOf(properties.get("driver"));
            url = String.valueOf(properties.get("url"));
            bd = String.valueOf(properties.get("bd"));
            login = String.valueOf(properties.get("user"));
            password = String.valueOf(properties.get("password"));
            host = String.valueOf(properties.get("host"));
            puerto = String.valueOf(properties.get("puerto"));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url + host + ":" + puerto + "/" + bd, login, password);
            System.out.println("Conexion creada");

        } catch (SQLException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public Connection getConexion() {
        if (conexion == null) {
            crearConexion();
        }
        return conexion;
    }

    public void desconectar() {
        try {
            conexion.close();
            conexion = null;
            System.out.println("La conexion a la  base de datos " + bd + " ha terminado");
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión.");
        }
    }

}
