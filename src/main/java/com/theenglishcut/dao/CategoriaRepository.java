package com.theenglishcut.dao;

import com.theenglishcut.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository  extends JpaRepository<CategoryEntity, Integer> {

}
