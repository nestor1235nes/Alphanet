/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.math.BigInteger;

/**
 *
 * @author ramir
 */
public class instanceProduct {
    private int id;
    private String name;
    private int precio;
    private BigInteger codigo;
    private int cant;
    private int total = 0;
    

    public instanceProduct(Producto p) {

        this.id = p.getId();
        this.name = p.getName();
        this.precio = p.getPrecio();
        this.codigo = p.getCodigo();
        this.cant = 1;
    }

    public instanceProduct() {

    }
    
    
    public int getCant(){
        return cant;
    }
    
    public void setCant(int cant){
        this.cant = cant;
    }
    
    public BigInteger getCodigo() {
        return codigo;
    }

    public void setCodigo(BigInteger codigo) {
        this.codigo = codigo;
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
    
    public void aumentar (){
        cant++;
    }
    
    public void precioTotal(int nuevo){
        
        total = total + nuevo;
    }
    public int getTotalPrice(){
        return total;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", name=" + name + ", precio=" + precio + ", cod=" + codigo + '}';
    }  
}
