package com.ribai.PuntoDeVenta.repository;

import com.ribai.PuntoDeVenta.logic.OrdenVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenVentaRepository extends JpaRepository<OrdenVenta, Integer> {
}