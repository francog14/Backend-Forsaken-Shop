import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Categoria, CategoriaForm } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class CategoriaService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.categorias;

  listar() {
    return this.http.get<Categoria[]>(this.url);
  }

  crear(categoria: CategoriaForm) {
    return this.http.post<Categoria>(this.url, categoria);
  }

  actualizar(categoria: Categoria) {
    return this.http.put<Categoria>(this.url, categoria);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}
