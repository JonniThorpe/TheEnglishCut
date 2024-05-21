package com.gt.theenglishcut.dao;

import com.gt.theenglishcut.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
