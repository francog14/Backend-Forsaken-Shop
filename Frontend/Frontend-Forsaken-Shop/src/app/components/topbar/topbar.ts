import { Component, computed, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { Actor } from '../../models/forsaken.models';
import { ActorService } from '../../services/actor.service';
import { AuthService } from '../../services/auth.service';

interface NavLink {
  path: string;
  label: string;
  actores: Actor[];
}

@Component({
  selector: 'app-topbar',
  imports: [FormsModule, RouterLink, RouterLinkActive],
  templateUrl: './topbar.html',
  styleUrl: './topbar.scss',
})
export class TopbarComponent {
  private readonly actorService = inject(ActorService);
  private readonly authService = inject(AuthService);

  readonly actor = this.actorService.actor;
  readonly session = this.authService.session;
  readonly actores: { valor: Actor; etiqueta: string }[] = [
    { valor: 'usuario', etiqueta: 'Usuario' },
    { valor: 'vendedor', etiqueta: 'Vendedor' },
    { valor: 'bodeguero', etiqueta: 'Bodeguero' },
    { valor: 'admin', etiqueta: 'Admin' },
  ];

  private readonly links: NavLink[] = [
    { path: '/tienda', label: 'Tienda', actores: ['usuario'] },
    { path: '/mensajes', label: 'Soporte', actores: ['usuario', 'vendedor', 'admin'] },
    { path: '/vendedor', label: 'Panel vendedor', actores: ['vendedor'] },
    { path: '/ventas', label: 'Ventas', actores: ['vendedor', 'admin'] },
    { path: '/pedidos', label: 'Pedidos', actores: ['vendedor', 'bodeguero', 'admin'] },
    { path: '/bodega', label: 'Panel bodega', actores: ['bodeguero'] },
    { path: '/catalogo', label: 'Prendas', actores: ['bodeguero', 'admin'] },
    { path: '/categorias', label: 'Categorias', actores: ['bodeguero', 'admin'] },
    { path: '/admin', label: 'Panel admin', actores: ['admin'] },
    { path: '/usuarios', label: 'Usuarios', actores: ['admin', 'vendedor'] },
  ];

  readonly linksVisibles = computed(() =>
    this.links.filter((link) => link.actores.includes(this.actor()))
  );

  cambiarActor(actor: Actor) {
    this.actorService.cambiarActor(actor);
  }

  logout() {
    this.authService.logout();
    this.actorService.cambiarActor('usuario');
  }
}
