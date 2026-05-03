package com.rol.Microservicio_Rol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rol.Microservicio_Rol.models.entities.Rol;
import com.rol.Microservicio_Rol.service.RolService;


@RestController
@RequestMapping("roles")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping("")
    public List<Rol> obtenerTodosLosRoles() {
        return rolService.obtenerTodosLosRoles();
    }
    
    @GetMapping("/{id_rol}")
    public Rol obtenerRolPorId(@PathVariable int id_rol) {
        return rolService.obtenerRolPorId(id_rol);
    }
}