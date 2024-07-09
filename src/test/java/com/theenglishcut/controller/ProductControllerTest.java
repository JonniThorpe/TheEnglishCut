package com.theenglishcut.controller;

import com.theenglishcut.dao.CategoriaRepository;
import com.theenglishcut.entity.CategoryEntity;
import com.theenglishcut.entity.ProductEntity;
import com.theenglishcut.entity.StockEntity;
import com.theenglishcut.entity.ProductToCategoryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    private ProductEntity productEntity;
    private StockEntity stockEntity;
    private CategoryEntity categoryEntity;
    private ProductToCategoryEntity productToCategoryEntity;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // inicializo un producto
        stockEntity = new StockEntity();
        stockEntity.setCantidad(1);

        categoryEntity = new CategoryEntity();
        categoryEntity.setNombre("TODO");



        /**
         *         productoaCategoria = new ProductoaCategoria();
         *         productoaCategoria.setCategoria(categoria);
         *
         *         List<ProductoaCategoria> lista = Mockito.anyList();
         *         lista.add(productoaCategoria);
         *
         *
         *         producto = new Producto();
         *         producto.setDescripcion("hola");
         *         producto.setInventario(inventario);
         *         producto.setCategorias(lista);
         *         producto.setImagen("patanegra.jpg");
         *
         */

    }

    @Test
    void categoriaRepositoryNotNULL() {
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
        assertNotNull(categoriaRepository.findAll());
    }

    @Test
    void categoriaRepositorySize() {
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
        assertEquals(categoriaRepository.findAll().size(), 1);
    }

    @Test
    void categoriaRepositoryEqualsCategoria() {
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoryEntity));
        Assertions.assertEquals(categoriaRepository.findAll().get(0), categoryEntity);
    }
}