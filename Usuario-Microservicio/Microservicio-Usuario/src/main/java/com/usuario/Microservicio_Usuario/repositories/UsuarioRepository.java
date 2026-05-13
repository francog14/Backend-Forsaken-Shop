package com.usuario.Microservicio_Usuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuario.Microservicio_Usuario.models.Usuario;

public  interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
