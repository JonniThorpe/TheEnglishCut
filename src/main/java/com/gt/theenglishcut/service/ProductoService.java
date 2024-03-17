package com.gt.theenglishcut.service;

import com.gt.theenglishcut.model.Producto;
import com.gt.theenglishcut.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
@Autowired
    ProductRepository productRepository;
    public List<Producto> getProduct(){

        //return productRepository.findAll();

        return List.of(
                new Producto(1,
                        "raqueta",
                        "raqueta standard de padel",
                        40.5,
                        10,
                        ""
                )
        );

    }

    public Producto guardarProducto(Producto product){
        return productRepository.save(product);
    }
    /**
     * obtiene el  objeto por id si este existe,  si no devuelve un null
     */
    public Optional<Producto> getIdProducto(Long id){
        return productRepository.findById(id);
    }

    public Producto modificarProducto(Producto product, Long id){
        Producto producto = productRepository.findById(id).get();
        producto.setNombre(producto.getNombre());
        producto.setDescripcion(producto.getDescripcion());
        producto.setCantidad(producto.getCantidad());
        producto.setPrecio(producto.getPrecio());
        return product;
    }

    public Boolean eliminarProducto(Long id){
        try {
            productRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
