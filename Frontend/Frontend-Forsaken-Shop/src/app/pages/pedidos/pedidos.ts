import { CurrencyPipe, DatePipe } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Pedido, Usuario, Venta } from '../../models/forsaken.models';
import { PedidoService } from '../../services/pedido.service';
import { UsuarioService } from '../../services/usuario.service';
import { VentaService } from '../../services/venta.service';

@Component({
  selector: 'app-pedidos',
  imports: [CurrencyPipe, DatePipe, FormsModule],
  templateUrl: './pedidos.html',
  styleUrl: './pedidos.scss',
})
export class PedidosComponent implements OnInit {
  private readonly pedidoService = inject(PedidoService);
  private readonly usuarioService = inject(UsuarioService);
  private readonly ventaService = inject(VentaService);

  readonly pedidos = signal<Pedido[]>([]);
  readonly usuarios = signal<Usuario[]>([]);
  readonly ventas = signal<Venta[]>([]);
  readonly error = signal('');
  readonly exito = signal('');

  rutBusqueda = '';
  readonly estados = ['PENDIENTE', 'PAGADO', 'EN_PREPARACION', 'DESPACHADO', 'ENTREGADO', 'CANCELADO'];

  ngOnInit() {
    this.cargarDatos();
  }

  cargarDatos() {
    this.error.set('');
    this.exito.set('');

    this.pedidoService.listar().subscribe({
      next: (pedidos) => this.pedidos.set(pedidos),
      error: () => this.error.set('No se pudieron cargar los pedidos.'),
    });

    this.usuarioService.listar().subscribe({
      next: (usuarios) => this.usuarios.set(usuarios),
      error: () => this.error.set('No se pudieron cargar los usuarios.'),
    });

    this.ventaService.listar().subscribe({
      next: (ventas) => this.ventas.set(ventas),
      error: () => this.error.set('No se pudieron cargar las ventas.'),
    });
  }

  buscarPorRut() {
    this.error.set('');
    this.exito.set('');

    if (!this.rutBusqueda.trim()) {
      this.cargarDatos();
      return;
    }

    this.pedidoService.buscarPorRut(this.rutBusqueda.trim()).subscribe({
      next: (pedidos) => this.pedidos.set(pedidos),
      error: () => this.error.set('No se pudieron buscar pedidos por RUT.'),
    });
  }

  actualizarEstado(pedido: Pedido, estado: string) {
    this.pedidoService.actualizarEstado(pedido.id_pedido, estado).subscribe({
      next: () => {
        this.exito.set('Estado de pedido actualizado.');
        this.cargarDatos();
      },
      error: () => this.error.set('No se pudo actualizar el estado.'),
    });
  }

  nombreUsuario(idUsuario: number) {
    return this.usuarios().find((usuario) => usuario.id_usuario === idUsuario)?.nombre ?? `Usuario ${idUsuario}`;
  }

  totalVenta(idVenta: number) {
    return this.ventas().find((venta) => venta.id_venta === idVenta)?.total ?? 0;
  }
}
