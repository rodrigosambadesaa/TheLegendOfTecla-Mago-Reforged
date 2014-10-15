package Personaje;

import java.util.ArrayList;

/** Mochila sencilla de la primera entrega. */
public class Mochila {

  private ArrayList<Objeto> objetos = new ArrayList<>();
  private String nome;
  private int capacidad;
  private int bolsillos;

  public Mochila() {}

  public Mochila(String nome, int capacidad) {
    this.setNome(nome);
    this.setCapacidad(capacidad);
  }

  public Mochila(String nome, int capacidad, int bolsillos) {

    this.setNome(nome);
    this.setCapacidad(capacidad);
    this.setBolsillos(bolsillos);
  }

  public ArrayList<Objeto> getObjetos() {
    return objetos;
  }

  public void setObjetos(ArrayList<Objeto> objetos) {
    if (objetos != null) {
      this.objetos = objetos;
    }
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    if (nome.length() < 100) {
      this.nome = nome;
    } else {
      System.out.println("El nombre es demasiado largo");
    }
  }

  public int getCapacidad() {

    return capacidad;
  }

  public void setCapacidad(int capacidad) {
    if (capacidad > 0 && capacidad < 100) {
      this.capacidad = capacidad;
    } else {
      System.out.println("La capacidad debe tomar un valor entre 0 y 100");
    }
  }

  public int getBolsillos() {
    return bolsillos;
  }

  public void setBolsillos(int bolsillos) {
    if (bolsillos > 0 && bolsillos < 10) {
      this.bolsillos = bolsillos;
    } else {
      System.out.println("La mochila debe tener entre 1 y 9 bolsillos");
    }
  }
}
