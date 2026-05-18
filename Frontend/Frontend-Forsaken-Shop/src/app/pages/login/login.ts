import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Actor } from '../../models/forsaken.models';
import { ActorService } from '../../services/actor.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class LoginComponent {
  private readonly authService = inject(AuthService);
  private readonly actorService = inject(ActorService);
  private readonly router = inject(Router);

  email = 'admin@forsaken.cl';
  password = 'admin123';
  error = '';
  cargando = false;

  readonly cuentasDemo = [
    { email: 'admin@forsaken.cl', password: 'admin123', rol: 'Admin' },
    { email: 'vendedor@forsaken.cl', password: 'vendedor123', rol: 'Vendedor' },
    { email: 'bodega@forsaken.cl', password: 'bodega123', rol: 'Bodeguero' },
    { email: 'cliente@forsaken.cl', password: 'cliente123', rol: 'Usuario' },
  ];

  login() {
    this.error = '';
    this.cargando = true;

    this.authService.login({ email: this.email, password: this.password }).subscribe({
      next: (session) => {
        const actor = this.actorDesdeRol(session.rol);
        this.actorService.cambiarActor(actor);
        this.cargando = false;
        this.router.navigateByUrl(this.rutaPorActor(actor));
      },
      error: () => {
        this.error = 'Credenciales invalidas o microservicio Auth apagado.';
        this.cargando = false;
      },
    });
  }

  usarCuenta(email: string, password: string) {
    this.email = email;
    this.password = password;
  }

  private actorDesdeRol(rol: string): Actor {
    const normalizado = rol.toUpperCase();
    if (normalizado === 'ADMIN') {
      return 'admin';
    }
    if (normalizado === 'VENDEDOR') {
      return 'vendedor';
    }
    if (normalizado === 'BODEGUERO') {
      return 'bodeguero';
    }
    return 'usuario';
  }

  private rutaPorActor(actor: Actor) {
    if (actor === 'admin') {
      return '/admin';
    }
    if (actor === 'vendedor') {
      return '/vendedor';
    }
    if (actor === 'bodeguero') {
      return '/bodega';
    }
    return '/tienda';
  }
}
