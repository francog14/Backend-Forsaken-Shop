package com.detalle_venta.Microservicio_Detalle_Venta.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DetalleVentaActualizarRequest {

    @NotBlank(message = "El id del detalle de venta no puede estar vacío")
    private int id_detalle_venta;

    @NotBlank(message = "El id de la venta no puede estar vacío")
    private int id_venta;

    @NotBlank(message = "El id de la prenda no puede estar vacío")
    private int id_prenda;

    @NotBlank(message = "La cantidad no puede estar vacía")
    private int cantidad;

    @NotBlank(message = "El precio unitario no puede estar vacío")
    private double precio_unitario;

}
