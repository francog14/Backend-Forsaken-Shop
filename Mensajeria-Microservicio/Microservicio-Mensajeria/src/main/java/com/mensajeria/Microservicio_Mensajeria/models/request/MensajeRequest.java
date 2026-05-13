package com.mensajeria.Microservicio_Mensajeria.models.request;

import java.util.Date;

import lombok.Data;

@Data
public class MensajeRequest {
    private int id_usuario;
    private String asunto;
    private String contenido;
    private Date fecha_envio;
}
