package interfaces;

import excepciones.ComandoExcepcion;

public interface Comando {
  void ejecutar() throws ComandoExcepcion;
}
