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
public class Misil {

    private StringProperty modelo;
    private IntegerProperty megatones;

    public Misil(String modelo, int megatones) {

        this.modelo = new SimpleStringProperty(modelo);

        this.megatones = new SimpleIntegerProperty(megatones);
    }

    public String getModelo() {
        return modelo.get();
    }

    public void setModelo(String modelo) {
        this.modelo = new SimpleStringProperty(modelo);

    }

    public StringProperty getModeloProperty() {
        return modelo;
    }

    public int getMegatones() {
        return megatones.get();
    }

    public void setMegatones(int megatones) {
        this.megatones = new SimpleIntegerProperty(megatones);

    }

    public IntegerProperty getMegatonesProperty() {
        return megatones;
    }

    @Override
    public String toString() {
        return "Misil: " + getModelo()+ " - Megatones: " + getMegatones();
    }

    
}
