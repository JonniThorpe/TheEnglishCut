package com.theenglishcut.dao;

import com.theenglishcut.entity.ProductToOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoAProductoRepository extends JpaRepository<ProductToOrderEntity, Integer> {
}
