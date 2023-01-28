/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Gama
 */
public class daoProducto {
    Conexion c;
    
    public daoProducto(){
       c = new Conexion();
    }
    
    public boolean create(Producto p){
        try {
            String sql = "INSERT INTO productos(nombre,precio) VALUES(?,?)";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrecio());
            ps.execute();
            ps.close();
            ps = null;
            c.desconectar();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public ObservableList<Producto> read(){
        ObservableList<Producto> lista = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM productos";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Producto p = new Producto();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("nombre"));
                p.setPrecio(rs.getInt("precio"));
                lista.add(p);
            }
            ps.close();
            ps = null;
            c.desconectar();
        } catch (SQLException ex) {
        }
        return lista;
    }
    
    public boolean delete(int id){
        try {
            String sql = "DELETE FROM productos WHERE id=?";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            ps = null;
            c.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        }   
    }
    
    public boolean update(Producto p){
        try {
            System.out.println(p.getName());
            System.out.println(p.getId());
            System.out.println(p.getPrecio());
            String sql = "UPDATE productos SET nombre=?,precio=? WHERE id=?";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrecio());
            ps.setInt(3, p.getId());
            ps.execute();
            ps.close();
            ps = null;
            c.desconectar();
            return true;
        } catch (SQLException ex) {
            return false;
        } 
    }
}
