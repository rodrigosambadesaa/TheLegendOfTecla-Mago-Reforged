package Mapa_e_partida;

import Personaje.Objeto;
import java.util.ArrayList;

/** Celda transitable o bloqueada del mapa histórico. */
public class Celda {

  private String descripcion;
  private ArrayList<Objeto> objetos = new ArrayList<>();
  private boolean transitable;

  public Celda(String descripcion, ArrayList<Objeto> objetos, boolean transitable) {
    this.descripcion = descripcion;
    this.objetos = objetos;
    this.transitable = transitable;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setObjetos(ArrayList<Objeto> objetos) {
    this.objetos = objetos;
  }

  public void setTransitable(boolean transitable) {
    this.transitable = transitable;
  }

  public void ponerObjetos(Objeto objeto) {
    this.objetos.add(objeto);
  }

  public void ponerObjetos(ArrayList<Objeto> objetos) {
    this.objetos.addAll(objetos);
  }

  public String getDescripcion() {
    return descripcion;
  }

  public ArrayList<Objeto> getObjetos() {
    return objetos;
  }

  public boolean isTransitable() {
    return transitable;
  }

  @Override
  public String toString() {
    String retorno = this.descripcion;
    if (this.objetos != null) {
      retorno += ", " + this.objetos.toString();
    }
    retorno += ", " + this.transitable;
    return retorno;
  }
}
