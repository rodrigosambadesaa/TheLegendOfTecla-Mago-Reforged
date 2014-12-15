package Personaje;

public class PocimaSalud extends Objeto {

  @Override
  public void usar(Personaje personaje) {
    personaje.setSalud(personaje.getSalud() + this.getEfecto());
  }
}
