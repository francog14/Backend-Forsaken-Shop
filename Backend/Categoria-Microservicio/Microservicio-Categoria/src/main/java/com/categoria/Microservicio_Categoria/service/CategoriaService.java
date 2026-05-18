package com.categoria.Microservicio_Categoria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.categoria.Microservicio_Categoria.models.entities.Categoria;
import com.categoria.Microservicio_Categoria.repositories.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> obtenerTodasCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaPorId(int id_categoria) {
        Categoria categoria = categoriaRepository.findById(id_categoria).orElse(null);

        if (categoria == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria no encontrada " + id_categoria);
        }
        return categoria;
    }

    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Categoria categoriaActualizada) {
        Categoria categoriaExistente = categoriaRepository.findById(categoriaActualizada.getId_categoria()).orElse(null);
        if (categoriaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria no encontrada.");
        }
        
        categoriaExistente.setNombre_categoria(categoriaActualizada.getNombre_categoria());
        return categoriaRepository.save(categoriaExistente);
    }

    public String eliminarCategoria(int id_categoria) {
        Categoria categoriaExistente = categoriaRepository.findById(id_categoria).orElse(null);
        if (categoriaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria no encontrada.");
        }
        categoriaRepository.deleteById(id_categoria);
        return "Categoria eliminada exitosamente.";
    }
}
