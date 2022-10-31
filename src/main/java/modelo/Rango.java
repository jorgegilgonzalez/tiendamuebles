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
public class Rango {

    private IntegerProperty idCargo;
    private StringProperty cargo;
    private IntegerProperty nivelSeguridad;

    
    public Rango() {
        
    }
    
    public Rango(int idCargo, String cargo, int nivelSeguridad) {

        this.idCargo = new SimpleIntegerProperty(idCargo);
        this.cargo = new SimpleStringProperty(cargo);
        this.nivelSeguridad = new SimpleIntegerProperty(nivelSeguridad);
    }

    

    public int getidCargo() {
        return idCargo.get();
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = new SimpleIntegerProperty(idCargo);

    }

    public IntegerProperty getIdCargoProperty() {
        return idCargo;
    }
    
    
    public String getCargo() {
        return cargo.get();
    }

    public void setCargo(String cargo) {
        this.cargo = new SimpleStringProperty(cargo);

    }

    public StringProperty getCargoProperty() {
        return cargo;
    }

    public int getNivelSeguridad() {
        return nivelSeguridad.get();
    }

    public void setNivelSeguridad(int nivelSeguridad) {
        this.nivelSeguridad = new SimpleIntegerProperty(nivelSeguridad);

    }

    public IntegerProperty getNivelSeguridadProperty() {
        return nivelSeguridad;
    }

    @Override
    public String toString() {
        return getCargo();
    }

    

    
}
