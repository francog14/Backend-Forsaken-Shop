package com.mensajeria.Microservicio_Mensajeria.controller;

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

import com.mensajeria.Microservicio_Mensajeria.models.entities.Mensaje;
import com.mensajeria.Microservicio_Mensajeria.models.request.MensajeActualizarRequest;
import com.mensajeria.Microservicio_Mensajeria.models.request.MensajeRequest;
import com.mensajeria.Microservicio_Mensajeria.service.MensajeService;

@RequestMapping("/mensajes")
@RestController
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("")
    public List<Mensaje> obtenerTodosLosMensajes() {
        return mensajeService.obtenerTodosLosMensajes();
    }

    @GetMapping("/{id_mensaje}")
    public Mensaje obtenerMensajePorId(@PathVariable int id_mensaje) {
        return mensajeService.obtenerMensajePorId(id_mensaje);
    }

    @PostMapping("")
    public Mensaje agregarMensaje(@RequestBody MensajeRequest mensajeNuevo) {
        return mensajeService.agregarMensaje(mensajeNuevo);
    }

    @PutMapping("")
    public Mensaje actualizarMensaje(@RequestBody MensajeActualizarRequest mensajeActualizado) {
        return mensajeService.actualizarMensaje(mensajeActualizado);
    }

    @DeleteMapping("/{id_mensaje}")
    public String eliminarMensaje(@PathVariable int id_mensaje) {
        return mensajeService.eliminarMensaje(id_mensaje);
    }

}
