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

    public Producto(int id, String name, int precio) {
        this.id = id;
        this.name = name;
        this.precio = precio;
    }

    public Producto() {
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
        return "Producto{" + "id=" + id + ", name=" + name + ", precio=" + precio + '}';
    }   
}
