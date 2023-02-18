/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramir
 */
public class ViewStartController implements Initializable {

    int montoCaja = 0;
    
    @FXML
    private TextField txtMonto;
    
    @FXML
    private Button btnStart;
    
    @FXML
    void enter(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            start();
        }
    }
    
    @FXML
    void start(){
        try {
            
            
            Stage stage = (Stage) btnStart.getScene().getWindow();
            if(!txtMonto.getText().isEmpty()){
                montoCaja = Integer.parseInt(txtMonto.getText());
                
            }
           
            
            System.out.println(montoCaja);
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewPrincipal.fxml"));
            stage = new Stage();
            stage.setTitle("Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
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
