package com.auth.Microservicio_Auth.models.response;

public record AuthResponse(int id_auth, String nombre, String email, String rol, String token) {
}
