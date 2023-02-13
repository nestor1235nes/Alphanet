/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Producto;
import Database.daoProducto;
import Database.instanceProduct;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import static java.util.Objects.hash;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Gama
 */
public class ViewPrincipalController implements Initializable {
    
    Producto p;
    daoProducto dao = new daoProducto();
    ObservableList<Producto> lista;
    ObservableList<instanceProduct> carrito=FXCollections.observableArrayList();;
    String aux = "";  
    int total=0;
    int y = 1;
    
    int id;

    
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private Button btnAmount;
    
    @FXML 
    private TextField txtClientAmount;
    
    @FXML
    private Pane paneCash;
    
    @FXML
    private Button manageButton;
    
    @FXML
    private Button btnaddProduct;
    
    @FXML
    private Button cashPayment;
    
    @FXML
    private Button btnpayCard;
    
    @FXML
    private Button btnCashier;
    
    @FXML
    private TextField txtbarCode;
    
    @FXML
    private TextField txtCant;
    
    @FXML 
    private TextField totalPrice;
    
    @FXML
    private AnchorPane scanCode;
    
    @FXML
    private TableView<instanceProduct> shoppingTable;
    
    @FXML
    private TableColumn<instanceProduct, String> cantCol;

    @FXML
    private TableColumn<instanceProduct, String> nameCol;

    @FXML
    private TableColumn<instanceProduct, Integer> priceCol;
      
    
    public void refreshTable(){        
        cantCol.setCellValueFactory(new PropertyValueFactory<>("cant"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
        try {       
            shoppingTable.setItems(carrito);
            shoppingTable.refresh();

        } catch (Exception e) {
            System.out.println("No se pudo actualizar la tabla");
        }    
    }
    
    @FXML
    private void enterTyped(KeyEvent event){

        aux = aux+event.getText();
        try {       
            lista = dao.read();

        } catch (Exception e) {
            System.out.println("No se pudo actualizar la tabla");
        }       
        if(event.getCode().equals(KeyCode.ENTER) ){            
            aux=aux.substring(0,aux.length()-1);            
            if(carrito.isEmpty()){    
                for(Producto p :lista){
                    String code = p.getCodigo().toString();
                    if(aux.equals(code) && dao.search(p.getCodigo())){ 
                        instanceProduct producto = new instanceProduct(p);
                        carrito.add(producto);
                        total = carrito.get(0).getPrecio();
                        totalPrice.setText(Integer.toString(total));
                        
                        refreshTable();          

                    }
                }
                aux="";
            }
            else{
                for(Producto p:lista){                                               
                    int i=0;
                    int size = carrito.size();                    
                    while(i<size){                                          
                        if(carrito.get(i).getCodigo().toString().equals(aux)){                          
                            carrito.get(i).aumentar();
                            
                            total = total + carrito.get(i).getPrecio();
                            totalPrice.setText(Integer.toString(total));
                            refreshTable();
                            aux="";
                            i=size+1;
                        }
                        else{     
                            String code2 = p.getCodigo().toString();                         
                            if(aux.equals(code2) && dao.search(p.getCodigo())){
                                instanceProduct producto = new instanceProduct(p);
                                carrito.add(producto);
                                total = total + carrito.get(i+y).getPrecio();
                                totalPrice.setText(Integer.toString(total));
                                refreshTable();
                                aux="";
                                y++;
                                i=size+1;
                            }
                            else{
                                i++;
                            }
                        }
                        
                    }
                }
                aux="";
            }
        } 
    }
    
    public int getTotal(){
        return total;
    }
    
    @FXML
    void addProduct(ActionEvent event){
        
        String code = txtbarCode.getText();
        String cant = txtCant.getText();
        
        if(code.equals("")){
            JOptionPane.showConfirmDialog(null, "Falta que escriba el codigo de barras.");    
               
        }
        if(cant.equals("")){
            JOptionPane.showConfirmDialog(null, "Falta que escriba la cantidad de productos."); 
        }
        else{
            for(Producto p :lista){
                String aux = p.getCodigo().toString();
                if(aux.equals(code) && dao.search(p.getCodigo())){ 
                    instanceProduct producto = new instanceProduct(p);
                    carrito.add(producto);
                    int x = Integer.parseInt(cant);
                    
                    int i=0;
                    int size = carrito.size();
                    while(i<size){
                        if(carrito.get(i).getCodigo().toString().equals(aux)){
                            
                            for(int j=1; j<x ; j++){
                                carrito.get(i).aumentar();
                            }
                            total = total + (x*carrito.get(i).getPrecio());
                            totalPrice.setText(Integer.toString(total));
                            refreshTable();
                            i=size+1;

                        }
                        else{
                            i++;
                        }
                    }
                    txtbarCode.setText("");
                    txtCant.setText("");
                }
            }
        }
        
    }
    
    @FXML
    void cashier(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewLogin.fxml"));
            stage = new Stage();
            stage.setTitle("Inicio de sesión");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    void payCard(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewtypeCard.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    @FXML 
    void cash(ActionEvent event){
        
        paneCash.setVisible(true);
        
    }  
    @FXML
    void cancel (ActionEvent event ){
        paneCash.setVisible(false);
    }
    @FXML
    void amount(ActionEvent event){
        String monto = txtClientAmount.getText();
        if(txtClientAmount.getText().equals("")){
            JOptionPane.showConfirmDialog(null, "Ingrese monto pagado por cliente.");
        }
        else{
            int dinero = Integer.parseInt(monto);
            dinero = dinero - total;
            JOptionPane.showConfirmDialog(null, "El vuelto es de: $" + dinero +"." );
            paneCash.setVisible(false);
            for (int i = carrito.size()-1; i >=0 ; i--) {
                
                carrito.remove(i);
            }
            txtClientAmount.setText("");
            total = 0;
            totalPrice.setText("");
            refreshTable();
        }

    }
    
    
    
    
    @FXML
    void manual(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewaddManual.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void manage(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewAdmin.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
