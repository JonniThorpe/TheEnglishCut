package com.gt.theenglishcut.dao;

import com.gt.theenglishcut.entity.Categoria;
import com.gt.theenglishcut.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {

}
