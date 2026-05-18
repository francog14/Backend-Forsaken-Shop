package com.auth.Microservicio_Auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Microservicio_Auth.models.entities.AuthUsuario;
import com.auth.Microservicio_Auth.models.request.LoginRequest;
import com.auth.Microservicio_Auth.models.request.RegistroRequest;
import com.auth.Microservicio_Auth.models.response.AuthResponse;
import com.auth.Microservicio_Auth.service.AuthService;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/usuarios")
    public List<AuthUsuario> obtenerUsuariosAuth() {
        return authService.obtenerUsuariosAuth();
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/registro")
    public AuthResponse registrar(@RequestBody RegistroRequest request) {
        return authService.registrar(request);
    }
}
