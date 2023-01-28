/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Database.Producto;
import Database.daoProducto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Gama
 */
public class ViewProductController implements Initializable {
    
    Producto p;
    daoProducto dao = new daoProducto();
    ObservableList<Producto> lista;
    int id;
    
    @FXML
    private TableColumn<Producto, String> idCol;

    @FXML
    private TableColumn<Producto, String> nameCol;

    @FXML
    private TableColumn<Producto, String> priceCol;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TableView<Producto> productTable;
    
    public void refreshTable(){
        idCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Producto,String>("precio"));
        try {       
            lista = dao.read();
            for(Producto p: lista){
                productTable.setItems(lista);
            }
        } catch (Exception e) {
            System.out.println("No se pudo actualizar la tabla");
        }
        
    }
    
    public void cleanTextField(){
        nameField.setText("");
        priceField.setText("");
    }

    @FXML
    void addProduct(ActionEvent event) {
        p = new Producto();
        p.setName(nameField.getText());
        p.setPrecio(Integer.parseInt(priceField.getText()));
        if (!dao.create(p)) {
            JOptionPane.showMessageDialog(null, "No se inserto el producto");
        }
        refreshTable();
        cleanTextField();
    }
    
    @FXML
    void clickProduct(MouseEvent event) {
        p = productTable.getSelectionModel().getSelectedItem();
        nameField.setText(p.getName());
        priceField.setText(Integer.toString(p.getPrecio()));
    }

    @FXML
    void cleanButton(ActionEvent event) {
        cleanTextField();
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        int x = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar este registro?");
        if (x == 0 && p.getId() > 0) {
            if (!dao.delete(p.getId())) {
                JOptionPane.showMessageDialog(null, "No se elimino el registro");
            }
        }
        refreshTable();
    }

    @FXML
    void pdfButton(ActionEvent event) {
        try {
            FileOutputStream archivo;
            File file = new File("src\\pdf\\reporte.pdf");
            archivo = new FileOutputStream(file);
            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();
            Image img = Image.getInstance("src\\Images\\alphanet.jpg");
            img.setAlignment(Element.ALIGN_CENTER);
            img.scaleToFit(100, 100);
            doc.add(img);
            Paragraph p = new Paragraph(10);
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
            p.add(Chunk.NEWLINE);
            p.add("Catalogo de productos");
            p.add(Chunk.NEWLINE);
            p.add(Chunk.NEWLINE);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            PdfPTable tabla = new PdfPTable(3);
            tabla.setWidthPercentage(100);
            PdfPCell c1 = new PdfPCell(new Phrase("ID", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("NOMBRE", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("PRECIO", negrita));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tabla.addCell(c1);
            tabla.addCell(c2);
            tabla.addCell(c3);
            lista = dao.read();
            for (Producto product : lista) {
                tabla.addCell(""+product.getId());
                tabla.addCell(product.getName());
                tabla.addCell(""+product.getPrecio());
            }
            doc.add(tabla);
            p = new Paragraph(10);
            p.add(Chunk.NEWLINE);
            p.add("Numero de registros: " + lista.size());
            p.add(Chunk.NEWLINE);
            p.add(Chunk.NEWLINE);
            p.setAlignment(Element.ALIGN_RIGHT);
            doc.add(p);
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear archivo");
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear documento PDF");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear I/O");
        }
        
    }

    @FXML
    void saveProduct(ActionEvent event) {
        p.setName(nameField.getText());
        p.setPrecio(Integer.parseInt(priceField.getText()));
        if (!dao.update(p)) {
            JOptionPane.showMessageDialog(null, "No se actualizo el registro");
        }
        refreshTable();
        cleanTextField();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();
    }    
    
}
