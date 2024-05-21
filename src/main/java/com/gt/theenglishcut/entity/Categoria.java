package com.gt.theenglishcut.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private List<ProductoaCategoria> productos;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ProductoaCategoria> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoaCategoria> productos) {
        this.productos = productos;
    }

    // getters and setters
}

