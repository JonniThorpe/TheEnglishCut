package com.gt.theenglishcut.controller;

import com.gt.theenglishcut.dao.*;
import com.gt.theenglishcut.entity.*;
import com.gt.theenglishcut.ui.Usuario_provisioal;
import com.gt.theenglishcut.ui.producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Controlador {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoAProductoRepository PedidoaProductoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    private Pedido pedidoCliente = new Pedido();

    List<Producto> productosCarrito = new ArrayList<>();

    /**
     * Metodo que devuelve el incio de la página
     * @return home.jsp
     */
    @GetMapping("/")
    public String doInicio () {
        return "/home";
    }

    @PostMapping("/loginUser")
    public String login(@RequestParam(value = "nombre",required = false)String user, HttpSession sesion){

        Usuario usuario = usuarioRepository.findByNombreUser(user);
        sesion.setAttribute("user",usuario.getNombre());
        sesion.setAttribute("tipo",usuario.getRol().getNombre());
        return "redirect:/";
    }

    /**
     * Devuelve el listado de productos de la web
     * @return listadoProductos.jsp
     */
    @GetMapping("/listadoProductos")
    public String verProductos (Model model) {

        List<Producto> productoList = productoRepository.findAll();
        if(productoList == null || productoList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("productos", productoList);
        return "/listadoProductos";
    }

    /**
     * Devuelve la pagina login para acceder con un usuario
     * @return login.jsp
     */
    @GetMapping("/login")
    public String doLogin () {
        return "/login";
    }
    @GetMapping("/CrearProducto")
    public String crearProducto (Model modelo) {
        modelo.addAttribute("producto",  new producto());
        modelo.addAttribute("cantidadProducto",6);
        return "/CrearProducto";
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto (Model modelo, @ModelAttribute("producto")producto producto) {
        Producto productoNuevo = new Producto();
        Inventario inventario = new Inventario();

        inventario.setCantidad(producto.getCantidad());
        inventarioRepository.save(inventario);

        productoNuevo.setInventario(inventario);
        productoNuevo.setNombre(producto.getNombre());
        productoNuevo.setDescripcion(producto.getDescripcion());
        productoNuevo.setImagen(producto.getImagen());
        productoNuevo.setPrecio(producto.getPrecio());

        productoRepository.save(productoNuevo);
        return "redirect:/listadoProductos";
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto (Model model, @RequestParam("id")int id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto == null){
            System.out.println("ningun producto eliminado, no deberia ser null el producto");
            return "redirect:/listadoProductos";
        }
        productoRepository.deleteById(id);
        inventarioRepository.deleteById(producto.getInventario().getID());
        return "redirect:/listadoProductos";
    }

    //hacer el añadir pedido
    @GetMapping("/addProducto")
    public String addProducto (@RequestParam("id")int id) {
        productosCarrito.add(productoRepository.findById(id).get());
        return "redirect:/listadoProductos";
    }

    @GetMapping("/confirmarPedidoCliente")
    public String confirmarPedidoCliente (Model model) {
        model.addAttribute("productosCarrito", productosCarrito);
        return "/Carrito";
    }

    //TODO NO ENTRA POR EL METODO ENTONCES NO ASIGNA EL PEDIDO A CLIENTE NI A PRODUCTO
    @PostMapping("/confirmarPedido")
    public String confirmar_Producto_a_Pedido (HttpSession sesion, @RequestParam("listaProductos")List<Producto> listaPedido) {

        Usuario user = (Usuario) sesion.getAttribute("usuario");

        ProductoaPedido productoaPedido = new ProductoaPedido();
        productoaPedido.setPedido(pedidoCliente);


        pedidoCliente.setUsuario(user);
        pedidoCliente.getProductos().add(productoaPedido);


        for(Producto productoConfirmado:listaPedido){
            productoConfirmado.getPedidos().add(productoaPedido);
            productoaPedido.setProducto(productoConfirmado);
            productoRepository.save(productoConfirmado);
        }


        pedidoRepository.save(pedidoCliente);
        PedidoaProductoRepository.save(productoaPedido);

        //Limpiamos el carrito para nuevos productos
        productosCarrito.clear();

        return "redirect:/listadoProductos";
    }




    @GetMapping("/listarPedidos")
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "/listadoPedidos";
    }


}
