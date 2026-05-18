import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Venta, VentaForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class VentaService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.ventas;

  listar() {
    return this.http.get<Venta[]>(this.url);
  }

  crear(venta: VentaForm) {
    return this.http.post<Venta>(this.url, venta);
  }

  actualizar(venta: Venta) {
    return this.http.put<Venta>(this.url, venta);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
