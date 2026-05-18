package com.mensajeria.Microservicio_Mensajeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.mensajeria.Microservicio_Mensajeria.models.dto.UsuarioDto;
import com.mensajeria.Microservicio_Mensajeria.models.entities.Mensaje;
import com.mensajeria.Microservicio_Mensajeria.models.request.MensajeActualizarRequest;
import com.mensajeria.Microservicio_Mensajeria.models.request.MensajeRequest;
import com.mensajeria.Microservicio_Mensajeria.repositories.MensajeRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private WebClient usuarioWebClient;

    public Mensaje obtenerMensajePorId(int id_mensaje) {
        return mensajeRepository.findById(id_mensaje).orElse(null);
    }

    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeRepository.findAll();
    }

    public Mensaje agregarMensaje(MensajeRequest mensajeNuevo) {
        UsuarioDto usuario = null;
        try {
            usuario = usuarioWebClient.get()
                    .uri("usuarios/{id_usuario}", mensajeNuevo.getId_usuario())
                    .retrieve()
                    .bodyToMono(UsuarioDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener el usuario: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio usuario: " + e.getMessage());
        }

        Mensaje mensaje = new Mensaje();
        mensaje.setId_usuario(mensajeNuevo.getId_usuario());
        mensaje.setAsunto(mensajeNuevo.getAsunto());
        mensaje.setContenido(mensajeNuevo.getContenido());
        mensaje.setFecha_envio(mensajeNuevo.getFecha_envio());
        return mensajeRepository.save(mensaje);
    }

    public Mensaje actualizarMensaje(MensajeActualizarRequest mensajeActualizado) {
        Mensaje mensajeExistente = mensajeRepository.findById(mensajeActualizado.getId_mensaje()).orElse(null);
        if (mensajeExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensaje no encontrado.");
        }

        UsuarioDto usuario = null;
        try {
            usuario = usuarioWebClient.get()
                    .uri("usuarios/{id_usuario}", mensajeActualizado.getId_usuario())
                    .retrieve()
                    .bodyToMono(UsuarioDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener el usuario: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio usuario: " + e.getMessage());
        }

        mensajeExistente.setId_usuario(mensajeActualizado.getId_usuario());
        mensajeExistente.setAsunto(mensajeActualizado.getAsunto());
        mensajeExistente.setContenido(mensajeActualizado.getContenido());
        mensajeExistente.setFecha_envio(mensajeActualizado.getFecha_envio());
        return mensajeRepository.save(mensajeExistente);
    }

    public String eliminarMensaje(int id_mensaje) {
        Mensaje mensajeExistente = mensajeRepository.findById(id_mensaje).orElse(null);
        if (mensajeExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensaje no encontrado.");
        }
        mensajeRepository.deleteById(id_mensaje);
        return "Mensaje eliminado exitosamente.";
    }

}
