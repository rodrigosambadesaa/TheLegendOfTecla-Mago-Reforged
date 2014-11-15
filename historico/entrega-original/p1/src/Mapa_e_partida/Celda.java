/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa_e_partida;

import Personaje.Npcs;
import Personaje.Objeto;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Miguel Alonso Castro, Rodrigo Sambade Saa
 */
public class Celda {
  private Point posicionMapa;
  private String descripcion;
  private ArrayList<Objeto> objetos;
  private boolean transitable;
  private int numero;
  private String descAmplia;
  private ArrayList<Npcs> npcs;

  public Celda() {
    objetos = new ArrayList<>();
    npcs = new ArrayList<>();
  }

  public Celda(String descripcion, ArrayList<Objeto> objetos, boolean transitable) {
    this();
    this.descripcion = descripcion;
    this.objetos = new ArrayList<>(objetos);
    this.transitable = transitable;
  }

  public Celda(String descripcion, ArrayList<Objeto> objetos) {
    this();
    this.descripcion = descripcion;
    this.objetos = new ArrayList<>(objetos);
  }

  public Celda(Point posicionMapa, String descripcion, String descAmplia) {
    this();
    this.posicionMapa = posicionMapa;
    this.descripcion = descripcion;
    this.descAmplia = descAmplia;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public ArrayList<Objeto> getObjetos() {
    return objetos;
  }

  public void setObjetos(ArrayList<Objeto> objetos) {
    this.objetos = new ArrayList<>(objetos);
  }

  public void setObjetos(Objeto objeto) {
    this.objetos.add(objeto);
  }

  public boolean isTransitable() {
    return transitable;
  }

  public void setTransitable(boolean transitable) {
    this.transitable = transitable;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getDescAmplia() {
    return descAmplia;
  }

  public void setDescAmplia(String descAmplia) {
    this.descAmplia = descAmplia;
  }

  public Point getPosicionMapa() {
    Point copia = new Point(posicionMapa);
    return copia;
  }

  public void setPosicionMapa(Point posicionMapa) {
    Point copia = new Point(posicionMapa);
    this.posicionMapa = copia;
  }

  public ArrayList<Npcs> getNpcs() {
    return npcs;
  }

  public void setNpcs(ArrayList<Npcs> npcs) {
    this.npcs = npcs;
  }

  public void setNpcs(Npcs npcs) {
    this.npcs.add(npcs);
  }

  public boolean isNpc() {
    if (npcs.isEmpty()) return false;
    else return true;
  }

  //    @Override
  //    public String toString() {
  //        String retorno = "";
  //        retorno = this.posicionMapa;
  //        retorno += ", " + this.descripcion;
  //        if(this.objetos != null) retorno += ", " + this.objetos.toString();
  //        retorno += ", " + this.transitable;
  //        retorno += ", " + this.descAmplia;
  //        return retorno;
  //    }
  @Override
  public String toString() {
    return "Celda{"
        + "posicionMapa="
        + posicionMapa
        + ", descripcion="
        + descripcion
        + ", objetos="
        + objetos
        + ", transitable="
        + transitable
        + ", numero="
        + numero
        + ", descAmplia="
        + descAmplia
        + ", npcs="
        + npcs
        + '}';
  }

  public void ponerObjetos(Objeto objeto) {
    this.objetos.add(objeto);
  }

  public void ponerObjetos(ArrayList<Objeto> objetos) {
    for (int i = 0; i < objetos.size(); i++) {
      this.objetos.add(objetos.get(i));
    }
  }
}
