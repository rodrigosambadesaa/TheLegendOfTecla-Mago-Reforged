package Personaje;

public class PocimaEnergia extends Objeto {

  @Override
  public void usar(Personaje personaje) {
    personaje.setEnergia(personaje.getEnergia() + this.getEfecto());
  }
}
