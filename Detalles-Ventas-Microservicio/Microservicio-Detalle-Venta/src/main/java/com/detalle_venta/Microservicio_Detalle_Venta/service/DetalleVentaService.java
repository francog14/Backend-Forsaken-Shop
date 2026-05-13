package com.detalle_venta.Microservicio_Detalle_Venta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import com.detalle_venta.Microservicio_Detalle_Venta.models.dto.PrendaDto;
import com.detalle_venta.Microservicio_Detalle_Venta.models.dto.VentaDto;
import com.detalle_venta.Microservicio_Detalle_Venta.models.entities.DetalleVenta;
import com.detalle_venta.Microservicio_Detalle_Venta.models.request.DetalleVentaActualizarRequest;
import com.detalle_venta.Microservicio_Detalle_Venta.models.request.DetalleVentaRequest;
import com.detalle_venta.Microservicio_Detalle_Venta.repositories.DetalleVentaRepository;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private WebClient ventaWebClient;

    @Autowired
    private WebClient prendaWebClient;

    public DetalleVenta obtenerDetalleVentaPorId(int id_detalle_venta) {
        return detalleVentaRepository.findById(id_detalle_venta).orElse(null);
    }

    public List<DetalleVenta> obtenerTodosLosDetallesVenta() {
        return detalleVentaRepository.findAll();
    }

    public DetalleVenta agregarDetalleVenta(DetalleVentaRequest detalleVentaNuevo) {
        VentaDto venta = null;
        PrendaDto prenda = null;

        try {
            venta = ventaWebClient.get()
                    .uri("ventas/{id_venta}", detalleVentaNuevo.getId_venta())
                    .retrieve()
                    .bodyToMono(VentaDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener la venta: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio venta: " + e.getMessage());
        }

        try {
            prenda = prendaWebClient.get()
                    .uri("prendas/{id_prenda}", detalleVentaNuevo.getId_prenda())
                    .retrieve()
                    .bodyToMono(PrendaDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener la prenda: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio prenda: " + e.getMessage());
        }

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setId_venta(detalleVentaNuevo.getId_venta());
        detalleVenta.setId_prenda(detalleVentaNuevo.getId_prenda());
        detalleVenta.setCantidad(detalleVentaNuevo.getCantidad());
        detalleVenta.setPrecio_unitario(detalleVentaNuevo.getPrecio_unitario());
        return detalleVentaRepository.save(detalleVenta);
    }

    public DetalleVenta actualizarDetalleVenta(DetalleVentaActualizarRequest detalleVentaActualizado) {
        DetalleVenta detalleVentaExistente = detalleVentaRepository
                .findById(detalleVentaActualizado.getId_detalle_venta()).orElse(null);
        if (detalleVentaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de venta no encontrado.");
        }

        VentaDto venta = null;
        PrendaDto prenda = null;

        try {
            venta = ventaWebClient.get()
                    .uri("ventas/{id_venta}", detalleVentaActualizado.getId_venta())
                    .retrieve()
                    .bodyToMono(VentaDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener la venta: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio venta: " + e.getMessage());
        }

        try {
            prenda = prendaWebClient.get()
                    .uri("prendas/{id_prenda}", detalleVentaActualizado.getId_prenda())
                    .retrieve()
                    .bodyToMono(PrendaDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode().value()),
                    "Error al obtener la prenda: " + e.getStatusText());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "Error de conexión con el servicio prenda: " + e.getMessage());
        }

        detalleVentaExistente.setId_venta(detalleVentaActualizado.getId_venta());
        detalleVentaExistente.setId_prenda(detalleVentaActualizado.getId_prenda());
        detalleVentaExistente.setCantidad(detalleVentaActualizado.getCantidad());
        detalleVentaExistente.setPrecio_unitario(detalleVentaActualizado.getPrecio_unitario());
        return detalleVentaRepository.save(detalleVentaExistente);
    }

    public String eliminarDetalleVenta(int id_detalle_venta) {
        DetalleVenta detalleVentaExistente = detalleVentaRepository.findById(id_detalle_venta).orElse(null);
        if (detalleVentaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Detalle de venta no encontrado.");
        }
        detalleVentaRepository.deleteById(id_detalle_venta);
        return "Detalle de venta eliminado exitosamente.";
    }

}
