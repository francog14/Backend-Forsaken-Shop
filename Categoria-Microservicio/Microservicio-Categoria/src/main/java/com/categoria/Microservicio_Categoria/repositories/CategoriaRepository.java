package com.categoria.Microservicio_Categoria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.categoria.Microservicio_Categoria.models.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
