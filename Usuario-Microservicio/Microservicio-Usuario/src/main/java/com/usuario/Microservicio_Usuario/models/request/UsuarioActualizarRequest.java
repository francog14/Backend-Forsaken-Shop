package com.usuario.Microservicio_Usuario.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioActualizarRequest {

    @NotBlank(message = "El id del usuario no puede estar vacío")
    private int id_usuario;

    @NotBlank(message = "El run no puede estar vacío")
    private String run;

    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    @NotBlank(message = "El id del rol no puede estar vacío")
    private int id_rol;

}
