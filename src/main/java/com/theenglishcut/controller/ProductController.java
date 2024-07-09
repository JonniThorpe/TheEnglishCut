package com.theenglishcut.controller;

import com.theenglishcut.dao.*;
import com.theenglishcut.entity.*;
import com.theenglishcut.ui.producto;
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

        UserEntity userEntity = usuarioRepository.findByNombreUser(user);

        if(user == null){
            modelo.addAttribute("error","no existe ese usuario, prueba a registrarte antes de hacer login");
            return "/error?tipo=login";
        }else if(password == null || !password.equals(userEntity.getPassword())){
            modelo.addAttribute("error","la contraseña no coincide, prueba a prueba otra contraseña para hacer login hacer login");
            return "/error?tipo=login";
        }

        sesion.setAttribute("user", userEntity);

        categoriaGlobal = "TODO";
        //TODO el carrito debe estar vacio cuanddo un nuevo usuario se logee
        //productosCarrito.clear();

        return "redirect:/";
    }

    @GetMapping("/register")
    public String doRegister (HttpSession sesion, Model modelo  ) {

        String user = (String) sesion.getAttribute("user");
        UserEntity userEntity = usuarioRepository.findByNombreUser(user);

        if(userEntity != null && userEntity.getRol().getNombre().equals("Administrador")){
            modelo.addAttribute("roles", rolRepository.findAll());
        }
        return "/register";
    }

    @PostMapping("/registerUser")
    public String register(HttpSession sesion, Model modelo, @RequestParam(value = "nombre",required = false)String user, @RequestParam(value = "password",required = false)String password, @RequestParam(value="rolId")Integer rolId){

        UserEntity userEntity = usuarioRepository.findByNombreUser(user);

        if(userEntity != null){
            modelo.addAttribute("error","ya existe ese usuario, prueba a registrarte con otro nombre de usuario");
            return "redirect:/error?tipo=register";
        }

        userEntity = new UserEntity();

        userEntity.setNombre(user);
        userEntity.setPassword(password);

        userEntity.setRol(rolRepository.findById(rolId).get());
        usuarioRepository.save(userEntity);

        //designamoos como el nuevo usuario en uso para la web
        sesion.setAttribute("user", userEntity);

        categoriaGlobal = "TODO";
        //TODO el carrito debe estar vacio cuanddo un nuevo usuario se registre
        //productosCarrito.clear();

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout (HttpSession sesion) {

        sesion.setAttribute("user",null);

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
        List<CategoryEntity> categoryEntityList = categoriaRepository.findAll();
        List<String> lista = new ArrayList<>();

        for(CategoryEntity categoryEntity : categoryEntityList){
            lista.add(categoryEntity.getNombre());
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

        List<ProductEntity> productEntityList = buscarProductosFiltro(categoria);
        if(productEntityList.isEmpty()){
            model.addAttribute("mensaje", "No hay productos");
        }

        model.addAttribute("products", productEntityList);
        return "/listadoProductos";
    }

    private List<ProductEntity> buscarProductosFiltro(String categoria) {

        List<ProductEntity> aux = productoRepository.findAll();
        List<ProductEntity> productEntityList = new ArrayList<>();
        categoriaGlobal= categoria;
        boolean anadir = false;

        if(categoria.equals("TODO")){
            productEntityList = aux;
        }else{

            for(ProductEntity p:aux){
                for(ProductToCategoryEntity c:p.getCategorias()){
                    if(c.getCategoria().getNombre().equals(categoria)){
                        anadir = true;
                        break;
                    }
                }
                if(anadir){
                    productEntityList.add(p);
                    anadir = false;
                }
            }
        }

        return productEntityList;
    }

    @GetMapping("/Navbar")
    public String verCategorias (Model model) {

        List<CategoryEntity> categoryEntityList = categoriaRepository.findAll();
        List<String> lista = new ArrayList<>();

        for(CategoryEntity categoryEntity : categoryEntityList){
            lista.add(categoryEntity.getNombre());
        }


        model.addAttribute("listaCategoriasNombres", lista);
        return "/Navbar";
    }

    @GetMapping("/Detail")
    public String product_Detail(@RequestParam("id")Integer id, Model modelo){
        ProductEntity product = productoRepository.findById(id).get();

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
        ProductEntity productEntity = productoRepository.findById(id).get();

        productoModificado.setIdProducto(id);
        productoModificado.setNombre(productEntity.getNombre());
        productoModificado.setDescripcion(productEntity.getDescripcion());
        productoModificado.setPrecio(productEntity.getPrecio());
        productoModificado.setImagen(productEntity.getImagen());
        productoModificado.setCantidad(productEntity.getInventario().getCantidad());
        productoModificado.setCategorias(productEntity.getCategorias().stream()
                .map(ProductToCategoryEntity::getCategoria)
                .map(CategoryEntity::getID)
                .collect(Collectors.toList()));

        modelo.addAttribute("categorias",categoriaRepository.findAll());
        modelo.addAttribute("producto",  productoModificado);
        return "/CrearProducto";
    }

    @GetMapping("/eliminarProducto")
    public String eliminarProducto (Model model, @RequestParam("id")int id) {
        ProductEntity productEntity = productoRepository.findById(id).orElse(null);
        if(productEntity == null){
            System.out.println("ningun producto eliminado, no deberia ser null el producto");
            return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
        }
        productoACategoriaRepository.deleteByProductID(id);
        productoRepository.deleteById(id);
        inventarioRepository.deleteById(productEntity.getInventario().getID());
        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
    }

    @PostMapping("/guardarProducto")
    public String guardarProducto (Model modelo, @ModelAttribute("producto")producto producto) {
        ProductEntity productEntityNuevo = new ProductEntity();
        StockEntity stockEntity = new StockEntity();

        if(producto.getIdProducto() == null) {
            stockEntity.setCantidad(producto.getCantidad());
            inventarioRepository.save(stockEntity);
            productEntityNuevo.setInventario(stockEntity);

            GuardarObjetoProducto(producto, productEntityNuevo);
        }else{
            productEntityNuevo = productoRepository.findById(producto.getIdProducto()).orElse(null);

            stockEntity = productEntityNuevo.getInventario();
            stockEntity.setCantidad(producto.getCantidad());
            inventarioRepository.save(stockEntity);

            productoACategoriaRepository.deleteByProductID(producto.getIdProducto());
            productEntityNuevo.getCategorias().clear();

            GuardarObjetoProducto(producto, productEntityNuevo);

        }
        return "redirect:/listadoProductos?Categoria="+categoriaGlobal;
    }

    private void GuardarObjetoProducto(@ModelAttribute("producto") producto producto, ProductEntity productEntityNuevo) {

        productEntityNuevo.setNombre(producto.getNombre());
        productEntityNuevo.setDescripcion(producto.getDescripcion());
        productEntityNuevo.setImagen(producto.getImagen());
        productEntityNuevo.setPrecio(producto.getPrecio());
        productoRepository.save(productEntityNuevo);

        List<ProductToCategoryEntity> categoriasProducto = new ArrayList<>();

        for (CategoryEntity c : categoriaRepository.findAllById(producto.getCategorias())) {
            ProductToCategoryEntity pc = new ProductToCategoryEntity();

            pc.setProducto(productEntityNuevo);
            pc.setCategoria(c);

            categoriasProducto.add(pc);

            productoACategoriaRepository.save(pc);
        }

        productEntityNuevo.setCategorias(categoriasProducto);
    }

}
