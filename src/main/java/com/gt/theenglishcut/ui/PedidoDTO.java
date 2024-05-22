package com.gt.theenglishcut.ui;

import com.gt.theenglishcut.entity.Producto;

import java.util.List;

public class PedidoDTO {
    List<Producto> productosPedido;

    public List<Producto> getProductosPedido() {
        return productosPedido;
    }

    public void setProductosPedido(List<Producto> productosPedido) {
        this.productosPedido = productosPedido;
    }
}
