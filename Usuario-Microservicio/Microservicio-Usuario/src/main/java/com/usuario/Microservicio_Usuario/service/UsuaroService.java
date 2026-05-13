package com.usuario.Microservicio_Usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.usuario.Microservicio_Usuario.models.Usuario;
import com.usuario.Microservicio_Usuario.models.dto.RolDto;
import com.usuario.Microservicio_Usuario.models.request.UsuarioActualizarRequest;
import com.usuario.Microservicio_Usuario.models.request.UsuarioRequest;
import com.usuario.Microservicio_Usuario.repositories.UsuarioRepository;

@Service
public class UsuaroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private WebClient rolWebClient;

    public Usuario obtenerUsuarioPorId(int id_usuario) {
        return usuarioRepository.findById(id_usuario).orElse(null);
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario agregarUsuario(UsuarioRequest usuarioNuevo) {
        RolDto rol = null;
        try {
            rol = rolWebClient.get()
                    .uri("roles/{id_rol}", usuarioNuevo.getId_rol())
                    .retrieve()
                    .bodyToMono(RolDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener el rol: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio rol: " + e.getMessage());
        }

        Usuario usuario = new Usuario();
        usuario.setRun(usuarioNuevo.getRun());
        usuario.setNombre(usuarioNuevo.getNombre());
        usuario.setEmail(usuarioNuevo.getEmail());
        usuario.setId_rol(usuarioNuevo.getId_rol());
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(UsuarioActualizarRequest usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioActualizado.getId_usuario()).orElse(null);
        if (usuarioExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado.");
        }

        RolDto rol = null;
        try {
            rol = rolWebClient.get()
                    .uri("roles/{id_rol}", usuarioActualizado.getId_rol())
                    .retrieve()
                    .bodyToMono(RolDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener el rol: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio rol: " + e.getMessage());
        }

        usuarioExistente.setRun(usuarioActualizado.getRun());
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setId_rol(usuarioActualizado.getId_rol());
        return usuarioRepository.save(usuarioExistente);
    }

    public String eliminarUsuario(int id_usuario) {
        Usuario usuarioExistente = usuarioRepository.findById(id_usuario).orElse(null);
        if (usuarioExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado.");
        }
        usuarioRepository.deleteById(id_usuario);
        return "Usuario eliminado exitosamente.";
    }

}

