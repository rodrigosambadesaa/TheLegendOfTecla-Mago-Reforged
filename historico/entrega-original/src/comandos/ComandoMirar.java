package comandos;

import Utilidades.ConsolaNormal;
import excepciones.ComandoExcepcion;
import interfaces.Comando;
import java.awt.Point;
import juego.Juego;

public class ComandoMirar implements Comando {
  private String comando;
  private Juego juego;
  ConsolaNormal consola = new ConsolaNormal();

  public ComandoMirar(String comando, Juego juego) {
    this.comando = comando;
    this.juego = juego;
  }

  @Override
  public void ejecutar() throws ComandoExcepcion {
    try {
      Point pto = this.juego.getJugador().getPosicion();
      // mostramos solo la descripcion de un objeto si pone p.e mirar espada, en caso de poner mirar
      // mostramos los que tenga la celda
      if (this.comando.split(" ").length > 1) {
        String objeto = this.comando.split(" ")[1];
        consola.imprimir(juego.getMapa().mirarObjetoCelda(pto, objeto));
      } else {
        consola.imprimir(juego.getMapa().mirarCelda(pto));
      }
    } catch (Exception ex) {
      consola.imprimir(ex.toString());
      new ComandoExcepcion(ex.toString());
    }
  }
}
