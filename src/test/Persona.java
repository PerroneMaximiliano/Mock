/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author El Massi
 */
class Persona extends RecursiveTreeObject<Persona> {
    public StringProperty nombre;
    public StringProperty pais;
    public StringProperty fecha;
    
    public Persona (String nombre, String pais, String fecha) {
        this.nombre = new SimpleStringProperty(nombre);
        this.pais = new SimpleStringProperty(pais);
        this.fecha = new SimpleStringProperty(fecha);
    }
    
    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre.set(name);
    }
    
    public String getPais() {
        return pais.get();
    }

    public StringProperty paisProperty() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais.set(pais);
    }
    
    public String getFecha() {
        return fecha.get();
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }
}
