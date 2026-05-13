package com.venta.Microservicio_Venta.models.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VentaActualizarRequest {

    @NotBlank(message = "El id de la venta no puede estar vacío")
    private int id_venta;

    @NotBlank(message = "La fecha no puede estar vacía")
    private Date fecha;

    @NotBlank(message = "El total no puede estar vacío")
    private double total;

    @NotBlank(message = "El id del usuario no puede estar vacío")
    private int id_usuario;

}
