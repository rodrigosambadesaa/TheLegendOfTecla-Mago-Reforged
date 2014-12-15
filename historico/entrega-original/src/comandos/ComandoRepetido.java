/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import excepciones.ComandoExcepcion;
import interfaces.Comando;

public class ComandoRepetido implements Comando {
  private final Comando cmd;
  private final int veces;

  public ComandoRepetido(Comando cmd, int veces) throws ComandoExcepcion {
    this.cmd = cmd;
    this.veces = veces;
    for (int i = 0; i < this.veces; i++) {
      cmd.ejecutar();
    }
  }

  @Override
  public void ejecutar() throws ComandoExcepcion {
    for (int i = 0; i < this.veces; i++) {
      cmd.ejecutar();
    }
  }
}
