/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 * @author ramir
 */
public class ViewLoginController implements Initializable {

    @FXML
    private TextField txtUser;
    
    @FXML
    private TextField txtPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private void eventKey(KeyEvent event){
        
        Object evt = event.getSource();
          
    }
    
    @FXML
    public void eventAction(ActionEvent event){
        
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void eventKey(javafx.scene.input.KeyEvent event) {
    }
    
}
