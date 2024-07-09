package com.theenglishcut.dao;

import com.theenglishcut.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<StockEntity, Integer> {
}
