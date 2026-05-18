import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Usuario, UsuarioForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class UsuarioService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.usuarios;

  listar() {
    return this.http.get<Usuario[]>(this.url);
  }

  crear(usuario: UsuarioForm) {
    return this.http.post<Usuario>(this.url, usuario);
  }

  actualizar(usuario: Usuario) {
    return this.http.put<Usuario>(this.url, usuario);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
