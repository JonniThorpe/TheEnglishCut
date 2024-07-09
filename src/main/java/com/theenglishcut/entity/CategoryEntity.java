package com.theenglishcut.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "category")
    private List<ProductToCategoryEntity> productos;

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

    public List<ProductToCategoryEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductToCategoryEntity> productos) {
        this.productos = productos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

