package Personaje;

import excepciones.ComandoExcepcion;
import interfaces.Comando;
import java.awt.Point;

public class Jugador extends Personaje implements Comando {

  public Jugador(String nombre) {
    super(nombre);
  }

  public Jugador(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    super(vida, nombre, energia, mochila, posicion);
  }

  public Jugador(int vida, String nombre, int energia, Mochila mochila) {
    super(vida, nombre, energia, mochila);
  }

  public Jugador(Personaje copia) {
    super(copia);
  }

  @Override
  public void ejecutar() throws ComandoExcepcion {}
}
