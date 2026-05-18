package com.auth.Microservicio_Auth.models.request;

import lombok.Data;

@Data
public class RegistroRequest {
    private String nombre;
    private String email;
    private String password;
    private String rol;
}
