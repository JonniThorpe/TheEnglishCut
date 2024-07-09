package com.theenglishcut.entity;

import jakarta.persistence.*;

@Entity
public class ProductToOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @ManyToOne
    @JoinColumn(name = "Producto")
    private ProductEntity producto;

    @ManyToOne
    @JoinColumn(name = "Pedido")
    private OrderEntity pedido;

    private Integer cantidad;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId() {
        return ID;
    }

    public void setId(Integer id) {
        this.ID = id;
    }

    public ProductEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductEntity producto) {
        this.producto = producto;
    }

    public OrderEntity getPedido() {
        return pedido;
    }

    public void setPedido(OrderEntity pedido) {
        this.pedido = pedido;
    }
}
