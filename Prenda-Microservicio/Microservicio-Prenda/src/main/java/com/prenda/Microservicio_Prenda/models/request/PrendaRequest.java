package com.prenda.Microservicio_Prenda.models.request;

import lombok.Data;

@Data
public class PrendaRequest {
    private String nombre_prenda;
    private int precio_prenda;  
    private String talla;
    private String color;   
    private int stock_prenda;
    private int id_categoria;
}
