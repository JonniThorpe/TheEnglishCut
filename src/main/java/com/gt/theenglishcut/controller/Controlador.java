package com.gt.theenglishcut.controller;

import com.gt.theenglishcut.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Controlador {

    @GetMapping("/")
    public String doInicio () {
        return "/home.jsp";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Usuario user, HttpSession sesion){
        sesion.setAttribute("user",user.getUser());
        sesion.setAttribute("tipo",user.getTipo());
        return "/home.jsp";
    }

    @GetMapping("/listadoProductos")
    public String verProductos () {
        return "/componentes/Navbar.jsp";
    }


}
