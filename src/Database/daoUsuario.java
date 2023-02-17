/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Gama
 */
public class daoUsuario {
    Conexion c;
    
    public daoUsuario(){
       c = new Conexion();
    }
    
    public boolean create(Usuario u){
        try {
            String sql = "INSERT INTO usuarios(nombre,usuario,pass) VALUES(?,?,?)";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getPass());
            ps.execute();
            ps.close();
            ps = null;
            c.desconectar();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public ObservableList<Usuario> read(){
        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM usuarios";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setUsuario(rs.getString("usuario"));
                u.setPass(rs.getString("pass"));
                lista.add(u);
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
            String sql = "DELETE FROM usuarios WHERE id=?";
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
    
    public boolean update(Usuario u){
        try {
            String sql = "UPDATE usuarios SET nombre=?,usuario=?,pass=? WHERE id=?";
            PreparedStatement ps = c.conectar().prepareStatement(sql);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getPass());
            ps.setInt(4, u.getId());
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
