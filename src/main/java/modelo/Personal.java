/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author donjo
 */


public class Personal {

/*usare properties para que refresquen los cambios al interfaz FX
    y para poder acceder a ellos desde elementos como tableview*/
    
private IntegerProperty id;
private StringProperty nombre;
private StringProperty apellido;
private BooleanProperty esActivo;
private Rango rango;//por composicion añado estas clases para poder acceder a sus datos
private Base base;

//estructura de datos con properties

//constructor se pasan tipos primitivos y se asignan a las propiedades

public Personal(){
    
}

public Personal(int id,String nombre, String apellido, Boolean esActivo, Rango rango, Base base){
    
    this.id = new SimpleIntegerProperty(id);
    this.nombre = new SimpleStringProperty(nombre);
    this.apellido = new SimpleStringProperty(apellido);
    this.esActivo = new SimpleBooleanProperty(esActivo);
    this.rango = rango;
    this.base= base;
        
} 




//getter y setter de dato primitivo y de Property

public int getId() {
        return id.get();//devuelve el String no la property
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);//StringProperty es abstracta asi que se asigna de esta manera
    }

    public IntegerProperty idProperty(){//devuelve la property en si
        return id;
        
    }

    public String getNombre() {
        return nombre.get();//devuelve el String no la property
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);//StringProperty es abstracta asi que se asigna de esta manera
    }

    public StringProperty nombreProperty(){//devuelve la property en si
        return nombre;
        
    }

     public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido = new SimpleStringProperty(apellido);
    }

    public StringProperty apellidoProperty(){
        return apellido;
        
    }
     public boolean getEsActivo() {
        return esActivo.get();
    }

    public void setEsActivo(Boolean activo) {
        this.esActivo = new SimpleBooleanProperty(activo);
    }

    public BooleanProperty esActivoProperty(){
        return esActivo;
        
    }
    
    
    public Rango getRango() {
        return rango;
    }

    public void setRango(Rango rango) {
        this.rango = rango;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre.getValue() + ", apellido=" + apellido.getValue() + ", esActivo=" + esActivo.getValue() + "Rango: " + rango.getCargo() + "Niv.Seguridad: " + rango.getNivelSeguridad() + "Ciudad: " + base.getCiudad()+ "Capacidad: " + base.getCapacidad();
    }

    


}
