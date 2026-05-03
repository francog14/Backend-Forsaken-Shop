package com.rol.Microservicio_Rol.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rol.Microservicio_Rol.models.entities.Rol;
import com.rol.Microservicio_Rol.repositories.RolRepository;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> obtenerTodosLosRoles() {
        return rolRepository.findAll();
    }

    public Rol obtenerRolPorId(int id) {
        Rol rol = rolRepository.findById(id).orElse(null);

        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado " );
        }
        return rol;
    }


}
