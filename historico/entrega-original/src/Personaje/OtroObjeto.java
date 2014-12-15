package Personaje;

public class OtroObjeto extends Objeto {

  @Override
  public void usar(Personaje personaje) throws Exception {
    throw new Exception("no puede ser usado");
  }
}
