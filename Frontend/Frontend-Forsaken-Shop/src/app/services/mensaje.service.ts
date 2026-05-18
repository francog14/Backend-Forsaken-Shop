import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Mensaje, MensajeForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class MensajeService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.mensajes;

  listar() {
    return this.http.get<Mensaje[]>(this.url);
  }

  crear(mensaje: MensajeForm) {
    return this.http.post<Mensaje>(this.url, mensaje);
  }

  actualizar(mensaje: Mensaje) {
    return this.http.put<Mensaje>(this.url, mensaje);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
