/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa_e_partida;

import Personaje.NPC;
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
  private ArrayList<NPC> npcs;

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

  public ArrayList<NPC> getNpcs() {
    return npcs;
  }

  public void setNpcs(ArrayList<NPC> npcs) {
    this.npcs = npcs;
  }

  public void setNpcs(NPC npcs) {
    this.npcs.add(npcs);
  }

  public boolean isNpc() {
    if (npcs.isEmpty()) return false;
    else return true;
  }

  public void ponerObjetos(Objeto objeto) {
    this.objetos.add(objeto);
  }

  public void ponerObjetos(ArrayList<Objeto> objetos) {
    for (int i = 0; i < objetos.size(); i++) {
      this.objetos.add(objetos.get(i));
    }
  }

  /**
   * comprobamos si existe un personaje NPC en la celda con el nombre pasado como parámetro
   *
   * @param nombre del personaje
   * @return
   */
  public NPC personajeNombre(String nombre) {
    NPC npc = null;
    for (NPC npcCelda : this.getNpcs()) {
      if (npcCelda.getNombre().equalsIgnoreCase(nombre)) {
        npc = npcCelda;
      }
    }
    return npc;
  }

  public NPC getNPC() {
    NPC npc = null;
    for (NPC npcCelda : this.getNpcs()) {
      npc = npcCelda;
    }
    return npc;
  }

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
}
