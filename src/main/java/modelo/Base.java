/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author donjo
 */
public class Base {
    
    private IntegerProperty idCiudad;
    private StringProperty ciudad;
    private IntegerProperty capacidad;

    public Base() {
    }
    
    
    public Base (int idCiudad, String ciudad, int capacidad){
    
    this.idCiudad = new SimpleIntegerProperty(idCiudad);
    this.ciudad = new SimpleStringProperty(ciudad);
    this.capacidad = new SimpleIntegerProperty(capacidad);
    
} 

    
    public int getIdCiudad() {
        return idCiudad.get();
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = new SimpleIntegerProperty(idCiudad);

    }

    public IntegerProperty getIdCiudadProperty() {
        return idCiudad;
        
    }
     public String getCiudad(){
        return ciudad.get();
    }

    public void setCiudad(String ciudad){
        this.ciudad  = new SimpleStringProperty(ciudad);
        
    }
    
    public StringProperty getCiudadProperty(){
        return ciudad;
    }
    
     public int getCapacidad(){
        return capacidad.get();
    }

    public void setCapacidad(int capacidad){
        this.capacidad  = new SimpleIntegerProperty(capacidad);
        
    }
    
    public IntegerProperty getCapacidadProperty(){
        return capacidad;
    }

    @Override
    public String toString() {
        return getCiudad();
    }
    
    
}
