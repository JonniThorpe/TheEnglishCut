package com.gt.theenglishcut.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

    @GetMapping("/")
    public String doInicio () {
        return "/home.jsp";
    }

    @GetMapping("/login")
    public String login(){return "/login.jsp";}

    @GetMapping("/listadoProductos")
    public String verProductos () {
        return "/componentes/Navbar.jsp";
    }


}
