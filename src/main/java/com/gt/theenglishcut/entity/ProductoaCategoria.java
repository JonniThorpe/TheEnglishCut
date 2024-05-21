package com.gt.theenglishcut.entity;
import jakarta.persistence.*;

@Entity
public class ProductoaCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "Producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "Categoria")
    private Categoria categoria;

    public Integer getId() {
        return ID;
    }

    public void setId(Integer id) {
        this.ID = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}

