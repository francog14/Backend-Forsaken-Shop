import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { forkJoin } from 'rxjs';
import { DetalleVenta, Prenda, Usuario, Venta } from '../../models/forsaken.models';
import { DetalleVentaService } from '../../services/detalle-venta.service';
import { PedidoService } from '../../services/pedido.service';
import { PrendaService } from '../../services/prenda.service';
import { UsuarioService } from '../../services/usuario.service';
import { VentaService } from '../../services/venta.service';

interface ItemVenta {
  prenda: Prenda;
  cantidad: number;
}

@Component({
  selector: 'app-ventas',
  imports: [CurrencyPipe, DatePipe, FormsModule],
  templateUrl: './ventas.html',
  styleUrl: './ventas.scss',
})
export class VentasComponent implements OnInit {
  private readonly ventaService = inject(VentaService);
  private readonly detalleService = inject(DetalleVentaService);
  private readonly usuarioService = inject(UsuarioService);
  private readonly prendaService = inject(PrendaService);
  private readonly pedidoService = inject(PedidoService);

  readonly ventas = signal<Venta[]>([]);
  readonly detalles = signal<DetalleVenta[]>([]);
  readonly usuarios = signal<Usuario[]>([]);
  readonly prendas = signal<Prenda[]>([]);
  readonly itemsVenta = signal<ItemVenta[]>([]);
  readonly error = signal('');
  readonly exito = signal('');
  readonly registrando = signal(false);

  fecha = new Date().toISOString().slice(0, 10);
  idUsuario = 0;
  idPrenda = 0;
  cantidad = 1;

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.error.set('');

    this.ventaService.listar().subscribe({
      next: (ventas) => this.ventas.set(ventas),
      error: () => this.error.set('No se pudieron cargar las ventas.'),
    });

    this.detalleService.listar().subscribe({
      next: (detalles) => this.detalles.set(detalles),
      error: () => this.error.set('No se pudieron cargar los detalles.'),
    });

    this.usuarioService.listar().subscribe({
      next: (usuarios) => this.usuarios.set(usuarios),
      error: () => this.error.set('No se pudieron cargar los usuarios.'),
    });

    this.prendaService.listar().subscribe({
      next: (prendas) => this.prendas.set(prendas),
      error: () => this.error.set('No se pudieron cargar las prendas.'),
    });
  }

  agregarItem() {
    this.error.set('');
    const prenda = this.prendas().find((item) => item.id_prenda === Number(this.idPrenda));

    if (!prenda) {
      this.error.set('Selecciona una prenda para agregar.');
      return;
    }

    if (this.cantidad < 1) {
      this.error.set('La cantidad debe ser mayor a cero.');
      return;
    }

    if (this.cantidad > prenda.stock_prenda) {
      this.error.set('La cantidad supera el stock disponible.');
      return;
    }

    const actual = this.itemsVenta();
    const existente = actual.find((item) => item.prenda.id_prenda === prenda.id_prenda);

    if (existente) {
      const nuevaCantidad = existente.cantidad + Number(this.cantidad);
      if (nuevaCantidad > prenda.stock_prenda) {
        this.error.set('La cantidad acumulada supera el stock disponible.');
        return;
      }

      this.itemsVenta.set(actual.map((item) =>
        item.prenda.id_prenda === prenda.id_prenda ? { ...item, cantidad: nuevaCantidad } : item
      ));
    } else {
      this.itemsVenta.set([...actual, { prenda, cantidad: Number(this.cantidad) }]);
    }

    this.idPrenda = 0;
    this.cantidad = 1;
  }

  quitarItem(idPrenda: number) {
    this.itemsVenta.set(this.itemsVenta().filter((item) => item.prenda.id_prenda !== idPrenda));
  }

  totalVenta() {
    return this.itemsVenta().reduce((acc, item) => acc + item.prenda.precio_prenda * item.cantidad, 0);
  }

  registrarVenta() {
    this.error.set('');
    this.exito.set('');

    if (!this.idUsuario) {
      this.error.set('Selecciona un cliente para registrar la venta.');
      return;
    }

    if (!this.itemsVenta().length) {
      this.error.set('Agrega al menos una prenda a la venta.');
      return;
    }

    this.registrando.set(true);
    this.ventaService.crear({
      fecha: this.fecha,
      total: this.totalVenta(),
      id_usuario: Number(this.idUsuario),
    }).subscribe({
      next: (venta) => {
        const detalles = this.itemsVenta().map((item) => this.detalleService.crear({
          id_venta: venta.id_venta,
          id_prenda: item.prenda.id_prenda,
          cantidad: item.cantidad,
          precio_unitario: item.prenda.precio_prenda,
        }));

        forkJoin(detalles).subscribe({
          next: () => this.crearPedidoParaVenta(venta),
          error: () => {
            this.error.set('La venta se creo, pero fallo el registro de un detalle.');
            this.registrando.set(false);
          },
        });
      },
      error: () => {
        this.error.set('No se pudo registrar la venta. Revisa el cliente.');
        this.registrando.set(false);
      },
    });
  }

  eliminarVenta(id: number) {
    this.ventaService.eliminar(id).subscribe({
      next: () => this.finalizar('Venta eliminada.'),
      error: () => this.error.set('No se pudo eliminar la venta.'),
    });
  }

  eliminarDetalle(id: number) {
    this.detalleService.eliminar(id).subscribe({
      next: () => this.finalizar('Detalle eliminado.'),
      error: () => this.error.set('No se pudo eliminar el detalle.'),
    });
  }

  nombreUsuario(id: number) {
    return this.usuarios().find((usuario) => usuario.id_usuario === id)?.nombre ?? `Usuario ${id}`;
  }

  runUsuario(id: number) {
    return this.usuarios().find((usuario) => usuario.id_usuario === id)?.run ?? '';
  }

  nombrePrenda(id: number) {
    return this.prendas().find((prenda) => prenda.id_prenda === id)?.nombre_prenda ?? `Prenda ${id}`;
  }

  detallesDeVenta(idVenta: number) {
    return this.detalles().filter((detalle) => detalle.id_venta === idVenta);
  }

  subtotalVenta(idVenta: number) {
    return this.detallesDeVenta(idVenta).reduce((acc, detalle) =>
      acc + detalle.cantidad * detalle.precio_unitario, 0
    );
  }

  limpiar() {
    this.fecha = new Date().toISOString().slice(0, 10);
    this.idUsuario = 0;
    this.idPrenda = 0;
    this.cantidad = 1;
    this.itemsVenta.set([]);
  }

  private finalizar(mensaje: string) {
    this.exito.set(mensaje);
    this.registrando.set(false);
    this.limpiar();
    this.cargarDatos();
  }

  private crearPedidoParaVenta(venta: Venta) {
    this.pedidoService.crear({
      id_usuario: venta.id_usuario,
      id_venta: venta.id_venta,
      rut_cliente: this.runUsuario(venta.id_usuario),
      estado: 'PAGADO',
      fecha_pedido: this.fecha,
    }).subscribe({
      next: () => this.finalizar('Venta, detalles y pedido registrados.'),
      error: () => {
        this.error.set('La venta se registro, pero no se pudo crear el pedido.');
        this.registrando.set(false);
        this.cargarDatos();
      },
    });
  }
}
