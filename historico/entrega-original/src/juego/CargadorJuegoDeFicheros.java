package juego;

import interfaces.CargadorJuego;

public class CargadorJuegoDeFicheros implements CargadorJuego {

  @Override
  public Juego cargarJuego() {
    Juego juego = new Juego();
    // llamamos al que carga los ficheros que es el que lleva como parámetro la ruta donde están
    juego.cargarMapaDeFichero("");
    return juego;
  }
}
