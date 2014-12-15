package Personaje;

public class PocimaVeneno extends Objeto {

  @Override
  public void usar(Personaje personaje) {
    personaje.setSalud(personaje.getSalud() - this.getEfecto());
  }
}
