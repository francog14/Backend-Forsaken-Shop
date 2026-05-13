package com.detalle_venta.Microservicio_Detalle_Venta.controller;

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

import com.detalle_venta.Microservicio_Detalle_Venta.models.entities.DetalleVenta;
import com.detalle_venta.Microservicio_Detalle_Venta.models.request.DetalleVentaActualizarRequest;
import com.detalle_venta.Microservicio_Detalle_Venta.models.request.DetalleVentaRequest;
import com.detalle_venta.Microservicio_Detalle_Venta.service.DetalleVentaService;

@RequestMapping("/detalles-ventas")
@RestController
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping("")
    public List<DetalleVenta> obtenerTodosLosDetallesVenta() {
        return detalleVentaService.obtenerTodosLosDetallesVenta();
    }

    @GetMapping("/{id_detalle_venta}")
    public DetalleVenta obtenerDetalleVentaPorId(@PathVariable int id_detalle_venta) {
        return detalleVentaService.obtenerDetalleVentaPorId(id_detalle_venta);
    }

    @PostMapping("")
    public DetalleVenta agregarDetalleVenta(@RequestBody DetalleVentaRequest detalleVentaNuevo) {
        return detalleVentaService.agregarDetalleVenta(detalleVentaNuevo);
    }

    @PutMapping("")
    public DetalleVenta actualizarDetalleVenta(@RequestBody DetalleVentaActualizarRequest detalleVentaActualizado) {
        return detalleVentaService.actualizarDetalleVenta(detalleVentaActualizado);
    }

    @DeleteMapping("/{id_detalle_venta}")
    public String eliminarDetalleVenta(@PathVariable int id_detalle_venta) {
        return detalleVentaService.eliminarDetalleVenta(id_detalle_venta);
    }

}
