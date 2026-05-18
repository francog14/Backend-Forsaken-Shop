package com.prenda.Microservicio_Prenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.prenda.Microservicio_Prenda.models.dto.CategoriaDto;
import com.prenda.Microservicio_Prenda.models.entities.Prenda;
import com.prenda.Microservicio_Prenda.models.request.PrendaActualizarRequest;
import com.prenda.Microservicio_Prenda.models.request.PrendaRequest;
import com.prenda.Microservicio_Prenda.repositories.PrendaRepository;

@Service
public class PrendaService {

    @Autowired
    private PrendaRepository prendaRepository;

    @Autowired
    private WebClient webClient;

    public Prenda obtenePrendaPorId(int id_prenda) {
        return prendaRepository.findById(id_prenda).orElse(null);
    }

    public List<Prenda> obtenerTodasLasPrendas() {
        return prendaRepository.findAll();
    }

    public Prenda agregarPrenda(PrendaRequest prendaNueva){
        CategoriaDto categoria = null;
        try {
            categoria = webClient.get()
                    .uri("categorias/{id_categoria}", prendaNueva.getId_categoria())
                    .retrieve()
                    .bodyToMono(CategoriaDto.class)
                    .block();
        } catch (WebClientResponseException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
            "Error al obtener la categoria: " + e.getStatusText());
        } catch (Exception e){
            throw new ResponseStatusException(
             HttpStatus.SERVICE_UNAVAILABLE,
                "Error de conexión con el servicio categoria: " + e.getMessage());
        }

        Prenda prenda = new Prenda();
        prenda.setNombre_prenda(prendaNueva.getNombre_prenda());
        prenda.setPrecio_prenda(prendaNueva.getPrecio_prenda());
        prenda.setTalla(prendaNueva.getTalla());
        prenda.setColor(prendaNueva.getColor());
        prenda.setStock_prenda(prendaNueva.getStock_prenda());
        prenda.setId_categoria(prendaNueva.getId_categoria());
        return prendaRepository.save(prenda);
    }

    
    public Prenda actualizarPrenda(PrendaActualizarRequest prendaActualizada) {
        Prenda prendaExistente = prendaRepository.findById(prendaActualizada.getId_prenda()).orElse(null);
        if (prendaExistente == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Prenda no encontrada. ");
        }
        prendaExistente.setNombre_prenda(prendaActualizada.getNombre_prenda());
        prendaExistente.setPrecio_prenda(prendaActualizada.getPrecio_prenda());
        prendaExistente.setTalla(prendaActualizada.getTalla());
        prendaExistente.setColor(prendaActualizada.getColor());
        prendaExistente.setStock_prenda(prendaActualizada.getStock_prenda());
        prendaExistente.setId_categoria(prendaActualizada.getId_categoria());
        return prendaRepository.save(prendaExistente);
    }

    public String eliminarPrenda(int id_prenda) {
        Prenda prendaExistente = prendaRepository.findById(id_prenda).orElse(null);
        if (prendaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prenda no encontrada. ");
        }
        prendaRepository.deleteById(id_prenda);
        return "Prenda eliminada exitosamente.";
    }
    
}
