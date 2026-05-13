package com.prenda.Microservicio_Prenda.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PrendaActualizarRequest {
    
    @NotBlank(message = "El id de la prenda no puede estar vacío")
    private int id_prenda;

    @NotBlank(message = "El nombre de la prenda no puede estar vacío")
    private String nombre_prenda;

    @NotBlank(message = "El precio de la prenda no puede estar vacío")
    private int precio_prenda;

    @NotBlank(message = "La talla de la prenda no puede estar vacía")
    private String talla;

    @NotBlank(message = "El color de la prenda no puede estar vacío")
    private String color;

    @NotBlank(message = "El stock de la prenda no puede estar vacío")
    private int stock_prenda;

    @NotBlank(message = "El id de la categoría no puede estar vacío")
    private int id_categoria;

}
