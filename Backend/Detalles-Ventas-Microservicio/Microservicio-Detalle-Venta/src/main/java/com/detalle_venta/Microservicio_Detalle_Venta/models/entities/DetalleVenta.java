package com.detalle_venta.Microservicio_Detalle_Venta.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_venta;

    @Column(nullable = false)
    private int id_venta;

    @Column(nullable = false)
    private int id_prenda;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precio_unitario;

}
