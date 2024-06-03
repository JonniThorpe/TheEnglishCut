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

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private RolRepository rolRepository;

    private Pedido pedidoCliente = new Pedido();

    private String categoriaGlobal;

    Map<Producto,Integer> productosCarrito= new Hashtable<>();

    private List<Pedido> pedidoList;

    private Boolean filtro = true;

    /**
     * Devuelve la pagina login para acceder con un usuario
     * @return login.jsp
     */
    @GetMapping("/login")
    public String doLogin () {
        return "/login";
    }

    @PostMapping("/loginUser")
    public String login(Model modelo,@RequestParam(value = "nombre",required = false)String user, @RequestParam(value = "password",required = false)String password, HttpSession sesion){

        Usuario usuario = usuarioRepository.findByNombreUser(user);

        if(user == null){
            modelo.addAttribute("error","no existe ese usuario, prueba a registrarte antes de hacer login");
            return "/error?tipo=login";
        }else if(password == null || !password.equals(usuario.getPassword())){
            modelo.addAttribute("error","la contraseña no coincide, prueba a prueba otra contraseña para hacer login hacer login");
            return "/error?tipo=login";
        }

        sesion.setAttribute("user",usuario.getNombre());
        sesion.setAttribute("tipo",usuario.getRol().getNombre());

        categoriaGlobal = "TODO";
        productosCarrito.clear();

        return "redirect:/";
    }

    @GetMapping("/register")
    public String doRegister (HttpSession sesion, Model modelo  ) {

        String user = (String) sesion.getAttribute("user");
        Usuario usuario = usuarioRepository.findByNombreUser(user);

        if(usuario != null && usuario.getRol().getNombre().equals("Administrador")){
            modelo.addAttribute("roles", rolRepository.findAll());
        }
        return "/register";
    }

    @PostMapping("/registerUser")
    public String register(HttpSession sesion, Model modelo, @RequestParam(value = "nombre",required = false)String user, @RequestParam(value = "password",required = false)String password, @RequestParam(value="rolId")Integer rolId){

        Usuario usuario = usuarioRepository.findByNombreUser(user);

        if(usuario != null){
            modelo.addAttribute("error","ya existe ese usuario, prueba a registrarte con otro nombre de usuario");
            return "redirect:/error?tipo=register";
        }

        usuario = new Usuario();

        usuario.setNombre(user);
        usuario.setPassword(password);

        usuario.setRol(rolRepository.findById(rolId).get());
        usuarioRepository.save(usuario);

        //designamoos como el nuevo usuario en uso para la web
        sesion.setAttribute("user",usuario.getNombre());
        sesion.setAttribute("tipo",usuario.getRol().getNombre());

        categoriaGlobal = "TODO";
        productosCarrito.clear();

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout (HttpSession sesion) {

        sesion.setAttribute("user",null);
        sesion.setAttribute("tipo",null);

        categoriaGlobal = "TODO";

        return "redirect:/";
    }

    @GetMapping("/error")
    public String doError (@RequestParam("tipo")String tipo, Model modelo) {
        modelo.addAttribute("tipo",tipo);
        return "/error";
    }

    /**
     * Metodo que devuelve el incio de la página
     * @return home.jsp
     */
    @GetMapping("/")
    public String doInicio (HttpSession sesion) {
        List<Categoria> categoriaList = categoriaRepository.findAll();
        List<String> lista = new ArrayList<>();

        for(Categoria categoria:categoriaList){
            lista.add(categoria.getNombre());
        }

        sesion.setAttribute("listaCategoriasNombres", lista);
        return "/home";
    }

    /**
     * Devuelve el listado de productos de la web
     * @return listadoProductos.jsp
     */
    @GetMapping("/listadoProductos")
    public String verProductos (Model model, @RequestParam("Categoria") String categoria) {

        List<Producto> productoList = buscarProductosFiltro(categoria);
        if(productoList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("productos", productoList);
        return "/listadoProductos";
    }

    private List<Producto> buscarProductosFiltro(String categoria) {

        List<Producto> aux = productoRepository.findAll();
        List<Producto> productoList = new ArrayList<>();
        categoriaGlobal= categoria;
        boolean anadir = false;

        if(categoria.equals("TODO")){
            productoList = aux;
        }else{

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
        }

        return productoList;
    }

    @GetMapping("/Navbar")
    public String verCategorias (Model model) {

        List<Categoria> categoriaList = categoriaRepository.findAll();
        List<String> lista = new ArrayList<>();

        for(Categoria categoria:categoriaList){
            lista.add(categoria.getNombre());
        }


        model.addAttribute("listaCategoriasNombres", lista);
        return "/Navbar";
    }

    @GetMapping("/listarPedidos")
    public String listar_Pedidos(Model model, HttpSession sesion) {
        String user = (String) sesion.getAttribute("user");
        Usuario usuario = usuarioRepository.findByNombreUser(user);
        List<Usuario> usuarioList = usuarioRepository.findAll();

        if(usuario.getRol().getNombre().equals("Administrador") && filtro){
            pedidoList = pedidoRepository.findAll();
        }else if(usuario.getRol().getNombre().equals("Usuario") || usuario.getRol().getNombre().equals("Empleado")){
            pedidoList = pedidoRepository.findPedidoByUser(usuario.getID());
        }

        if(pedidoList == null || pedidoList.isEmpty()){
            model.addAttribute("mensaje", "No hay pedidos");
        }
        model.addAttribute("pedidos", pedidoList);
        model.addAttribute("usuarios", usuarioList);
        model.addAttribute("tipoUsuario", usuario.getRol().getNombre());

        return "/ListadoPedidos";
    }

    @PostMapping("/filtrar")
    public String filtrarPedidos(Model model, @RequestParam("idUsuario")Integer idUsuario){
        if(idUsuario != 0){
            filtro = false;
            pedidoList = pedidoRepository.findPedidoByUser(idUsuario);
        }else{
            filtro=true;
        }
        return "redirect:/listarPedidos";
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
        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto (Model model, @RequestParam("id")int id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto == null){
            System.out.println("ningun producto eliminado, no deberia ser null el producto");
            return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
        }
        productoRepository.deleteById(id);
        inventarioRepository.deleteById(producto.getInventario().getID());
        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
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
        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
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

        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
    }
}
