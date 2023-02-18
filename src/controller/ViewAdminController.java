/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramir
 */
public class ViewAdminController implements Initializable {
    
    @FXML   
    private Button btnCancel;
    
    @FXML
    private Button btnProduct;
    
    @FXML
    private Button btnCashier;
    
    @FXML
    private Button adminSale;
    
    @FXML
    private void keyPressed(KeyEvent event){
        if(event.getCode().equals(KeyCode.DIGIT1)){   
            cashier();
         }
        if(event.getCode().equals(KeyCode.DIGIT2)){
            product();
        }
        if(event.getCode().equals(KeyCode.DIGIT3)){
            sale();           
        }
        if(event.getCode().equals(KeyCode.ESCAPE)){
            cancel();           
        }
        
    }
    
    @FXML
    void sale(){
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewSale.fxml"));
            stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void product(){
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewProduct.fxml"));
            stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void cashier(){
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewCashier.fxml"));
            stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ViewPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @FXML
    void cancel(){
        
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
