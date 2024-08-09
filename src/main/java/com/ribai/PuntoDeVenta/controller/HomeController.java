package com.ribai.PuntoDeVenta.controller;

import com.ribai.PuntoDeVenta.logic.Usuario;
import com.ribai.PuntoDeVenta.service.UsuarioService;
import com.ribai.PuntoDeVenta.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    //private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/home")
    public String home(Model model, @RequestParam("token") String token) {
        String username = JwtUtil.extractUsername(token);
        Usuario usuario = usuarioService.findByNombre(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("token", token); // Agrega el token al modelo
        return "home"; // Devuelve la plantilla home.ftl
    }
}