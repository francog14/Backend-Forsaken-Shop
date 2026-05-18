package com.mensajeria.Microservicio_Mensajeria.models.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MensajeActualizarRequest {

    @NotBlank(message = "El id del mensaje no puede estar vacío")
    private int id_mensaje;

    @NotBlank(message = "El id del usuario no puede estar vacío")
    private int id_usuario;

    @NotBlank(message = "El asunto no puede estar vacío")
    private String asunto;

    @NotBlank(message = "El contenido no puede estar vacío")
    private String contenido;

    @NotBlank(message = "La fecha de envío no puede estar vacía")
    private Date fecha_envio;

}
