package Personaje;

import excepciones.ComandoExcepcion;
import java.awt.Point;

public class Guerrero extends Jugador {

  public Guerrero(String nombre) {
    super(nombre);
  }

  public Guerrero(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    super(vida, nombre, energia, mochila, posicion);
  }

  public Guerrero(int vida, String nombre, int energia, Mochila mochila) {
    super(vida, nombre, energia, mochila);
  }

  public Guerrero(Personaje copia) {
    super(copia);
  }

  @Override
  public void atacar(Personaje personaje) throws Exception {
    int dano = 0;
    // miramos si hay ese personaje en la celda en la que está el jugador
    NPC npcs = this.mapa.getCelda(this.getPosicion()).personajeNombre(personaje.getNombre());
    if (npcs == null) {
      throw new ComandoExcepcion(
          "no hay ningun personaje con ese nombre en la celda:" + personaje.getNombre());
    }

    // Hai personaje NPC procedemos a calcular cantos puntos de dano fai o inimigo (en funcion da
    // sua enerxia e forza e da defensa do xogador)
    float fuerzaCoef = (this.getFuerza() / 10);
    int energiaUsada;
    // el guerrero puede atacar a Amigo o Enemigo
    if ((npcs instanceof Amigo) || (npcs instanceof Enemigo)) {
      // le atacamos y le quitamos salud dependiendo de la fuerza del jugador y de la defensa del
      // npcs
      dano = Math.abs((this.getFuerza() / 5) - (npcs.getDefensa() / 8));
      // le quitamos salud al npcs
      consola.imprimir(
          "salud npcs " + personaje.getNombre() + " antes de ataque:" + npcs.getSalud());
      npcs.setSalud(npcs.getSalud() - dano);
      consola.imprimir("salud npcs " + personaje.getNombre() + " tras ataque:" + npcs.getSalud());
      // se o npcs e pasivo e lle queda saúde responderanos á súa vez con un ataque
      // if(npcs.getTipo().equalsIgnoreCase("enemigopasivo") && npcs.getSalud()>0){
      // se é un enemigo pasivo e ten saúde
      if ((npcs instanceof Pasivo) && npcs.getSalud() > 0) {
        // nos quita salud a nosotros
        int danoRecibido = Math.abs((this.getEnergia() / 10) - (npcs.getEnergia() / 10));
        consola.imprimir("salud jugador antes de ataque:" + this.getSalud());
        this.setSalud(this.getSalud() - danoRecibido);
        consola.imprimir("salud jugador tras ataque:" + this.getSalud());
        if (this.getSalud() <= 0) {
          consola.imprimir("nos matan, quedamos sin salud");
        }
      }
      // si no tiene salud gestionamos su cadaver
      if (npcs.getSalud() <= 0) {
        gestionCadaver((NPC) npcs, this.mapa.getCelda(npcs.getPosicion()));
      }
    }
  }

  @Override
  public int calculaGastoEnerxia() {
    int gasto = 2;
    if (this.mochila.calculaPeso() > 0) {
      gasto = gasto + (int) ((this.mochila.calculaPeso() / 5) + 0.999);
    }
    return gasto;
  }
}
