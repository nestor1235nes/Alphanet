/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Producto;
import Database.daoProducto;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Gama
 */
public class ViewProductController implements Initializable {
    
    Producto p;
    daoProducto dao = new daoProducto();
    int id;
    
    @FXML
    private TableColumn<Producto, String> idCol;

    @FXML
    private TableColumn<Producto, String> nameCol;

    @FXML
    private TableColumn<Producto, String> priceCol;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<Producto> productTable;
    
    public void refreshTable(){
        idCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("precio"));
        try {       
            ObservableList<Producto> lista = dao.read();
            for(Producto p: lista){
                productTable.setItems(lista);
            }
        } catch (Exception e) {
            System.out.println("No se pudo actualizar la tabla");
        }
        
    }

    @FXML
    void addProduct(ActionEvent event) {
        p = new Producto();
        p.setName(nameField.getText());
        p.setPrecio(Integer.parseInt(priceField.getText()));
        if (!dao.create(p)) {
            JOptionPane.showMessageDialog(null, "No se inserto el producto");
        }
        refreshTable();
    }
    
    @FXML
    void clickProduct(MouseEvent event) {
        p = productTable.getSelectionModel().getSelectedItem();     
    }

    @FXML
    void cleanButton(ActionEvent event) {
        
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        int x = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar este registro?");
        if (x == 0 && p.getId() > 0) {
            if (!dao.delete(p.getId())) {
                JOptionPane.showMessageDialog(null, "No se elimino el registro");
            }
            refreshTable();
        }
        
    }

    @FXML
    void pdfButton(ActionEvent event) {

    }

    @FXML
    void saveProduct(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }    
    
}
