package com.auth.Microservicio_Auth.models.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
