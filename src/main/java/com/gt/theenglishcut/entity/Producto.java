package com.gt.theenglishcut.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String nombre;

    private String descripcion;

    private Double precio;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "Inventario")
    private Inventario inventario;

    @OneToMany(mappedBy = "producto")
    private List<ProductoaPedido> pedidos;

    @OneToMany(mappedBy = "producto")
    private List<ProductoaCategoria> categorias;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public List<ProductoaPedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ProductoaPedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ProductoaCategoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<ProductoaCategoria> categorias) {
        this.categorias = categorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

