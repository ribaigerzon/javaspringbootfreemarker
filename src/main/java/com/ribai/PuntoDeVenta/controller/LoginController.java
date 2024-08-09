package com.ribai.PuntoDeVenta.controller;

import com.ribai.PuntoDeVenta.component.TokenManager;
import com.ribai.PuntoDeVenta.logic.Usuario;
import com.ribai.PuntoDeVenta.service.UsuarioService;
import com.ribai.PuntoDeVenta.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenManager tokenManager;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Devuelve el nombre de la plantilla login.ftl
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("nombre") String nombre,
            @RequestParam("contrasenia") String contrasenia,
            Model model) {
        try {
            Usuario usuario = usuarioService.authenticate(nombre, contrasenia)
                    .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));
            String token = JwtUtil.generateToken(nombre, usuario.getId_usuario());  // Usa el ID del usuario en lugar de la contraseña
            model.addAttribute("token", token);
            model.addAttribute("usuario", usuario);
            tokenManager.setToken(token);
            return "redirect:/homePage?token=" + token;  // Redirige a la página principal con el token
        } catch (Exception e) {
            model.addAttribute("error", "Error de autenticación: " + e.getMessage());
            return "login";  // Devuelve la plantilla login.ftl con un mensaje de error
        }
    }

    @GetMapping("/homePage")
    public String home(Model model, @RequestParam("token") String token) {
        String username = JwtUtil.extractUsername(token);
        Usuario usuario = usuarioService.findByNombre(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "home";  // Devuelve la plantilla home.ftl
    }

    @GetMapping("/ordenventa")
    public String ordenventa(Model model, @RequestParam("token") String token) {
        String username = JwtUtil.extractUsername(token);
        Usuario usuario = usuarioService.findByNombre(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "generar_orden";  // Devuelve la plantilla generar_orden.ftl
    }

    @GetMapping("/logout")
    public String logout() {

        return "redirect:/login";
    }
}