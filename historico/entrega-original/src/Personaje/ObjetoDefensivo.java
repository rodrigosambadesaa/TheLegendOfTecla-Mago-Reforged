package Personaje;

public class ObjetoDefensivo extends Objeto {

  @Override
  public void usar(Personaje personaje) throws Exception {
    throw new Exception(
        "no se puede usar, solo se usa a la hora de calcular defenda cuando atacan");
  }
}
