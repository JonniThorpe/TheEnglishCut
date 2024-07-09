package com.theenglishcut.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    private String nombre;

    private String descripcion;

    private Double precio;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "Inventario")
    private StockEntity inventario;

    @OneToMany(mappedBy = "producto",fetch = FetchType.EAGER)
    private List<ProductToOrderEntity> pedidos;

    @OneToMany(mappedBy = "producto")
    private List<ProductToCategoryEntity> categorias;

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

    public StockEntity getInventario() {
        return inventario;
    }

    public void setInventario(StockEntity inventario) {
        this.inventario = inventario;
    }

    public List<ProductToOrderEntity> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<ProductToOrderEntity> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ProductToCategoryEntity> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<ProductToCategoryEntity> categorias) {
        this.categorias = categorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}

