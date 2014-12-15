package comandos;

import Mapa_e_partida.Mapa;
import Personaje.Personaje;
import Utilidades.ConsolaNormal;
import excepciones.ComandoExcepcion;
import excepciones.ExcepcionMover;
import interfaces.Comando;

public class ComandoMover implements Comando {
  private String comando;
  private Mapa mapa;
  private Personaje personaje;
  ConsolaNormal consola = new ConsolaNormal();

  public ComandoMover(String comando, Mapa mapa, Personaje personaje) {
    this.comando = comando;
    this.mapa = mapa;
    this.personaje = personaje;
  }

  @Override
  public void ejecutar() throws ComandoExcepcion {
    try {
      this.personaje.mover(mapa, comando);
      consola.imprimir(this.personaje.getPosicionString());
      consola.imprimir("jugador vida:" + this.personaje.getVida());
      consola.imprimir("jugador energia:" + this.personaje.getEnergia());
    } catch (ExcepcionMover ex) {
      consola.imprimir(ex.toString());
      new ComandoExcepcion(ex.toString());
    } catch (Exception ex) {
      consola.imprimir(ex.toString());
      new ComandoExcepcion(ex.toString());
    }
  }
}
