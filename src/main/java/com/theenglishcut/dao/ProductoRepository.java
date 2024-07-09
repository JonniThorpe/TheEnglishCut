package com.theenglishcut.dao;

import com.theenglishcut.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<ProductEntity, Integer> {

}
