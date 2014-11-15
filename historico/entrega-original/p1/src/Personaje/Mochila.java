/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

/**
 * @author miguel.alonso
 */
import Mapa_e_partida.Celda;
import Mapa_e_partida.Mapa;
import java.awt.Point;
import java.util.ArrayList;

public class Mochila {

  private ArrayList<Objeto> objetos = new ArrayList<Objeto>();
  private String nome;
  private int capacidad;
  private int bolsillos;
  private double pesoMax;
  private String descripcion;

  public Mochila(String nome, int capacidad) {
    this.nome = nome;
    this.capacidad = capacidad;
  }

  public Mochila() {}

  public Mochila(String nome, int capacidad, int bolsillos) {
    this.nome = nome;
    this.capacidad = capacidad;
    this.bolsillos = bolsillos;
  }

  // copy constructor
  public Mochila(Mochila copia) {
    this.bolsillos = copia.getBolsillos();
    this.capacidad = copia.getCapacidad();
    this.nome = copia.getNome();
    this.objetos = copia.getObjetos();
  }

  // setters

  public ArrayList<Objeto> getObjetos() {
    return objetos;
  }

  public void setObjetos(ArrayList<Objeto> objetos) {
    this.objetos = objetos;
  }

  public void setObjetos(Objeto objeto) {
    this.objetos.add(objeto);
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

  public void vaciaMochila() {
    ArrayList<Objeto> vacio = new ArrayList<>();
    this.setObjetos(vacio);
  }

  public double calculaPeso() {
    double sumatorio = 0;
    for (int i = 0; i < this.objetos.size(); i++) {
      sumatorio = sumatorio + objetos.get(i).getPeso();
    }
    return sumatorio;
  }

  public double getPesoMax() {
    return pesoMax;
  }

  public void setPesoMax(double pesoMax) {
    this.pesoMax = pesoMax;
  }

  public double getPesoActual() {
    double peso = 0.0;
    for (Objeto objeto : this.getObjetos()) {
      peso += objeto.getPeso();
    }
    return peso;
  }

  //    public void setPesoActual(double pesoActual) {
  //        this.pesoActual = pesoActual;
  //    }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String ojearInventario() {
    String retorno = "";
    for (Objeto objeto : objetos) {
      retorno += objeto.toString() + "\n";
    }
    retorno +=
        "\nNúmero actual de objetos en mochila:"
            + this.objetos.size()
            + " (maximo: "
            + this.getCapacidad()
            + "), "
            + (this.objetos.size() / (double) this.getCapacidad()) * 100
            + "%";
    retorno +=
        "\nPeso actual mochila:"
            + this.getPesoActual()
            + " (maximo peso: "
            + this.getPesoMax()
            + "), "
            + (this.getPesoActual() / this.getPesoMax()) * 100
            + "%";
    return retorno;
  }

  public String cogerObjeto(Point coordenadas, Mapa mapa, String nombreObjeto) {
    String retorno = "sin objeto";
    Celda celda = mapa.getCelda(coordenadas);
    boolean bExiste = false;
    if (celda.getObjetos() != null) {
      for (Objeto objeto : celda.getObjetos()) {
        // comprobamos que el objeto que quiere coger sea el pedido
        if (nombreObjeto.equalsIgnoreCase(objeto.getNombre())) {
          bExiste = true;
          // si no excede el número de objetos en la mochila su capacidad continuamos
          if (this.objetos.size() < this.getCapacidad()) {
            if (objeto.getTipo_objeto().equalsIgnoreCase("arma") && this.tieneTipoObjeto("arma")) {
              System.out.println("solo puedes tener un arma");
            } else {
              // ahora comprobamos que no se exceda el peso
              if (this.getPesoMax() >= this.getPesoActual() + objeto.getPeso()) {
                this.setObjetos(objeto);
                // this.setPesoActual(this.getPesoActual() + objeto.getPeso());
                retorno = "agregado";
                // lo quitamos de la celda
                celda.getObjetos().remove(objeto);
                // salimos porque solo puede coger un objeto
                break;
              } else {
                retorno = "con el objeto encontrado excedemos el peso máximo de la mochila";
              }
            }
          } else {
            retorno = "la mochila ya esta al limite de capacidad";
          }
        }
      }
      if (!bExiste) retorno = "no existe el objeto que quieres coger:" + nombreObjeto;
    }
    return retorno;
  }

  public String tirarObjeto(Point coordenadas, Mapa mapa, String nombreObjeto) {
    String retorno = "no hay objetos en la mochila";
    boolean bExiste = false;
    if (this.getObjetos().size() > 0) {
      Objeto objetoABorrar = null;
      for (Objeto objeto : objetos) {
        if (nombreObjeto.equalsIgnoreCase(objeto.getNombre())) {
          bExiste = true;
          // dejamos el objeto en la celda que estamos
          mapa.getCelda(coordenadas).setObjetos(objeto);
          objetoABorrar = objeto;
          retorno = "tirado objeto: " + objeto.toString() + "\n";
          // this.setPesoActual(this.getPesoActual() - objeto.getPeso());
        }
      }
      if (!bExiste) retorno = "no existe el objeto que quieres tirar:" + nombreObjeto;

      if (objetoABorrar != null) {
        // borramos el objeto de la mochila
        this.objetos.remove(objetoABorrar);
      }
    }

    return retorno;
  }

  public boolean tieneObjeto(String nombreObjeto) {
    boolean bExiste = false;
    for (Objeto objeto : this.getObjetos()) {
      // comprobamos si el nombre del objeto está en la mochila
      if (nombreObjeto.equalsIgnoreCase(objeto.getNombre())) {
        bExiste = true;
      }
    }
    return bExiste;
  }

  public boolean tieneTipoObjeto(String tipo_objeto) {
    boolean bExiste = false;
    for (Objeto objeto : this.getObjetos()) {
      // comprobamos si el nombre del objeto está en la mochila
      if (tipo_objeto.equalsIgnoreCase(objeto.getTipo_objeto())) {
        bExiste = true;
      }
    }
    return bExiste;
  }

  public Objeto objetoBuscado(String nombreObjeto) {
    Objeto obj = null;
    for (Objeto objeto : this.getObjetos()) {
      // comprobamos si el nombre del objeto está en la mochila
      if (nombreObjeto.equalsIgnoreCase(objeto.getNombre())) {
        obj = objeto;
        break;
      }
    }
    return obj;
  }

  public boolean eliminarObjeto(Objeto objeto) {
    return this.objetos.remove(objeto);
  }

  @Override
  public String toString() {
    return "Mochila{"
        + "objetos="
        + objetos
        + ", nome="
        + nome
        + ", capacidad="
        + capacidad
        + ", bolsillos="
        + bolsillos
        + ", pesoMax="
        + pesoMax
        + ", descripcion="
        + descripcion
        + '}';
  }
}
