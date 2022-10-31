package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author donjo
 */
public class Operaciones {

    public static void informacionBases(BDConexionSingleton conexion, ObservableList<Base> listaBases) {//pide la conexion y la lista observable

        try {

            String consulta = "SELECT idbases, ciudad, capacidad FROM Personal.bases";//sentencia

            Statement st = conexion.getConexion().createStatement();//creo el objeto statement desde la conexion singleton
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                listaBases.add(new Base(
                        rs.getInt("idbases"),
                        rs.getString("ciudad"),
                        rs.getInt("capacidad")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void informacionMisil(BDConexionSingleton conexion, ObservableList<Misil> listaMisiles) {
        try {

            String consulta = "SELECT nombre, stock FROM Personal.misiles";

            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                listaMisiles.add(new Misil(
                        rs.getString("modelo"),
                        rs.getInt("stock")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void informacionRangos(BDConexionSingleton conexion, ObservableList<Rango> listaRangos) {

        try {

            String consulta = "SELECT idcargos, cargo, nivelseguridad FROM Personal.cargos";

            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                listaRangos.add(new Rango(
                        rs.getInt("idcargos"),
                        rs.getString("cargo"),
                        rs.getInt("nivelseguridad")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void InformacionTabla(BDConexionSingleton conexion, ObservableList<Personal> datosPersonal) {

        try {

            String consulta = "Select a.id, a.nombre, a.apellido, a.activo, b.idcargos, b.cargo, b.nivelseguridad, c.idbases, c.ciudad, c.capacidad"
                    + " From personal as a "
                    + "join cargos as b on a.idcargos = b.idcargos "
                    + "join bases as c on a.idbases = c.idbases";

            
            Statement st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                datosPersonal.add(new Personal(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getBoolean("activo"),
                        //por composicion necesito una instancia de cada clase 
                        new Rango(rs.getInt("idcargos"), rs.getString("cargo"), rs.getInt("nivelseguridad")),
                        new Base(rs.getInt("idbases"), rs.getString("ciudad"), rs.getInt("capacidad"))
                ));

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean insertarRegistro(BDConexionSingleton conexion, Personal personal) {

        boolean insertado = false;

        try {

            final String consulta = "INSERT INTO personal VALUES (null,?,?,?,?,?)";
            PreparedStatement ps = conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, personal.getNombre());
            ps.setString(2, personal.getApellido());
            ps.setInt(3, personal.getEsActivo() ? 1 : 0);//si devuelve true inserta 1 si es false inserta 0
            ps.setInt(4, personal.getRango().getidCargo());
            ps.setInt(5, personal.getBase().getIdCiudad());
            ps.executeUpdate();
            insertado = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return insertado;
        }

        return insertado;

    }

    public static boolean modificarRegistro(BDConexionSingleton conexion, Personal personal) {

        boolean modificado = false;

        try {

            final String consulta = "UPDATE personal SET "
                    + "nombre = ?, "
                    + "apellido = ?, "
                    + "activo = ?, "
                    + "idcargos = ?, "
                    + "idbases = ? "
                    + "WHERE id = ?";

            PreparedStatement ps = conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, personal.getNombre());
            ps.setString(2, personal.getApellido());
            ps.setInt(3, personal.getEsActivo() ? 1 : 0);//si devuelve true inserta 1 si es false inserta 0
            ps.setInt(4, personal.getRango().getidCargo());
            ps.setInt(5, personal.getBase().getIdCiudad());
            ps.setInt(6, personal.getId());
            ps.executeUpdate();
            modificado = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return modificado;
        }

        return modificado;

    }

    public static boolean borrarRegistro(BDConexionSingleton conexion, Personal personal) {

        boolean borrado = false;

        try {

            final String consulta = "DELETE FROM personal WHERE (id = ?)";

            PreparedStatement ps = conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, personal.getId());
            ps.executeUpdate();
            borrado = true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return borrado;
        }

        return borrado;

    }

    public static Personal retenerUltimoRegistro(Personal personal) {

        return personal;

    }

    public static void InformacionBusqueda(BDConexionSingleton conexion, ObservableList<Personal> datosPersonal, Personal personal) {

        String consulta = "Select a.id, a.nombre, a.apellido, a.activo, b.idcargos, b.cargo, b.nivelseguridad, c.idbases, c.ciudad, c.capacidad"
                + " From personal as a "
                + "join cargos as b on a.idcargos = b.idcargos "
                + "join bases as c on a.idbases = c.idbases";

        String filtroNombre = "\"" + personal.getNombre() + "\"";
        String filtroApellido = "\"" + personal.getApellido() + "\"";
        String filtroEsActivo = "\"" + String.valueOf(personal.getEsActivo() ? 1 : 0) + "\"";
        
        String filtroCargo ="";
        if (personal.getRango().getidCargo()>0){
        filtroCargo = "\"" + String.valueOf(personal.getRango().getidCargo()) + "\"";}
        
        System.out.println(filtroCargo);
        
        String filtroBase="";
        if (personal.getBase().getIdCiudad()>0){
        filtroBase = "\"" + String.valueOf(personal.getBase().getIdCiudad()) + "\"";}
        
        System.out.println(filtroBase);

        StringBuilder filtro = new StringBuilder();//construyo un stringbuilder para crear el filtro where

        if (personal.getNombre() != "") {//si el campo nombre no esta vacio
            System.out.println("filtro nombre aplicado");
            filtro.append("a.nombre like " + filtroNombre); // anade el filtro
            if (filtroApellido != "" || filtroEsActivo != null || filtroCargo != "" || filtroBase != "") { //si tiene algun otro filtro a continuacion anade un and para continuar la sentencia sql

                filtro.append(" and ");
                System.out.println("filtro nombre and ");

            }
        }

        if (personal.getApellido() != "") {
            filtro.append("a.apellido like " + filtroApellido);
            System.out.println("filtro apellido aplicado");
            if (filtroEsActivo != null || filtroCargo != "" || filtroBase != "") {
                filtro.append(" and ");
                System.out.println("filtro apellido and ");
            }
        }

        //if (personal.getEsActivo() != null) {
            System.out.println("filtro activo aplicado");
            filtro.append("a.activo like " + filtroEsActivo);

            
            
            if (filtroCargo != "" || filtroBase != "") {
                filtro.append(" and ");
                System.out.println("filtro activo and ");
            }
        

        if (filtroCargo != "") {

            filtro.append("b.idcargos like " + filtroCargo);
            System.out.println("filtro cargo aplicado");
            if (filtroBase != "") {
                filtro.append(" and ");
                System.out.println("filtro idcargos and ");
            }

        }
            if (filtroBase != "") {
                System.out.println("filtro base aplicado");
                filtro.append("c.idbases like " + filtroBase);
            }

        

        //System.out.println(filtro);
        consulta += (" WHERE " + filtro);//añade un filtro a la busqueda
        System.out.println(consulta);

        Statement st;
        try {
            st = conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                datosPersonal.add(new Personal(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getBoolean("activo"),
                        //por composicion necesito una instancia de cada clase 
                        new Rango(rs.getInt("idcargos"), rs.getString("cargo"), rs.getInt("nivelseguridad")),
                        new Base(rs.getInt("idbases"), rs.getString("ciudad"), rs.getInt("capacidad"))
                ));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
