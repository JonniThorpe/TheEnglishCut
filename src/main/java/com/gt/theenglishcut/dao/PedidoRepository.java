package com.gt.theenglishcut.dao;

import com.gt.theenglishcut.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("select p from Pedido p where p.usuario.ID = :ID")
    public List<Pedido> findPedidoByUser(@Param("ID")int ID);

    @Query("select p from Pedido p where p.usuario.nombre = : nombre")
    public List<Pedido> findPedidoByUserName(@Param("nombreUsuario")String nombre);
}
