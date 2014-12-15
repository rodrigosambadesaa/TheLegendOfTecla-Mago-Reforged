package Personaje;

import java.awt.Point;

public class Activo extends Enemigo {

  public Activo(String nombre) {
    super(nombre);
  }

  public Activo(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    super(vida, nombre, energia, mochila, posicion);
  }

  public Activo(int vida, String nombre, int energia, Mochila mochila) {
    super(vida, nombre, energia, mochila);
  }

  public Activo(Personaje copia) {
    super(copia);
  }
}
