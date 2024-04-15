package com.gt.theenglishcut.controller;

import com.gt.theenglishcut.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class Controlador {
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
    public String verProductos () {
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
    public String crearProducto () {
        return "/CrearProducto";
    }

    @PostMapping("/loginUser")
    public String login(@ModelAttribute Usuario user, HttpSession sesion){
        sesion.setAttribute("user",user.getUser());
        sesion.setAttribute("tipo",user.getTipo());
        return "/home";
    }




}
