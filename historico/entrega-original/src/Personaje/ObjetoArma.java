package Personaje;

public class ObjetoArma extends Objeto {

  @Override
  public void usar(Personaje personaje) throws Exception {
    throw new Exception("no se puede usar, solo se usa a la hora de calcular daño en atacar");
  }
}
