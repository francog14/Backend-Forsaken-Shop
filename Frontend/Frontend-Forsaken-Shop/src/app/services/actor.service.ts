import { Injectable, signal } from '@angular/core';
import { Actor } from '../models/forsaken.models';

@Injectable({ providedIn: 'root' })
export class ActorService {
  private readonly storageKey = 'forsaken_actor';
  private readonly actoresValidos: Actor[] = ['usuario', 'vendedor', 'bodeguero', 'admin'];
  readonly actor = signal<Actor>(this.obtenerActorInicial());

  cambiarActor(actor: Actor) {
    this.actor.set(actor);
    localStorage.setItem(this.storageKey, actor);
  }

  private obtenerActorInicial(): Actor {
    const guardado = localStorage.getItem(this.storageKey) as Actor | null;
    return guardado && this.actoresValidos.includes(guardado) ? guardado : 'usuario';
  }
}
