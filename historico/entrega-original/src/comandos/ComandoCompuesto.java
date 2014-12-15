package comandos;

import excepciones.ComandoExcepcion;
import interfaces.Comando;
import java.util.ArrayList;
import java.util.List;
import juego.Juego;

public class ComandoCompuesto implements Comando {

  private List ListComandos = new ArrayList();
  private String comandos;
  private Juego juego;

  public ComandoCompuesto() {
    this.comandos();
  }

  public ComandoCompuesto(String comandos, Juego juego) {
    this();
    this.comandos = comandos;
    this.juego = juego;
  }

  @Override
  public void ejecutar() throws ComandoExcepcion {
    String[] cmds = this.comandos.split(",");
    for (String cmd : cmds) {
      cmd = cmd.trim();
      String cmd1 = cmd.split(" ")[0];
      if (ListComandos.contains(cmd1)) {
        if (cmd.matches(".*[0-9].*")) {
          String veces = cmd.split(" ")[2];
          String cmd2 = cmd.split(" ")[0] + " " + cmd.split(" ")[1];
          if (cmd.contains("norte")
              || cmd.contains("sur")
              || cmd.contains("este")
              || cmd.contains("oeste")) {
            new ComandoRepetido(
                new ComandoMover(cmd2, juego.getMapa(), juego.getJugador()),
                Integer.parseInt(veces));
          } else {
            throw new ComandoExcepcion(
                "falta direccion de movimiento en el segundo parametro: norte, sur, este, oeste");
          }
        } else if (cmd.contains("coger")) {
          new ComandoCoger(cmd, juego).ejecutar();
        } else if (cmd.contains("tirar")) {
          new ComandoTirar(cmd, juego).ejecutar();
        } else if (cmd.contains("mover")) {
          new ComandoMover(cmd, juego.getMapa(), juego.getJugador()).ejecutar();
        } else if (cmd.contains("mirar")) {
          new ComandoMirar(cmd, juego).ejecutar();
        } else if (cmd.contains("atacar")) {
          new ComandoAtacar(cmd, juego).ejecutar();
        }
      }
    }
  }

  private void comandos() {
    ListComandos.add("coger");
    ListComandos.add("tirar");
    ListComandos.add("mover");
    ListComandos.add("mirar");
    ListComandos.add("atacar");
  }
}
