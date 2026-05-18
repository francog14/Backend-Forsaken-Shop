import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Pedido, PedidoForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class PedidoService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.pedidos;

  listar() {
    return this.http.get<Pedido[]>(this.url);
  }

  listarPorUsuario(idUsuario: number) {
    return this.http.get<Pedido[]>(`${this.url}/usuario/${idUsuario}`);
  }

  buscarPorRut(rut: string) {
    return this.http.get<Pedido[]>(`${this.url}/rut/${rut}`);
  }

  crear(pedido: PedidoForm) {
    return this.http.post<Pedido>(this.url, pedido);
  }

  actualizarEstado(idPedido: number, estado: string) {
    return this.http.put<Pedido>(`${this.url}/${idPedido}/estado`, { estado });
  }
}
