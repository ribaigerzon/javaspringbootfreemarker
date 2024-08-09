package com.ribai.PuntoDeVenta.controller;

import com.ribai.PuntoDeVenta.component.TokenManager;
import com.ribai.PuntoDeVenta.logic.Producto;
import com.ribai.PuntoDeVenta.service.ProductoService;
import com.ribai.PuntoDeVenta.service.UsuarioService;
import com.ribai.PuntoDeVenta.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProductoController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/alta_producto")
    public String showAltaProductoPage(Model model) {
        String token = tokenManager.getToken();  // Obtiene el token desde el TokenManager
        String username = jwtUtil.extractUsername(token);  // Extrae el nombre de usuario desde el token

        model.addAttribute("username", username);
        return "alta_producto";  // Devuelve el nombre de la plantilla alta_producto.ftl
    }

    @PostMapping("/alta_producto")
    public String saveProducto(
            @RequestParam("codigo") String codigo,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("precio") Double precio,
            Model model) {
        try {
            String token = tokenManager.getToken();  // Obtiene el token desde el TokenManager
            String username = jwtUtil.extractUsername(token);

            Producto nuevoProducto = new Producto();
            nuevoProducto.setCodigo(codigo);
            nuevoProducto.setNombre(nombre);
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setPrecio(precio);

            productoService.saveProducto(nuevoProducto);
            model.addAttribute("message", "Producto dado de alta exitosamente");
            return "redirect:/ver_producto";  // Redirige a una página de listado de Productos
        } catch (Exception e) {
            model.addAttribute("error", "Error al dar de alta el producto: " + e.getMessage());
            return "alta_producto";  // Devuelve la plantilla alta_producto.ftl con un mensaje de error
        }
    }

    @GetMapping("/ver_producto")
    public String showVerProductoPage(Model model) {
        String token = tokenManager.getToken();  // Obtiene el token desde el TokenManager
        String username = jwtUtil.extractUsername(token);  // Extrae el nombre de usuario desde el token

        model.addAttribute("username", username);

        List<Producto> productos = productoService.getAllProductos();
        model.addAttribute("productos", productos);
        return "ver_producto";  // Devuelve el nombre de la plantilla ver_productos.ftl
    }
}
