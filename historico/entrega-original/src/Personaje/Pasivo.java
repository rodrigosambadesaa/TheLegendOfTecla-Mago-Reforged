package Personaje;

import java.awt.Point;

public class Pasivo extends Enemigo {

  public Pasivo(String nombre) {
    super(nombre);
  }

  public Pasivo(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    super(vida, nombre, energia, mochila, posicion);
  }

  public Pasivo(int vida, String nombre, int energia, Mochila mochila) {
    super(vida, nombre, energia, mochila);
  }

  public Pasivo(Personaje copia) {
    super(copia);
  }
}
