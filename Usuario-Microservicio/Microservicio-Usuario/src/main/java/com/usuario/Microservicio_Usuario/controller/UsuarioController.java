package com.usuario.Microservicio_Usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.Microservicio_Usuario.models.Usuario;
import com.usuario.Microservicio_Usuario.models.request.UsuarioActualizarRequest;
import com.usuario.Microservicio_Usuario.models.request.UsuarioRequest;
import com.usuario.Microservicio_Usuario.service.UsuaroService;

@RequestMapping("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    private UsuaroService usuarioService;

    @GetMapping("")
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id_usuario}")
    public Usuario obtenerUsuarioPorId(@PathVariable int id_usuario) {
        return usuarioService.obtenerUsuarioPorId(id_usuario);
    }

    @PostMapping("")
    public Usuario agregarUsuario(@RequestBody UsuarioRequest usuarioNuevo) {
        return usuarioService.agregarUsuario(usuarioNuevo);
    }

    @PutMapping("")
    public Usuario actualizarUsuario(@RequestBody UsuarioActualizarRequest usuarioActualizado) {
        return usuarioService.actualizarUsuario(usuarioActualizado);
    }

    @DeleteMapping("/{id_usuario}")
    public String eliminarUsuario(@PathVariable int id_usuario) {
        return usuarioService.eliminarUsuario(id_usuario);
    }

}
