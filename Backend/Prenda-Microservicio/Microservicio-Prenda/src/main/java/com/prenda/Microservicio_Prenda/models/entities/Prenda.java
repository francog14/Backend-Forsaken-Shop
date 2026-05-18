package com.prenda.Microservicio_Prenda.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "prendas")
public class Prenda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_prenda;

    @Column(nullable=false)
    private String nombre_prenda;

    @Column(nullable=false)
    private int precio_prenda;

    @Column(nullable=false)
    private String talla;

    @Column(nullable=false)
    private String color;   

    @Column(nullable=false)
    private int stock_prenda;

    @Column(nullable=false)
    private int id_categoria;



}
