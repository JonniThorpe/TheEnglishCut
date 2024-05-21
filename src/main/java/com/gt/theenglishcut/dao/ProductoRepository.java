package com.gt.theenglishcut.dao;

import com.gt.theenglishcut.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
