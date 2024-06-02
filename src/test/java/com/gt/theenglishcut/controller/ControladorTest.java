package com.gt.theenglishcut.controller;

import com.gt.theenglishcut.dao.CategoriaRepository;
import com.gt.theenglishcut.entity.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ControladorTest {

    @Mock
    private CategoriaRepository categoriaRepository;
    private Producto producto;
    private Inventario inventario;
    private Categoria categoria;
    private ProductoaCategoria productoaCategoria;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // inicializo un producto
        inventario = new Inventario();
        inventario.setCantidad(1);

        categoria = new Categoria();
        categoria.setNombre("TODO");



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
    void verCategorias() {
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria));
        assertNotNull(categoriaRepository.findAll());
        assertEquals(categoriaRepository.findAll().size(), 1);
        assertEquals(categoriaRepository.findAll().get(0), categoria);
    }
}