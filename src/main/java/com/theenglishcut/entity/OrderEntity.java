package com.theenglishcut.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private String entrega;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private UserEntity usuario;

    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER)
    private List<ProductToOrderEntity> productos;

    public List<ProductToOrderEntity> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductToOrderEntity> productos) {
        this.productos = productos;
    }

    public UserEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UserEntity usuario) {
        this.usuario = usuario;
    }

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }



    // getters and setters
}
