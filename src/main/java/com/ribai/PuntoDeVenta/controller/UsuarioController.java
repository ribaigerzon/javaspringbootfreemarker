package com.ribai.PuntoDeVenta.controller;

import com.ribai.PuntoDeVenta.component.TokenManager;
import com.ribai.PuntoDeVenta.logic.Usuario;
import com.ribai.PuntoDeVenta.service.UsuarioService;
import com.ribai.PuntoDeVenta.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.ui.Model;

@Controller
public class UsuarioController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/alta_usuario")
    public String showAltaUsuarioPage(Model model) {
        String token = tokenManager.getToken();  // Obtiene el token desde el TokenManager
        String username = jwtUtil.extractUsername(token);  // Extrae el nombre de usuario desde el token

        model.addAttribute("username", username);
        return "alta_usuario";  // Devuelve el nombre de la plantilla alta_usuario.ftl
    }

    @PostMapping("/alta_usuario")
    public String saveUsuario(
            @RequestParam("nombre") String nombre,
            @RequestParam("contrasenia") String contrasenia,
            @RequestParam("rol") String rol,
            Model model) {
        try {
            String token = tokenManager.getToken();
            String username = jwtUtil.extractUsername(token);

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(nombre);
            nuevoUsuario.setContrasenia(contrasenia);
            nuevoUsuario.setRol(rol);
            usuarioService.saveUsuario(nuevoUsuario);
            model.addAttribute("message", "Usuario dado de alta exitosamente");
            return "redirect:/ver_usuario";  // Redirige a una página de listado de usuarios
        } catch (Exception e) {
            model.addAttribute("error", "Error al dar de alta el usuario: " + e.getMessage());
            return "alta_usuario";  // Devuelve la plantilla alta_usuario.ftl con un mensaje de error
        }
    }

    @GetMapping("/ver_usuario")
    public String showVerUsuarioPage(Model model) {
        String token = tokenManager.getToken();  // Obtén el token desde el TokenManager
        String username = jwtUtil.extractUsername(token);  // Extrae el nombre de usuario desde el token

        model.addAttribute("username", username);

        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "ver_usuario";  // Devuelve el nombre de la plantilla ver_usuario.ftl
    }
}
