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
import javafx.stage.Stage;


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
    int count = 1;
    
    //Map<String, Integer> hash = Collections.synchronizedMap(new HashMap());
    HashMap<String, Integer> hash = new HashMap<String, Integer>();
    
    
    int id;

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
    private AnchorPane scanCode;
    
    @FXML
    private TableView<instanceProduct> shoppingTable;
    
    @FXML
    private TableColumn<instanceProduct, String> cantCol;

    @FXML
    private TableColumn<instanceProduct, String> nameCol;

    @FXML
    private TableColumn<instanceProduct, Integer> priceCol;
    
    
    
    
    public void refreshTable(instanceProduct product){
        
        
        cantCol.setCellValueFactory(new PropertyValueFactory<>("cant"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("precio"));

        try {       
            //shoppingTable.getItems().addAll(carrito);
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
                        //shoppingCart(aux, code, 0, p);
                        carrito.add(producto);
            

                        refreshTable(producto);
                        
                    }
                }
                aux="";
            }
            else{
                for(Producto p:lista){
                              
                    //shoppingCart(aux, p.getCodigo().toString(), 1, p);
                    
                    int i=0;
                    int size = carrito.size();
                    
                    while(i<size){
                                           
                        if(carrito.get(i).getCodigo().toString().equals(aux)){
                            
                            carrito.get(i).aumentar();
                            
                            
                            refreshTable(carrito.get(i));
                            aux="";
                            i=size+1;

                        }
                        else{     
                            String code2 = p.getCodigo().toString();
                            
                            if(aux.equals(code2) && dao.search(p.getCodigo())){
                                instanceProduct producto = new instanceProduct(p);
                                carrito.add(producto);

                                
                                refreshTable(producto);
                                aux="";
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
 
    
    /*public void shoppingCart(String scanCode, String dbCode, int x, Producto p){
        
        if(x == 0){
            carrito.add(p);
            
            //System.out.println(carrito.get(0).getName());
            refreshTable();
            hash.put(scanCode, count);                        
            
        }
        else{
            for(Entry<String, Integer> valor : hash.entrySet()){
                if(valor.getKey().equals(scanCode) && scanCode.equals(dbCode)){
                    
                    //System.out.println("aqui");
                    hash.put(scanCode, valor.getValue() +1);
                    refreshTable();
                }
                else{
                    
                    if(scanCode.equals(dbCode)&& dao.search(p.getCodigo())){
                       // System.out.println("aca");
                        carrito.add(p);
                        hash.put(scanCode, count);                        
                        refreshTable();
                    }
                }
                
            }
            System.out.println("");
        }
        
        
    }*/
    
    
    
    @FXML
    void addProduct(ActionEvent event){
        String code = txtbarCode.getText();
        String cant = txtCant.getText();
        
        if(code.equals("")){
            //JOptionPane.showConfirmDialog(null, "Falta que escriba el codigo de barras.");    
               
        }
        if(cant.equals("")){
            //JOptionPane.showConfirmDialog(null, "Falta que escriba la cantidad de productos."); 
        }
        else{
            for(Producto p :lista){
                String aux = p.getCodigo().toString();
                if(aux.equals(code) && dao.search(p.getCodigo())){ 
                    instanceProduct producto = new instanceProduct(p);
                    carrito.add(producto);
                    int x = Integer.parseInt(cant);
                    
                    //refreshTable();
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewCash.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
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
