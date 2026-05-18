package com.categoria.Microservicio_Categoria.controller;

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

import com.categoria.Microservicio_Categoria.models.entities.Categoria;
import com.categoria.Microservicio_Categoria.service.CategoriaService;


@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("")
    public List<Categoria> obtenerTodasCategorias() {
        return categoriaService.obtenerTodasCategorias();
    }
    
    @GetMapping("/{id_categoria}")
    public Categoria obtenerCategoria(@PathVariable int id_categoria) {
        return categoriaService.obtenerCategoriaPorId(id_categoria);
    }

    @PostMapping("")
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }

    @PutMapping("")
    public Categoria actualizarCategoria(@RequestBody Categoria categoriaActualizada) {
        return categoriaService.actualizarCategoria(categoriaActualizada);
    }

    @DeleteMapping("/{id_categoria}")
    public String eliminarCategoria(@PathVariable int id_categoria) {
        return categoriaService.eliminarCategoria(id_categoria);
    }
}
