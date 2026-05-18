package com.auth.Microservicio_Auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.auth.Microservicio_Auth.models.entities.AuthUsuario;
import com.auth.Microservicio_Auth.models.request.LoginRequest;
import com.auth.Microservicio_Auth.models.request.RegistroRequest;
import com.auth.Microservicio_Auth.models.response.AuthResponse;
import com.auth.Microservicio_Auth.repositories.AuthUsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private AuthUsuarioRepository authUsuarioRepository;

    public List<AuthUsuario> obtenerUsuariosAuth() {
        return authUsuarioRepository.findAll();
    }

    public AuthResponse login(LoginRequest request) {
        AuthUsuario usuario = authUsuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas."));

        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas.");
        }

        return crearRespuesta(usuario);
    }

    public AuthResponse registrar(RegistroRequest request) {
        if (authUsuarioRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya esta registrado.");
        }

        AuthUsuario usuario = new AuthUsuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setRol(normalizarRol(request.getRol()));
        AuthUsuario guardado = authUsuarioRepository.save(usuario);

        return crearRespuesta(guardado);
    }

    private AuthResponse crearRespuesta(AuthUsuario usuario) {
        String contenidoToken = usuario.getEmail() + ":" + usuario.getRol();
        String token = Base64.getEncoder().encodeToString(contenidoToken.getBytes(StandardCharsets.UTF_8));
        return new AuthResponse(usuario.getId_auth(), usuario.getNombre(), usuario.getEmail(), usuario.getRol(), token);
    }

    private String normalizarRol(String rol) {
        if (rol == null || rol.isBlank()) {
            return "USUARIO";
        }

        return rol.trim().toUpperCase();
    }
}
