package com.venta.Microservicio_Venta.models.request;

import java.util.Date;

import lombok.Data;

@Data
public class VentaRequest {
    private Date fecha;
    private double total;
    private int id_usuario;
}
