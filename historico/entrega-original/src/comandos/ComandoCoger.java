package comandos;

import Personaje.Objeto;
import Utilidades.ConsolaNormal;
import excepciones.ComandoExcepcion;
import interfaces.Comando;
import java.awt.Point;
import juego.Juego;

public class ComandoCoger implements Comando {
  private String comando;
  private Juego juego;
  ConsolaNormal consola = new ConsolaNormal();

  public ComandoCoger(String comando, Juego juego) {
    this.comando = comando;
    this.juego = juego;
  }

  @Override
  public void ejecutar() throws ComandoExcepcion {
    try {
      String nombreObjeto = this.comando.split(" ")[1];
      Point pto = this.juego.getJugador().getPosicion();
      this.juego.getJugador().coger(new Objeto(nombreObjeto), this.juego.getMapa().getCelda(pto));
    } catch (Exception ex) {
      consola.imprimir(ex.toString());
      new ComandoExcepcion(ex.toString());
    }
  }
}
