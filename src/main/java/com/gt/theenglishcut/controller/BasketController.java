package com.gt.theenglishcut.controller;

import com.gt.theenglishcut.dao.*;
import com.gt.theenglishcut.entity.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("Basket")
public class BasketController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private InventarioRepository inventarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private PedidoAProductoRepository PedidoaProductoRepository;

    Map<Producto,Integer> productosCarrito= new Hashtable<>();
    private Pedido pedidoCliente = new Pedido();

    @GetMapping("/addProducto")
    public String addProducto (@RequestParam("id")int id) {
        Producto producto_meter = productoRepository.findById(id).get();//Este va a ser el producto que vamos a meter
        boolean existe=false;
        for(Producto producto: productosCarrito.keySet()){
            if(producto.getID().equals(producto_meter.getID())){
                existe=true;
                int unidades = productosCarrito.get(producto)+1;
                productosCarrito.put(producto,unidades);
            }
        }
        if(!existe){
            productosCarrito.put(producto_meter,1);
        }
        return "redirect:/listadoProductos?Categoria=TODO";
    }

    @GetMapping("/confirmarPedidoCliente")
    public String confirmarPedidoCliente (Model model) {
        model.addAttribute("productosCarrito", productosCarrito);
        return "/Carrito";
    }

    @PostMapping("/confirmarPedido")
    public String confirmar_Producto_a_Pedido (HttpSession sesion) {

        String user = (String) sesion.getAttribute("user");
        if (user == null) {
            // Manejar el caso en que el usuario no esté autenticado
            return "redirect:/login"; // Redirigir a la página de inicio de sesión
        }
        Usuario clientePedido = usuarioRepository.findByNombreUser(user);


        List<ProductoaPedido> productoaPedidoLista = new ArrayList<>();


        //Creamos el pedido
        pedidoCliente.setUsuario(clientePedido);
        pedidoCliente.setFechaCreacion(new Date());
        pedidoCliente.setEntrega("NO CONFIRMADO");
        pedidoRepository.save(pedidoCliente);


        for(Map.Entry<Producto, Integer> mapaProducto : productosCarrito.entrySet()){
            Producto productoConfirmado = mapaProducto.getKey();

            ProductoaPedido productoaPedido = new ProductoaPedido();

            productoaPedido.setProducto(productoConfirmado);
            productoaPedido.setPedido(pedidoCliente);
            productoaPedido.setCantidad(mapaProducto.getValue());

            PedidoaProductoRepository.save(productoaPedido);
            productoaPedidoLista.add(productoaPedido);

            productoConfirmado.setPedidos(productoaPedidoLista);

            productoRepository.save(productoConfirmado);

            Inventario inventarioProducto;

            inventarioProducto = inventarioRepository.findById(productoConfirmado.getInventario().getID()).orElse(null);
            if(inventarioProducto != null && productoConfirmado.getInventario().getCantidad() > 0  ){
                inventarioProducto.setCantidad(productoConfirmado.getInventario().getCantidad() - mapaProducto.getValue());
                inventarioRepository.save(inventarioProducto);
            }

        }
        pedidoCliente.setProductos(productoaPedidoLista);

        pedidoRepository.save(pedidoCliente);

        //Limpiamos el carrito para nuevos productos y nuevo pedido
        productosCarrito.clear();

        pedidoCliente = new Pedido();

        return "redirect:/listadoProductos?Categoria=TODO";
    }

}
