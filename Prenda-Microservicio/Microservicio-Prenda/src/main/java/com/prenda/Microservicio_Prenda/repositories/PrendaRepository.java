package com.prenda.Microservicio_Prenda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prenda.Microservicio_Prenda.models.entities.Prenda;

public interface  PrendaRepository extends JpaRepository<Prenda, Integer> {
    
}
