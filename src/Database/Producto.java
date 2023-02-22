/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Gama
 */
public class Producto {
    private int id;
    private String name;
    private int precio;
    private String codigo;
    private int cantidad;

    public Producto(int id, String name, int precio, String codigo, int cantidad) {

        this.id = id;
        this.name = name;
        this.precio = precio;
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public Producto() {
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }   

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", name=" + name + ", precio=" + precio + ", cod=" + codigo + '}';
    }   
}
