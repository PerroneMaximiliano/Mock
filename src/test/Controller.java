/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.sun.deploy.net.HttpResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.util.Locale;
/**
 *
 * @author El Massi
 */
public class Controller implements Initializable {
    @FXML
    private TreeTableColumn<Persona, String> nombreCol;
    @FXML
    private TreeTableColumn<Persona, String> paisCol;
    @FXML
    private TreeTableColumn<Persona, String> fechaCol;
    
    
    @FXML
    private JFXTextField nombreTF;
    @FXML
    private JFXTextField fechaTF;

    
    @FXML
    private Label mensajeTF;

    
    @FXML
    private JFXComboBox<String> paisCB;
    
    
    @FXML
    private JFXTreeTableView<Persona> treeTableView;
    
    
    ObservableList<Persona> list;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //obtener paises para combobox
        String[] locales = Locale.getISOCountries();
	for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            paisCB.getItems().addAll(obj.getDisplayCountry());
	}
        
        //inicializar columnas
        nombreCol.setCellValueFactory(cellData -> cellData.getValue().getValue().nombre);
        paisCol.setCellValueFactory(cellData -> cellData.getValue().getValue().pais);
        fechaCol.setCellValueFactory(cellData -> cellData.getValue().getValue().fecha);
        
        list = FXCollections.observableArrayList();
        
        //pasarle la lista al tableview
        TreeItem<Persona> root = new RecursiveTreeItem<Persona>(list, RecursiveTreeObject ::getChildren);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);
        
        //agregar un registro
        list.addAll(new Persona("Juan Ramires", "Argentina", "02-02-91"));
        
        //hacer click en una fila del tableview
        treeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails(newValue);
        });
    }
    
    @FXML
    public void btnSaludar() {
        list.addAll(new Persona (nombreTF.getText(), paisCB.getSelectionModel().getSelectedItem(), fechaTF.getText()));
        
        if (!fechaTF.getText().equals(""))
            mostrarSaludo();
        
        clearFields();
    }
    
    //limpiar campos
    public void clearFields(){
        nombreTF.setText("");
        fechaTF.setText("");
        paisCB.getSelectionModel().select("");
    }
    
    //mostrar mensaje de saludo en label cuando se guarda un registro
    public void mostrarSaludo () {
        String fecha = fechaTF.getText();
        String[] partes = fecha.split("-");
        String dia = partes[0];
        String mes = partes[1]; 
        String años = partes[2];
        
        mensajeTF.setText("Hola " + nombreTF.getText() + " de " + paisCB.getSelectionModel().getSelectedItem() + ", el dia " + dia
        + " del " + mes + " tendras " +años);
    }
    
    //mostrar mensaje de saludo en label cuando se hace click en una fila de tableview
    public void showDetails(TreeItem<Persona> treeItem){
        String fecha = treeItem.getValue().getFecha();
        String[] partes = fecha.split("-");
        String dia = partes[0];
        String mes = partes[1]; 
        String años = partes[2];
        
        mensajeTF.setText("Hola " + treeItem.getValue().getNombre() + " de " + treeItem.getValue().getPais() + ", el dia " + dia
        + " del " + mes + " tendras " +años);
    }
    
}
