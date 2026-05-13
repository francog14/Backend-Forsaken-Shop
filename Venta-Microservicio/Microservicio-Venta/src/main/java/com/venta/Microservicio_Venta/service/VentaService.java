package com.venta.Microservicio_Venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.venta.Microservicio_Venta.models.dto.UsuarioDto;
import com.venta.Microservicio_Venta.models.entities.Venta;
import com.venta.Microservicio_Venta.models.request.VentaActualizarRequest;
import com.venta.Microservicio_Venta.models.request.VentaRequest;
import com.venta.Microservicio_Venta.repositories.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private WebClient usuarioWebClient;

    public Venta obtenerVentaPorId(int id_venta) {
        return ventaRepository.findById(id_venta).orElse(null);
    }

    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.findAll();
    }

    public Venta agregarVenta(VentaRequest ventaNueva) {
        UsuarioDto usuario = null;
        try {
            usuario = usuarioWebClient.get()
                    .uri("usuarios/{id_usuario}", ventaNueva.getId_usuario())
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

        Venta venta = new Venta();
        venta.setFecha(ventaNueva.getFecha());
        venta.setTotal(ventaNueva.getTotal());
        venta.setId_usuario(ventaNueva.getId_usuario());
        return ventaRepository.save(venta);
    }

    public Venta actualizarVenta(VentaActualizarRequest ventaActualizada) {
        Venta ventaExistente = ventaRepository.findById(ventaActualizada.getId_venta()).orElse(null);
        if (ventaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada.");
        }

        UsuarioDto usuario = null;
        try {
            usuario = usuarioWebClient.get()
                    .uri("usuarios/{id_usuario}", ventaActualizada.getId_usuario())
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

        ventaExistente.setFecha(ventaActualizada.getFecha());
        ventaExistente.setTotal(ventaActualizada.getTotal());
        ventaExistente.setId_usuario(ventaActualizada.getId_usuario());
        return ventaRepository.save(ventaExistente);
    }

    public String eliminarVenta(int id_venta) {
        Venta ventaExistente = ventaRepository.findById(id_venta).orElse(null);
        if (ventaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrada.");
        }
        ventaRepository.deleteById(id_venta);
        return "Venta eliminada exitosamente.";
    }

}
