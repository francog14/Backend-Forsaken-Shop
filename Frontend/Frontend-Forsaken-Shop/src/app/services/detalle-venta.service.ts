import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { DetalleVenta, DetalleVentaForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class DetalleVentaService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.detallesVentas;

  listar() {
    return this.http.get<DetalleVenta[]>(this.url);
  }

  crear(detalle: DetalleVentaForm) {
    return this.http.post<DetalleVenta>(this.url, detalle);
  }

  actualizar(detalle: DetalleVenta) {
    return this.http.put<DetalleVenta>(this.url, detalle);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
