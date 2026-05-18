import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { AuthSession } from '../models/forsaken.models';

interface LoginPayload {
  email: string;
  password: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly url = environment.api.auth;
  private readonly storageKey = 'forsaken_session';

  readonly session = signal<AuthSession | null>(this.obtenerSesionInicial());

  login(payload: LoginPayload) {
    return this.http.post<AuthSession>(`${this.url}/login`, payload).pipe(
      tap((session) => this.guardarSesion(session))
    );
  }

  logout() {
    localStorage.removeItem(this.storageKey);
    this.session.set(null);
  }

  private guardarSesion(session: AuthSession) {
    localStorage.setItem(this.storageKey, JSON.stringify(session));
    this.session.set(session);
  }

  private obtenerSesionInicial() {
    const raw = localStorage.getItem(this.storageKey);
    if (!raw) {
      return null;
    }

    try {
      return JSON.parse(raw) as AuthSession;
    } catch {
      localStorage.removeItem(this.storageKey);
      return null;
    }
  }
}
