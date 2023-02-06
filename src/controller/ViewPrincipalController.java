/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Producto;
import Database.daoProducto;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
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
    ObservableList<Producto> carrito=FXCollections.observableArrayList();;
    String aux = "";
    int count = 1;
    
    
    
    int id;

    @FXML
    private Button manageButton;
    
    @FXML
    private Button addManual;
    
    @FXML
    private Button cashPayment;
    
    @FXML
    private Button btnpayCard;
    
    @FXML
    private Button btnCashier;
    
    

    
    @FXML
    private AnchorPane scanCode;
    
    @FXML
    private TableView<Producto> shoppingTable;
    
    @FXML
    private TableColumn cantCol;

    @FXML
    private TableColumn<Producto, String> nameCol;

    @FXML
    private TableColumn<Producto, String> priceCol;
    
    
    
    
    public void refreshTable(){

        cantCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("cant"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("precio"));
        //codeCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("codigo"));
        try {       
            shoppingTable.setItems(carrito);
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
                        
                        carrito.add(p);
                        carrito.get(0).setCant(1);
                        refreshTable();

                    }
                }
                aux="";
            }
            else{
                for(Producto p:lista){
                    int size = carrito.size();
                    int ite =0;
                    int i=0;
                    
                    while(i<size){
                        String code = carrito.get(i).getCodigo().toString();
                        
                        if(aux.equals(code)){
                            System.out.println("aqui");
                            count = carrito.get(i).getCant();
                            count++;
                            carrito.get(i).setCant(count);
                            refreshTable();
                            aux="";
                            i++;
                        }
                        else{
                            
                            String code2 = p.getCodigo().toString();
                            
                            System.out.println(code2);
                            System.out.println(aux);
                            System.out.println("");
                            if(aux.equals(code2) && dao.search(p.getCodigo())){
                                System.out.println("aquiii");
                                carrito.add(p);
                                carrito.get(i).setCant(1);
                                refreshTable();
                                aux="";
                                i++;
                            }
                            else{
                                i++;
                            }
                        }
                    }
                    
                    /*for(i=0 ; i<=size ; i++){
                        String code = carrito.get(i).getCodigo().toString();
 
                        if(aux.equals(code)){
                            System.out.println("aqui");
                            count = carrito.get(i).getCant();
                            count++;

                            carrito.get(i).setCant(count);
                            refreshTable();
                            ite=1;
                            
                            
                        }
                        else{
                            code = p.getCodigo().toString();
                                if(ite==0 && aux.equals(code)){

                                carrito.add(p);
                                carrito.get(i).setCant(1);

                                refreshTable();
                                size=i+1;
                                aux="";

                                }


                        }

                       break; 
                    }*/
                }
                
            }
            

            /*for(Producto p :lista){

                String code = p.getCodigo().toString();

                if(aux.equals(code) && dao.search(p.getCodigo())){
                    carrito.add(p);
                    refreshTable();
                    
                }
            }
            aux="";*/
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
