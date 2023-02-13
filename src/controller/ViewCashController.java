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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramir
 */
public class ViewCashController implements Initializable {
    
    int monCliente=0;
    int vuelto=0;
    int total;
    
    
    @FXML
    private TextField txtmontoCliente;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    void montoCliente(ActionEvent event){
        monCliente = Integer.parseInt(txtmontoCliente.getText());
        System.out.println(monCliente);
        
        Vuelto();
        
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void cancel(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public void Vuelto(){
        ViewPrincipalController valor = new ViewPrincipalController();
        int x = valor.getTotal();
        System.out.println(x);
        vuelto = monCliente - x;
        if(vuelto > 0){
            System.out.println("Su vuelto es: " + vuelto);
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
