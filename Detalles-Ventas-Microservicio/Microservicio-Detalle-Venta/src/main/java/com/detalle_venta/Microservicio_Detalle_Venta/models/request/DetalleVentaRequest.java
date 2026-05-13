package com.detalle_venta.Microservicio_Detalle_Venta.models.request;

import lombok.Data;

@Data
public class DetalleVentaRequest {
    private int id_venta;
    private int id_prenda;
    private int cantidad;
    private double precio_unitario;
}
