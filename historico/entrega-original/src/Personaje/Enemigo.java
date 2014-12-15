package Personaje;

import java.awt.Point;
import java.util.Random;

public class Enemigo extends NPC {

  public Enemigo(String nombre) {
    super(nombre);
  }

  public Enemigo(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    super(vida, nombre, energia, mochila, posicion);
  }

  public Enemigo(int vida, String nombre, int energia, Mochila mochila) {
    super(vida, nombre, energia, mochila);
  }

  public Enemigo(Personaje copia) {
    super(copia);
  }

  @Override
  public void atacar(Personaje personaje) throws Exception {
    int dano = 0;
    super.atacar(personaje);
    // si da una excepcion en la padre ya no hace lo siguiente
    // calcular cantos puntos de daño fai o inimigo (en funcion da sua enerxia e forza e da defensa
    // do xogador)
    float fuerzaCoef = (this.getFuerza() / 10);
    int energiaUsada;

    // se o inimigo non ten enerxia non ataca
    if (this.energia > 0) {
      if (this.energia > ((int) this.energiaMaxima / 7)) {
        energiaUsada = (int) this.energiaMaxima / 7;
      } else {
        energiaUsada = this.energia;
      }

      // calculo de impacto critico
      Random aleatorio = new Random();
      int tirada = aleatorio.nextInt(99);
      int critico = 4;
      if (tirada >= (100 - this.probabilidadCritico)) {
        consola.imprimir(this.nombre + " ha hecho un impacto critico!");
        critico = 2;
      }

      // funcion para calcular o dano
      dano =
          ((int)
              ((fuerzaCoef * energiaUsada)
                  / critico)); // en caso de retirar o critico, sustituilo aqui por 4

      // restar vida ao xogador
      personaje.setVida(personaje.getVida() - dano);

      // restar enerxia ao npc
      this.setEnergia(this.getEnergia() - energiaUsada);
      consola.imprimir(
          "Ataque de "
              + this.getNombre()
              + " al jugador "
              + personaje.getNombre()
              + ", daño provocado: "
              + dano
              + ", vida restante del jugador: "
              + personaje.getVida());
    } else {
      consola.imprimir(this.nombre + " no tiene suficiente energia para atacar");
    }
  }
}
