package com.rol.Microservicio_Rol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rol.Microservicio_Rol.models.entities.Rol;

public interface RolRepository extends  JpaRepository<Rol, Integer> {
    
}
