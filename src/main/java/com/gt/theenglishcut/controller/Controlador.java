package com.gt.theenglishcut.controller;

import com.gt.theenglishcut.dao.PedidoAProductoRepository;
import com.gt.theenglishcut.dao.PedidoRepository;
import com.gt.theenglishcut.dao.ProductoRepository;
import com.gt.theenglishcut.dao.UsuarioRepository;
import com.gt.theenglishcut.entity.Pedido;
import com.gt.theenglishcut.entity.Producto;
import com.gt.theenglishcut.entity.ProductoaPedido;
import com.gt.theenglishcut.entity.Usuario;
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

    private Pedido pedidoCliente = new Pedido();

    List<Producto> productos = new ArrayList<>();
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
        productoNuevo.setNombre(producto.getNombre());
        productoNuevo.setDescripcion(producto.getDescripcion());
        productoNuevo.setImagen(producto.getImagen());
        productoNuevo.setPrecio(producto.getPrecio());

        productoRepository.save(productoNuevo);
        return "redirect:/listadoProductos";
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto (Model model, @RequestParam("id")int id) {
        productoRepository.deleteById(id);

        return "redirect:/listadoProductos";
    }

    //hacer el añadir pedido
    @GetMapping("/addProducto")
    public String addProducto (Model model,@RequestParam("id")int id) {
        productos.add(productoRepository.findById(id).get());
        return "redirect:/listadoProductos";
    }

    //Sera el futuro confirmar pedido
    @GetMapping("/confirmarPedido")
    public String confirmar_Producto_a_Pedido (HttpSession sesion,@RequestParam("id")int id) {

        Producto producto = productoRepository.findById(id).get();

        ProductoaPedido productoaPedido = new ProductoaPedido();
        productoaPedido.setPedido(pedidoCliente);
        productoaPedido.setProducto(producto);
        Usuario user = (Usuario) sesion.getAttribute("usuario");

        pedidoCliente.setUsuario(user);
        pedidoCliente.getProductos().add(productoaPedido);

        producto.getPedidos().add(productoaPedido);

        productoRepository.save(producto);
        pedidoRepository.save(pedidoCliente);
        PedidoaProductoRepository.save(productoaPedido);

        return "redirect:/listadoProductos";
    }




    @GetMapping("/listarPedidos")
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "/listadoPedidos";
    }


}
