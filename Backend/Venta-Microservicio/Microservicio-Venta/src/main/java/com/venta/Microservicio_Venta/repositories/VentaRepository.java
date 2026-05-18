package com.venta.Microservicio_Venta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venta.Microservicio_Venta.models.entities.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
