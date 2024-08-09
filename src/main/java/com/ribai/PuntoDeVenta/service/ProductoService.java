package com.ribai.PuntoDeVenta.service;

import com.ribai.PuntoDeVenta.logic.Producto;
import com.ribai.PuntoDeVenta.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}
