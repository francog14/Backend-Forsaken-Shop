package com.usuario.Microservicio_Usuario.models.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String run;
    private String nombre;
    private String email;
    private int id_rol;
}
