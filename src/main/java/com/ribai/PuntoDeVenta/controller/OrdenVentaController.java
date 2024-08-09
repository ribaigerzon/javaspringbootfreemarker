package com.ribai.PuntoDeVenta.controller;

import com.ribai.PuntoDeVenta.component.TokenManager;
import com.ribai.PuntoDeVenta.logic.OrdenVenta;
import com.ribai.PuntoDeVenta.logic.Producto;
import com.ribai.PuntoDeVenta.logic.Usuario;
import com.ribai.PuntoDeVenta.service.OrdenVentaService;
import com.ribai.PuntoDeVenta.service.ProductoService;
import com.ribai.PuntoDeVenta.service.UsuarioService;
import com.ribai.PuntoDeVenta.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.sql.Date;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class OrdenVentaController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrdenVentaService ordenVentaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/generar_orden")
    public String mostrarGenerarOrden(Model model) {
        String token = tokenManager.getToken();  // Obtiene el token desde el TokenManager
        String username = jwtUtil.extractUsername(token);  // Extrae el nombre de usuario desde el token

        if (token == null || token.isEmpty()) {
            model.addAttribute("error", "Token no disponible. Por favor, inicie sesión nuevamente.");
            return "login";
        }
        model.addAttribute("token", token);
        model.addAttribute("username", username);
        model.addAttribute("productos", productoService.getAllProductos());
        return "generar_orden";
    }

    @PostMapping("/generar_orden")
    public String generarOrden(
            @RequestParam("token") String token,
            @RequestParam("fechaVenta") String fechaVenta,
            @RequestParam("productIds") List<String> productIds,
            @RequestParam("quantities") List<Integer> quantities,
            Model model) {
        try {
            if (token == null || token.isEmpty()) {
                throw new RuntimeException("Token no disponible. Por favor, inicie sesión nuevamente.");
            }
            System.out.println("Token recibido: " + token); // Mensaje de depuración
            Integer userId = JwtUtil.extractUserId(token);  // Extraer id_usuario del token
            Usuario usuario = usuarioService.getUsuarioById(userId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            for (int i = 0; i < productIds.size(); i++) {
                Integer productId = Integer.parseInt(productIds.get(i));
                Integer quantity = quantities.get(i);
                Producto producto = productoService.getProductoById(productId)
                        .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

                OrdenVenta nuevaOrden = new OrdenVenta();
                nuevaOrden.setFechaVenta(Date.valueOf(fechaVenta));
                nuevaOrden.setImporte(producto.getPrecio() * quantity);
                nuevaOrden.setUsuario(usuario);
                nuevaOrden.setProducto(producto);

                ordenVentaService.saveOrdenVenta(nuevaOrden);
            }

            model.addAttribute("message", "Orden de venta generada exitosamente");
            return "redirect:/ver_ordenes?token=" + token;
        } catch (Exception e) {
            model.addAttribute("error", "Error al generar la orden de venta: " + e.getMessage());
            return "generar_orden";
        }
    }

    @GetMapping("/ver_ordenes")
    public String showVerOrdenesPage(Model model) {
        String token = tokenManager.getToken();  // Obtiene el token desde el TokenManager
        String username = jwtUtil.extractUsername(token);  // Extrae el nombre de usuario desde el token

        model.addAttribute("username", username);

        List<OrdenVenta> ordenes = ordenVentaService.getAllOrdenes();
        model.addAttribute("ordenes", ordenes);
        return "ver_ordenes";
    }
}
