import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Rol, RolForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class RolService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.roles;

  listar() {
    return this.http.get<Rol[]>(this.url);
  }

  crear(rol: RolForm) {
    return this.http.post<Rol>(this.url, rol);
  }

  actualizar(rol: Rol) {
    return this.http.put<Rol>(this.url, rol);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
