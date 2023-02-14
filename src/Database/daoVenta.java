/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Gama
 */
public class daoVenta {
    Conexion c;
    
    public daoVenta(){
       c = new Conexion();
    }
    
    /*public String nroSerieVentas(){
        String serie = "";
        String sql = "SELECT max(numeroserie) from ventas";
        try {
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
        }
    }*/
    
    public boolean create(Venta v){
        try {
            String sql = "INSERT INTO ventas(idvendedor,numeroserie,fecha,monto) VALUES(?,?,?,?,?)";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ps.setInt(1, v.getIdvendedor());
            ps.setInt(2, v.getNumeroserie());
            ps.setDate(3, (Date) v.getFecha());   
            ps.setInt(4, v.getMonto());
            ps.execute();
            ps.close();
            ps = null;
            c.desconectar();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public ObservableList<Venta> read(){
        ObservableList<Venta> lista = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM ventas";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Venta v = new Venta();
                v.setId(rs.getInt("id"));
                v.setIdvendedor(rs.getInt("idvendedor"));
                v.setNumeroserie(rs.getInt("numeroserie"));
                v.setFecha(rs.getDate("fecha"));
                v.setMonto(rs.getInt("monto"));
                lista.add(v);
            }
            ps.close();
            ps = null;
            c.desconectar();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return lista;
    }
    
    public boolean delete(int id){
        try {
            String sql = "DELETE FROM ventas WHERE id=?";
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
    
    public boolean update(Venta v){
        try {
            String sql = "UPDATE ventas SET idvendedor=?,numeroserie=?,fecha=?,monto=? WHERE id=?";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ps.setInt(1, v.getIdvendedor());
            ps.setInt(2, v.getNumeroserie());
            ps.setDate(3, (Date) v.getFecha());
            ps.setInt(1, v.getMonto());
            ps.execute();
            ps.close();
            ps = null;
            c.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } 
    }
}
