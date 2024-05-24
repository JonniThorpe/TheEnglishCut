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

import java.util.*;

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


    Map<Producto,Integer> productosCarrito= new Hashtable<>();

    /**
     * Devuelve la pagina login para acceder con un usuario
     * @return login.jsp
     */
    @GetMapping("/login")
    public String doLogin () {
        return "/login";
    }

    @PostMapping("/loginUser")
    public String login(@RequestParam(value = "nombre",required = false)String user, HttpSession sesion){

        Usuario usuario = usuarioRepository.findByNombreUser(user);
        sesion.setAttribute("user",usuario.getNombre());
        sesion.setAttribute("tipo",usuario.getRol().getNombre());
        return "redirect:/";
    }

    /**
     * Metodo que devuelve el incio de la página
     * @return home.jsp
     */
    @GetMapping("/")
    public String doInicio () {
        return "/home";
    }

    /**
     * Devuelve el listado de productos de la web
     * @return listadoProductos.jsp
     */
    @GetMapping("/listadoProductos")
    public String verProductos (Model model) {

        List<Producto> productoList = productoRepository.findAll();
        if(productoList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("productos", productoList);
        return "/listadoProductos";
    }

    @GetMapping("/listadoProductosComida")
    public String verProductosComida (Model model) {

        List<Producto> productoList = buscarProductosFiltro("COMIDA");

        if(productoList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }
        model.addAttribute("productosComida", productoList);
        return "/listadoProductosComida";
    }

    @GetMapping("/listadoProductosRopa")
    public String verProductosRopa (Model model) {

        List<Producto> productoList = buscarProductosFiltro("ROPA");

        if(productoList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("productosRopa", productoList);
        return "/listadoProductosRopa";
    }

    @GetMapping("/listadoProductosMuebles")
    public String verProductosMuebles (Model model) {

        List<Producto> productoList = buscarProductosFiltro("MUEBLES");

        if(productoList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("productosMuebles", productoList);
        return "/listadoProductosMuebles";
    }

    private List<Producto> buscarProductosFiltro(String categoria) {

        List<Producto> aux = productoRepository.findAll();
        List<Producto> productoList = new ArrayList<>();
        boolean anadir = false;

        for(Producto p:aux){
            for(ProductoaCategoria c:p.getCategorias()){
                if(c.getCategoria().getNombre().equals(categoria)){
                    anadir = true;
                    break;
                }
            }
            if(anadir){
                productoList.add(p);
                anadir = false;
            }
        }

        return productoList;
    }

    @GetMapping("/listarPedidos")
    public String listar_Pedidos(Model model) {
        List<Pedido> pedidoList = pedidoRepository.findAll();
        if(pedidoList == null || pedidoList.isEmpty()){
            model.addAttribute("mensaje", "No hay pedidos");
        }
        model.addAttribute("pedidos", pedidoList);

        return "/ListadoPedidos";
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
        Producto producto_meter = productoRepository.findById(id).get();//Este va a ser el producto que vamos a meter
        boolean existe=false;
        for(Producto producto: productosCarrito.keySet()){
            if(producto.getNombre().toLowerCase().equals(producto_meter.getNombre().toLowerCase())){
                existe=true;
                int unidades = productosCarrito.get(producto)+1;
                productosCarrito.put(producto,unidades);
            }
        }
        if(!existe){
            productosCarrito.put(producto_meter,1);
        }
        return "redirect:/listadoProductos";
    }

    @GetMapping("/confirmarPedidoCliente")
    public String confirmarPedidoCliente (Model model) {
        model.addAttribute("productosCarrito", productosCarrito);
        return "/Carrito";
    }

    //TODO NO ENTRA POR EL METODO ENTONCES NO ASIGNA EL PEDIDO A CLIENTE NI A PRODUCTO
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


        for(Producto productoConfirmado:productosCarrito.keySet()){
            ProductoaPedido productoaPedido = new ProductoaPedido();


            productoaPedido.setProducto(productoConfirmado);
            productoaPedido.setPedido(pedidoCliente);


            PedidoaProductoRepository.save(productoaPedido);
            productoaPedidoLista.add(productoaPedido);

            productoConfirmado.setPedidos(productoaPedidoLista);
            productoRepository.save(productoConfirmado);

        }
        pedidoCliente.setProductos(productoaPedidoLista);

        pedidoRepository.save(pedidoCliente);



        //Limpiamos el carrito para nuevos productos y nuevo pedido
        productosCarrito.clear();

        pedidoCliente = new Pedido();

        return "redirect:/listadoProductos";
    }
}
