package comandos;

import Personaje.Personaje;
import Utilidades.ConsolaNormal;
import excepciones.ComandoExcepcion;
import interfaces.Comando;
import juego.Juego;

public class ComandoAtacar implements Comando {
  private String comando;
  private Juego juego;
  ConsolaNormal consola = new ConsolaNormal();

  public ComandoAtacar(String comando, Juego juego) {
    this.comando = comando;
    this.juego = juego;
  }

  @Override
  public void ejecutar() throws ComandoExcepcion {
    try {
      String sNombrePersSecundario = this.comando.split(" ")[1];
      this.juego.getJugador().atacar(new Personaje(sNombrePersSecundario));
    } catch (Exception ex) {
      consola.imprimir(ex.toString());
      new ComandoExcepcion(ex.toString());
    }
  }
}
