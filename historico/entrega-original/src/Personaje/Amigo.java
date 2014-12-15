package Personaje;

import java.awt.Point;

public class Amigo extends NPC {

  public Amigo(String nombre) {
    super(nombre);
  }

  public Amigo(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    super(vida, nombre, energia, mochila, posicion);
  }

  public Amigo(int vida, String nombre, int energia, Mochila mochila) {
    super(vida, nombre, energia, mochila);
  }

  public Amigo(Personaje copia) {
    super(copia);
  }

  @Override
  public void atacar(Personaje personaje) throws Exception {
    throw new Exception("los personajes amigos no atacan");
  }
}
