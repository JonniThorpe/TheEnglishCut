package com.gt.theenglishcut.dao;

import com.gt.theenglishcut.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Integer> {
}
