package com.theenglishcut.controller;

import com.theenglishcut.dao.PedidoRepository;
import com.theenglishcut.dao.UsuarioRepository;
import com.theenglishcut.entity.OrderEntity;
import com.theenglishcut.entity.UserEntity;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("Orders")
public class OrdersController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private List<OrderEntity> ordersList;

    private Boolean filtro = true;

    @GetMapping("/listarPedidos")
    public String listar_Pedidos(Model model, HttpSession sesion) {
        UserEntity user = (UserEntity) sesion.getAttribute("user");
        UserEntity userEntity = usuarioRepository.findByNombreUser(user.getNombre());
        List<UserEntity> users = usuarioRepository.findAll();

        if(userEntity.getRol().getNombre().equals("Administrador") && filtro){
            ordersList = pedidoRepository.findAll();
        }else if(userEntity.getRol().getNombre().equals("Usuario") || userEntity.getRol().getNombre().equals("Empleado")){
            ordersList = pedidoRepository.findPedidoByUser(userEntity.getID());
        }

        if(ordersList == null || ordersList.isEmpty()){
            model.addAttribute("mensaje", "No hay pedidos");
        }
        model.addAttribute("orders", ordersList);
        model.addAttribute("users", users);
        model.addAttribute("tipoUsuario", userEntity.getRol().getNombre());
        filtro=true;
        return "/ListadoPedidos";
    }

    @PostMapping("/filtrar")
    public String filtrarPedidos(Model model, @RequestParam("idUsuario")Integer idUsuario){
        if(idUsuario != 0){
            filtro = false;
            ordersList = pedidoRepository.findPedidoByUser(idUsuario);
        }else{
            filtro=true;
        }
        return "redirect:/Orders/listarPedidos";
    }
}
