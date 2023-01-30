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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gama
 */
public class ViewPrincipalController implements Initializable {

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
