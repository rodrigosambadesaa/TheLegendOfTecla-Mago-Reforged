package Personaje;

import Mapa_e_partida.Celda;
import Mapa_e_partida.Mapa;
import java.awt.Point;
import java.util.ArrayList;

/** Personaje jugable de la primera entrega. */
public class Personaje {

  private int vida;
  private String nombre;
  private int energia;
  private Mochila mochila;
  private final ArrayList<Point> posicion;

  public Personaje() {
    posicion = new ArrayList<>();
  }

  public Personaje(int vida, String nombre, int energia, Mochila mochila) {
    this.setVida(vida);
    this.setNombre(nombre);
    this.setEnergia(energia);
    this.setMochila(mochila);
    posicion = new ArrayList<>();
  }

  // getters
  public int getVida() {
    return vida;
  }

  public String getNombre() {
    return nombre;
  }

  public int getEnergia() {
    return energia;
  }

  public Mochila getMochila() {
    return mochila;
  }

  public Point getPosicionActual() {
    return posicion.isEmpty() ? new Point(0, 0) : posicion.get(posicion.size() - 1);
  }

  public String getPosicionActualString() {
    int x = this.getPosicionActual().x;
    int y = this.getPosicionActual().y;
    return "(" + x + "," + y + ")";
  }

  public ArrayList<Point> getPosicion() {
    return this.posicion;
  }

  public void setVida(int vida) {
    if (vida > 0 && vida < 101) {
      this.vida = vida;
    } else {
      System.out.println("La vida debe tomar un valor entre 0 y 100");
    }
  }

  public void setNombre(String nombre) {
    if (nombre.length() < 100) {
      this.nombre = nombre;
    } else {
      System.out.println("El nombre no puede ser tan largo");
    }
  }

  public void setEnergia(int energia) {
    if (energia > 0 && energia < 101) {
      this.energia = energia;
    } else {
      System.out.println("La energia debe tomar un valor entre 0 y 100");
    }
  }

  public void setPosicion(Point posicion) {
    this.posicion.add(posicion);
  }

  public void setMochila(Mochila mochila) {
    this.mochila = mochila;
  }

  /**
   * retona un String coa posicion inicial do personaxe no mapa, exemplos 0,0
   *
   * @param mapa
   * @return
   */
  public Point empezar(Mapa mapa) {
    Point p = new Point(0, 0);
    this.setPosicion(p);
    return this.getPosicionActual();
  }

  public Point empezar(Mapa mapa, Point posicion) {
    this.setPosicion(posicion);
    return posicion;
  }

  /**
   * devolve unha cadea con ok, ou o motivo polo que non se move
   *
   * @param mapa
   * @return
   */
  public String mover(Mapa mapa, String direccion) {
    // o primeiro elemento é o i das coordenadas e o segundo a j
    int i = (int) this.getPosicionActual().getX();
    int j = (int) this.getPosicionActual().getY();

    if (i == 0 && "norte".equalsIgnoreCase(direccion)) {
      return "sales do mapa polo norte";
    } else if (j == 0 && "oeste".equalsIgnoreCase(direccion)) {
      return "sales do mapa polo oeste";
    } else if (i == 19 && "sur".equalsIgnoreCase(direccion)) {
      return "sales do mapa polo sur";
    } else if (j == 19 && "este".equalsIgnoreCase(direccion)) {
      return "sales do mapa polo este";
    }

    if ("norte".equalsIgnoreCase(direccion)) {
      i--;
    } else if ("sur".equalsIgnoreCase(direccion)) {
      i++;
    } else if ("oeste".equalsIgnoreCase(direccion)) {
      j--;
    } else if ("este".equalsIgnoreCase(direccion)) {
      j++;
    } else {
      return "direccion descoñecida";
    }
    Celda celda = mapa.getCelda(new Point(i, j));
    if (!celda.isTransitable()) {
      return "celda non transitable:" + i + "," + j;
    }
    this.posicion.add(new Point(i, j));
    for (Objeto objeto : celda.getObjetos()) {
      System.out.println("objeto: " + objeto.getTipo());
    }
    return "ok";
  }

  public void imprimePosicion() {
    for (int i = 0; i < posicion.size(); i++) {
      int x = posicion.get(i).x;
      int y = posicion.get(i).y;
      System.out.println("(" + x + "," + y + ")");
    }
  }

  public void mirar(Mapa mapa) {
    Celda celda = mapa.getCelda(this.getPosicionActual());
    if (celda.getObjetos().isEmpty()) {
      System.out.println("Non hai obxetos na celda");
    } else {
      System.out.println("Tipo obxeto:" + celda.getObjetos().get(0).getTipo());
    }
  }
}
