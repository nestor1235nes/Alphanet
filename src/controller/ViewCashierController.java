/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Usuario;
import Database.daoUsuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ramir
 */
public class ViewCashierController implements Initializable {
    Usuario u;
    daoUsuario dao = new daoUsuario();
    ObservableList<Usuario> lista;

    @FXML
    private TableColumn<Usuario, String> idCol;

    @FXML
    private TableColumn<Usuario, String> nameCol;

    @FXML
    private TableColumn<Usuario, String> passCol;
    
    @FXML
    private TableColumn<Usuario, String> userCol;
    
    @FXML
    private TableView<Usuario> userTable;
    
    @FXML
    private TextField nameField;
    
    @FXML
    private TextField passField;

    @FXML
    private TextField userField;

    @FXML
    void addButton(ActionEvent event) {
        u = new Usuario();
        u.setNombre(nameField.getText());
        u.setUsuario(userField.getText());
        u.setPass(passField.getText());
        if (!dao.create(u)) {
            JOptionPane.showMessageDialog(null, "No se inserto el producto");
        }
        refreshTable();
        cleanTextField();
    }
    
    @FXML
    void clickUser(MouseEvent event) {
        try {
            u = userTable.getSelectionModel().getSelectedItem();
            nameField.setText(u.getNombre());
            userField.setText(u.getUsuario());
            passField.setText(u.getPass());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void deleteButton(ActionEvent event) {
        int x = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar este registro?");
        if (x == 0) {
            if (!dao.delete(u.getId())) {
                JOptionPane.showMessageDialog(null, "No se elimino el registro");
            }
            refreshTable();
        } 
    }

    @FXML
    void updateButton(ActionEvent event) {
        u.setNombre(nameField.getText());
        u.setUsuario(userField.getText());
        u.setPass(passField.getText());
        if (!dao.update(u)) {
            JOptionPane.showMessageDialog(null, "No se actualizo el registro");
        }
        refreshTable();
        cleanTextField();
    }
    
    public void refreshTable(){
        idCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
        userCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("usuario"));
        passCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("pass"));
        try {       
            lista = dao.read();
            userTable.setItems(lista);
        } catch (Exception e) {
            System.out.println("No se pudo actualizar la tabla");
        }    
    }
    
    public void cleanTextField(){
        nameField.setText("");
        userField.setText("");
        passField.setText("");
    }
    
    @FXML
    void cancel(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }    
    
}
