/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Conexion;
import alphanet.Session;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ramir
 */
public class ViewLoginController implements Initializable {
    
    Conexion cx;

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnLogin;
    
    @FXML
    void eventAction(ActionEvent event) {    
        try {   
            cx = new Conexion();
            cx.conectar();
            String user = txtUser.getText();
            String pass = txtPassword.getText();
            String query = "SELECT * FROM usuarios WHERE usuario='"+user+"' and pass='"+pass+"'";
            Statement st = cx.conectar().createStatement();
            ResultSet rs = st.executeQuery(query);    
            if (rs.next()) {
                Session.getCurrentInstance().setLoggedUser(rs.getInt("id"));
                Stage stage = (Stage) btnLogin.getScene().getWindow();
                stage.close();
                Parent root = FXMLLoader.load(getClass().getResource("/view/ViewStart.fxml"));
                stage = new Stage();
                stage.setTitle("Inicio Caja");
                stage.setScene(new Scene(root));
                stage.show();
                cx.desconectar();
            }
            else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }    
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void eventKey(javafx.scene.input.KeyEvent event) {
    }
    
}
