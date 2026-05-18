package com.venta.Microservicio_Venta.controller;

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

import com.venta.Microservicio_Venta.models.entities.Venta;
import com.venta.Microservicio_Venta.models.request.VentaActualizarRequest;
import com.venta.Microservicio_Venta.models.request.VentaRequest;
import com.venta.Microservicio_Venta.service.VentaService;

@RequestMapping("/ventas")
@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("")
    public List<Venta> obtenerTodasLasVentas() {
        return ventaService.obtenerTodasLasVentas();
    }

    @GetMapping("/{id_venta}")
    public Venta obtenerVentaPorId(@PathVariable int id_venta) {
        return ventaService.obtenerVentaPorId(id_venta);
    }

    @PostMapping("")
    public Venta agregarVenta(@RequestBody VentaRequest ventaNueva) {
        return ventaService.agregarVenta(ventaNueva);
    }

    @PutMapping("")
    public Venta actualizarVenta(@RequestBody VentaActualizarRequest ventaActualizada) {
        return ventaService.actualizarVenta(ventaActualizada);
    }

    @DeleteMapping("/{id_venta}")
    public String eliminarVenta(@PathVariable int id_venta) {
        return ventaService.eliminarVenta(id_venta);
    }

}
