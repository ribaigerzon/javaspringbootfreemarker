package com.ribai.PuntoDeVenta.service;

import com.ribai.PuntoDeVenta.logic.OrdenVenta;
import com.ribai.PuntoDeVenta.repository.OrdenVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdenVentaService {

    @Autowired
    private OrdenVentaRepository ordenVentaRepository;

    public void saveOrdenVenta(OrdenVenta ordenVenta) {
        ordenVentaRepository.save(ordenVenta);
    }

    public void saveAllOrdenes(List<OrdenVenta> ordenes) {
        ordenVentaRepository.saveAll(ordenes);
    }

    public List<OrdenVenta> getAllOrdenes() {
        return ordenVentaRepository.findAll();
    }
}