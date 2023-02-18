/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphanet;

import Database.Conexion;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ramir
 */
public class Alphanet extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {         
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewLog.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("No se puedo iniciar el programa");
            System.out.println(e.getMessage());
        }      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
}
