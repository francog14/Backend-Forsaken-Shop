package com.detalle_venta.Microservicio_Detalle_Venta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.detalle_venta.Microservicio_Detalle_Venta.models.entities.DetalleVenta;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {

}
