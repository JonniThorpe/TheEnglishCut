package com.gt.theenglishcut.producto;

import com.gt.theenglishcut.model.Producto;
import com.gt.theenglishcut.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.color.ProfileDataException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalle/producto")
public class ProductoControlador {

    private final ProductoService contextProducto;
    @Autowired
    public ProductoControlador(ProductoService contextProducto) {
        this.contextProducto = contextProducto;
    }

    @GetMapping
    public List<Producto> getProduct(){
        return  contextProducto.getProduct();
    }

    @GetMapping(path ="/{id}")
    public Optional<Producto> getIdProducto(@PathVariable("id") Long id){
        return this.contextProducto.getIdProducto(id);
    }

    @PostMapping("/crearProducto")
    public Producto guardarProducto(@RequestBody Producto producto){
        return this.contextProducto.guardarProducto(producto);
    }

    @PostMapping(path ="/{id}")
    public Producto modificarProducto(@RequestBody Producto producto, @PathVariable("id")Long id){
        return this.contextProducto.modificarProducto(producto,id);
    }
    @DeleteMapping(path ="/{id}")
    public String eliminarProducto(@PathVariable("id")Long id){
        boolean ok = this.contextProducto.eliminarProducto(id);
        if(ok){
            return "Producto con id "+id+" eliminado.";
        }else{
            return "Producto no eliminado  adecuadamente";
        }
    }

}
