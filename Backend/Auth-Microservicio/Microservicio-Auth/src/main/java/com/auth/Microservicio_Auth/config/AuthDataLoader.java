package com.auth.Microservicio_Auth.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.auth.Microservicio_Auth.models.entities.AuthUsuario;
import com.auth.Microservicio_Auth.repositories.AuthUsuarioRepository;

@Configuration
public class AuthDataLoader {

    @Bean
    CommandLineRunner cargarUsuariosDemo(AuthUsuarioRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }

            crear(repository, "Administrador", "admin@forsaken.cl", "admin123", "ADMIN");
            crear(repository, "Vendedor", "vendedor@forsaken.cl", "vendedor123", "VENDEDOR");
            crear(repository, "Bodeguero", "bodega@forsaken.cl", "bodega123", "BODEGUERO");
            crear(repository, "Cliente", "cliente@forsaken.cl", "cliente123", "USUARIO");
        };
    }

    private void crear(AuthUsuarioRepository repository, String nombre, String email, String password, String rol) {
        AuthUsuario usuario = new AuthUsuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol(rol);
        repository.save(usuario);
    }
}
