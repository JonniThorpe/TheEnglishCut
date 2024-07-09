package com.theenglishcut.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class StockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private Integer cantidad;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<ProductEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductEntity> productos) {
        this.productos = productos;
    }

    @OneToMany(mappedBy = "inventario")
    private List<ProductEntity> productos;

    // getters and setters
}

