package com.auth.Microservicio_Auth.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.Microservicio_Auth.models.entities.AuthUsuario;

public interface AuthUsuarioRepository extends JpaRepository<AuthUsuario, Integer> {
    Optional<AuthUsuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
