import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Prenda, PrendaForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class PrendaService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.prendas;

  listar() {
    return this.http.get<Prenda[]>(this.url);
  }

  crear(prenda: PrendaForm) {
    return this.http.post<Prenda>(this.url, prenda);
  }

  actualizar(prenda: Prenda) {
    return this.http.put<Prenda>(this.url, prenda);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
