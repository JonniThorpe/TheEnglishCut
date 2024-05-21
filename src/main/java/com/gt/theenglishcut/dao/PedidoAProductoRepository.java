package com.gt.theenglishcut.dao;

import com.gt.theenglishcut.entity.ProductoaPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAProductoRepository extends JpaRepository<ProductoaPedido, Integer> {
}
