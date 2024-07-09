package com.theenglishcut.dao;

import com.theenglishcut.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<OrderEntity, Integer> {
    @Query("select p from OrderEntity p where p.usuario.ID = :ID")
    public List<OrderEntity> findPedidoByUser(@Param("ID")int ID);

    @Query("select p from OrderEntity p where p.usuario.nombre = : nombre")
    public List<OrderEntity> findPedidoByUserName(@Param("nombreUsuario")String nombre);
}
