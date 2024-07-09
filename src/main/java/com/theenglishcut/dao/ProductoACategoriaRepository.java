package com.theenglishcut.dao;

import com.theenglishcut.entity.ProductToCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductoACategoriaRepository extends JpaRepository<ProductToCategoryEntity,Integer> {
    @Query("delete ProductToCategoryEntity pc where pc.producto.ID = :id ")
    @Modifying
    @Transactional
    void deleteByProductID(@Param("id") Integer id);
}
