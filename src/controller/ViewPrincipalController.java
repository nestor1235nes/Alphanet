/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Producto;
import Database.Venta;
import Database.daoProducto;
import Database.daoVenta;
import Database.instanceProduct;
import alphanet.Session;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    daoVenta daoV = new daoVenta();
    ObservableList<Producto> lista;
    ObservableList<instanceProduct> carrito = FXCollections.observableArrayList();
    String aux = "";  
    int total=0;
    int y = 1;
    int id;
    int user = Session.getCurrentInstance().getLoggedUser();
    int option;
    boolean active = false;

    
    @FXML
    private Button removeProduct;
    
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
    public void remove(){
        
        instanceProduct ip = shoppingTable.getSelectionModel().getSelectedItem();
        if (ip == null) {
            
            String [] opcion = {"Aceptar"};      
            JOptionPane.showOptionDialog(null, "Se necesita que seleccione un producto a eliminar", "Error", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar");
            active = true;
        }
        else{
            if(ip.getCant() == 1){
                carrito.remove(ip);
                total = total - ip.getPrecio();
                totalPrice.setText(Integer.toString(total));
            }
            else{
                ip.restar();
                total = total - ip.getPrecio();
                totalPrice.setText(Integer.toString(total));
            }
        }
        refreshTable();
        
    }
    
    
    
    @FXML
    private void enterTyped(KeyEvent event){

        if(event.getCode().equals(KeyCode.DELETE) && !paneCash.isVisible()){
            remove();
        }
        
        
        if(event.getCode().equals(KeyCode.ENTER) && active == true){
            active = false;
        }
        else
            if(event.getCode().equals(KeyCode.ENTER) && paneCash.isVisible() && !txtClientAmount.getText().isEmpty()){
                amount();
                active = true;
            }
        else
        
            if(event.getCode().equals(KeyCode.ENTER) && !paneCash.isVisible()){
                cash();
            }
        else
            if(event.getCode().equals(KeyCode.ESCAPE) && paneCash.isVisible()){
                cancel();
            }
        else
            if(event.getCode().equals(KeyCode.DIGIT1) && paneCash.isVisible() && !txtClientAmount.isFocused()){
                option = 1000;
                amount();

            }
        else
            if(event.getCode().equals(KeyCode.DIGIT2) && paneCash.isVisible() && !txtClientAmount.isFocused()){
                option = 5000;
                amount();

            }
        else
            if(event.getCode().equals(KeyCode.DIGIT3) && paneCash.isVisible() && !txtClientAmount.isFocused()){
                option = 10000;
                amount();

            }
        else
            if(event.getCode().equals(KeyCode.DIGIT4) && paneCash.isVisible() && !txtClientAmount.isFocused()){

                option = 20000;
                amount();

            }
        else
            if(event.getCode().equals(KeyCode.F1)){
                btnaddProduct.setFocusTraversable(true);
                addProduct();
                
            }
        else
            if (event.getCode().equals(KeyCode.F3)) {
            manage();
        }
        else
            if (event.getCode().equals(KeyCode.F2)) {
            cashier();
        }
        
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
    
    
    @FXML
    void addProduct(){
        
        String code = txtbarCode.getText();
        String cant = txtCant.getText();
        
        if(code.equals("")){
            String [] opcion = {"Aceptar"};
            
            JOptionPane.showOptionDialog(null, "Falta que escriba el codigo de barras del producto.", "Aviso", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar");    
               
        }
        if(cant.equals("")){
            String [] opcion = {"Aceptar"};
            
            JOptionPane.showOptionDialog(null, "Falta que escriba la cantidad del producto.", "Aviso", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar"); 
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
                    while(i < size){
                        if(carrito.get(i).getCodigo().toString().equals(aux)){
                            
                            for(int j = 1; j < x ; j++){
                                carrito.get(i).aumentar();
                            }
                            total = total + (x*carrito.get(i).getPrecio());
                            totalPrice.setText(Integer.toString(total));
                            refreshTable(); 
                            i = size + 1;
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
    void cashier(){
        Stage stage = (Stage) this.btnCancel.getScene().getWindow();
        stage.close();
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/ViewLog.fxml"));
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
    void cash(){     
        paneCash.setVisible(true);  
        paneCash.toFront();
        
    }  
    
    @FXML
    void cancel (){
        paneCash.setVisible(false);
    }
    
    @FXML
    void amount(){
        
        if (carrito.isEmpty()) {
            String [] opcion = {"Aceptar"};
                
            JOptionPane.showOptionDialog(null, "El carrito está vacio", "Aviso", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar");
        }
        
        if(txtClientAmount.getText().equals("") && !carrito.isEmpty()){

            
            addVenta();
            int dinero = option - total;;
            
            
            if(dinero>=0){
                String [] opcion = {"Aceptar"};
                
                JOptionPane.showOptionDialog(null, "El vuelto es de: $" + dinero +".", "Aviso", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar");
                active = true;


                for (int i = carrito.size()-1; i >=0 ; i--) {

                    carrito.remove(i);
                }
                txtClientAmount.setText("");
                total = 0;
                totalPrice.setText("");
                y=1;
                refreshTable();
                cancel();
            }
            else{
                String [] opcion = {"Aceptar"};      
                JOptionPane.showOptionDialog(null, "Faltan: $" + (dinero*-1) +" para pagar el/los productos.", "Aviso", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar");
            }
        }
        
        else{
            if (!carrito.isEmpty()) {
                String monto = txtClientAmount.getText();
                addVenta();
                int dinero = Integer.parseInt(monto);
                dinero = dinero - total;

                if(dinero>=0){
                    String [] opcion = {"Aceptar"};

                    JOptionPane.showOptionDialog(null, "El vuelto es de: $" + dinero +".", "Aviso", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar");


                    paneCash.setVisible(false);
                    for (int i = carrito.size()-1; i >=0 ; i--) {

                        carrito.remove(i);
                    }
                    txtClientAmount.setText("");
                    total = 0;
                    totalPrice.setText("");
                    y=1;
                    refreshTable();
                }
                else{
                    String [] opcion = {"Aceptar"};      
                    JOptionPane.showOptionDialog(null, "Faltan: $" + (dinero*-1) +" para pagar el/los productos.", "Aviso", 0, JOptionPane.QUESTION_MESSAGE, null, opcion, "Aceptar");
                }
            }
            
            
        }
    }
    
    @FXML
    void addVenta() {
        Venta v = new Venta();
        v.setIdvendedor(user);
        v.setNumeroserie(651);
        v.setFecha(new Date());
        v.setMonto(total);
        if (!daoV.create(v)) {
            JOptionPane.showMessageDialog(null, "No se guardó la venta");
        }
    }

    @FXML
    void manage() {
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
