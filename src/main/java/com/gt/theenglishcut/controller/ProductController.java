package com.gt.theenglishcut.controller;

import com.gt.theenglishcut.dao.*;
import com.gt.theenglishcut.entity.*;
import com.gt.theenglishcut.ui.producto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProductController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoACategoriaRepository productoACategoriaRepository;

    @Autowired
    private RolRepository rolRepository;

    private String categoriaGlobal;


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
        //TODO el carrito debe estar vacio cuanddo un nuevo usuario se logee
        //productosCarrito.clear();

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
        //TODO el carrito debe estar vacio cuanddo un nuevo usuario se registre
        //productosCarrito.clear();

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

    @GetMapping("/Detail")
    public String product_Detail(@RequestParam("id")Integer id, Model modelo){
        Producto product = productoRepository.findById(id).get();

        modelo.addAttribute("product", product);
        return "/ProductDetail";
    }

    @GetMapping("/CrearProducto")
    public String crearProducto (Model modelo) {
        modelo.addAttribute("producto",  new producto());
        modelo.addAttribute("categorias",categoriaRepository.findAll());
        return "/CrearProducto";
    }

    @GetMapping("/modificarProducto")
    public String modificarProducto (Model modelo, @RequestParam("id") Integer id) {
        producto productoModificado = new producto();
        Producto producto = productoRepository.findById(id).get();

        productoModificado.setIdProducto(id);
        productoModificado.setNombre(producto.getNombre());
        productoModificado.setDescripcion(producto.getDescripcion());
        productoModificado.setPrecio(producto.getPrecio());
        productoModificado.setImagen(producto.getImagen());
        productoModificado.setCantidad(producto.getInventario().getCantidad());
        productoModificado.setCategorias(producto.getCategorias().stream()
                .map(ProductoaCategoria::getCategoria)
                .map(Categoria::getID)
                .collect(Collectors.toList()));

        modelo.addAttribute("categorias",categoriaRepository.findAll());
        modelo.addAttribute("producto",  productoModificado);
        return "/CrearProducto";
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto (Model model, @RequestParam("id")int id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if(producto == null){
            System.out.println("ningun producto eliminado, no deberia ser null el producto");
            return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
        }
        productoACategoriaRepository.deleteByProductID(id);
        productoRepository.deleteById(id);
        inventarioRepository.deleteById(producto.getInventario().getID());
        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto (Model modelo, @ModelAttribute("producto")producto producto) {
        Producto productoNuevo = new Producto();
        Inventario inventario = new Inventario();

        if(producto.getIdProducto() == null) {
            inventario.setCantidad(producto.getCantidad());
            inventarioRepository.save(inventario);
            productoNuevo.setInventario(inventario);

            GuardarObjetoProducto(producto, productoNuevo);
        }else{
            productoNuevo = productoRepository.findById(producto.getIdProducto()).orElse(null);

            inventario = productoNuevo.getInventario();
            inventario.setCantidad(producto.getCantidad());
            inventarioRepository.save(inventario);

            productoACategoriaRepository.deleteByProductID(producto.getIdProducto());
            productoNuevo.getCategorias().clear();

            GuardarObjetoProducto(producto, productoNuevo);

        }
        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
    }

    private void GuardarObjetoProducto(@ModelAttribute("producto") producto producto, Producto productoNuevo) {

        productoNuevo.setNombre(producto.getNombre());
        productoNuevo.setDescripcion(producto.getDescripcion());
        productoNuevo.setImagen(producto.getImagen());
        productoNuevo.setPrecio(producto.getPrecio());
        productoRepository.save(productoNuevo);

        List<ProductoaCategoria> categoriasProducto = new ArrayList<>();

        for (Categoria c : categoriaRepository.findAllById(producto.getCategorias())) {
            ProductoaCategoria pc = new ProductoaCategoria();

            pc.setProducto(productoNuevo);
            pc.setCategoria(c);

            categoriasProducto.add(pc);

            productoACategoriaRepository.save(pc);
        }

        productoNuevo.setCategorias(categoriasProducto);
    }

}
