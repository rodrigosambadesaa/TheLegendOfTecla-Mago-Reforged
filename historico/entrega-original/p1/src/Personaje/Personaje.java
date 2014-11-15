/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import Mapa_e_partida.Celda;
import Mapa_e_partida.Mapa;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author miguel.alonso
 */
public class Personaje {
  private int vida;
  private int vidaMaxima;
  private int energiaMaxima;
  private int salud;
  private int saludMaxima;
  private String nombre;
  private int energia;
  private Mochila mochila;
  private Point posicion;
  private ArrayList<Point> rutaRecorrida;
  private int fuerza;
  private int fuerzaMaxima;
  private ArrayList<Point> posicion2;

  public Personaje() {
    posicion2 = new ArrayList<>();
    rutaRecorrida = new ArrayList<>();
  }

  public Personaje(int vida, String nombre, int energia, Mochila mochila) {
    this.setVidaMaxima(vida);
    this.setVida(vida);
    this.setEnergiaMaxima(energia);
    this.setNombre(nombre);
    this.setEnergia(energia);
    this.setMochila(mochila);
    posicion2 = new ArrayList<>();
  }

  public Personaje(Personaje copia) {
    this.vida = copia.vida;
    this.vidaMaxima = copia.vidaMaxima;
    this.energia = copia.energia;
    this.energiaMaxima = copia.energiaMaxima;
    this.fuerza = copia.fuerza;
    this.fuerzaMaxima = copia.fuerzaMaxima;
    this.nombre = copia.nombre;
    this.posicion2 = new ArrayList(copia.posicion2);
  }

  // getters

  public Personaje(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    this();
    this.vida = vida;
    this.nombre = nombre;
    this.energia = energia;
    this.mochila = mochila;
    this.posicion = posicion;
  }

  public int getVidaMaxima() {
    return vidaMaxima;
  }

  public void setVidaMaxima(int vidaMaxima) {
    this.vidaMaxima = vidaMaxima;
  }

  public int getEnergiaMaxima() {
    return energiaMaxima;
  }

  public void setEnergiaMaxima(int energiaMaxima) {
    this.energiaMaxima = energiaMaxima;
  }

  public int getFuerzaMaxima() {
    return fuerzaMaxima;
  }

  public void setFuerzaMaxima(int fuerzaMaxima) {
    this.fuerzaMaxima = fuerzaMaxima;
  }

  public int getVida() {
    return vida;
  }

  public String getNombre() {
    return nombre;
  }

  public int getEnergia() {
    return energia;
  }

  public int getFuerza() {
    return fuerza;
  }

  public void setSalud(int salud) {
    if (salud > 100) {
      System.out.println("La salud no puede ser mayor que 100");
    } else if (salud > 0 && salud < 101) {
      this.salud = salud;
    }
  }

  public void setSaludMaxima(int saludMaxima) {
    this.saludMaxima = saludMaxima;
  }

  public int getSalud() {
    return salud;
  }

  public int getSaludMaxima() {
    return saludMaxima;
  }

  public Mochila getMochila() {
    return mochila;
  }

  public Point getPosicion() {
    Point copia = new Point(this.posicion);
    return copia;
  }

  public Point getPosicionActual() {
    if (posicion2.isEmpty()) {
      return new Point(0, 0);
    } else {
      // declaracion dun novo punto para evitar posibles erros relacionados co aliasing
      Point p = new Point(posicion2.get(posicion2.size() - 1));
      return p;
    }
  }

  public String getPosicionString() {
    String cadena;
    int x = this.getPosicion().x;
    int y = this.getPosicion().y;

    cadena = "(" + x + "," + y + ")";
    return cadena;
  }

  public ArrayList<Point> getRutaRecorrida() {
    return rutaRecorrida;
  }

  public void setRutaRecorrida(ArrayList<Point> rutaRecorrida) {
    this.rutaRecorrida = rutaRecorrida;
  }

  public void setRutaRecorrida(Point posicion) {
    if (!this.rutaRecorrida.contains(posicion)) {
      this.rutaRecorrida.add(posicion);
    }
  }

  public ArrayList<Point> getRutaDescubierta(int tamanoMapa) {

    ArrayList<Point> aDescubiertas = new ArrayList<>();
    for (Point punto : this.rutaRecorrida) {
      if (!aDescubiertas.contains(punto)) aDescubiertas.add(punto);
      // agregamos las celdas adyacentes como descubiertas
      for (int i = punto.x - 1; i <= (punto.x + 1); i++) {
        for (int j = punto.y - 1; j <= (punto.y + 1); j++) {
          if (i > -1 && i < tamanoMapa && j > -1 && j < tamanoMapa) {
            Point punto_adyacente = new Point(i, j);
            if (!aDescubiertas.contains(punto_adyacente)) aDescubiertas.add(punto_adyacente);
          }
        }
      }
    }
    return aDescubiertas;
  }

  public String getPosicionActualString() {
    String cadena;
    int x = this.getPosicionActual().x;
    int y = this.getPosicionActual().y;

    cadena = "(" + x + "," + y + ")";
    return cadena;
  }

  public ArrayList<Point> getPosicion2() {
    ArrayList<Point> copia = new ArrayList<>();

    for (int i = 0; i < this.posicion2.size(); i++) {
      copia.add(this.posicion2.get(i));
    }
    return copia;
  }

  // setters
  public void setVida(int vida) {
    if (vida > 0 && vida <= this.vidaMaxima) {
      this.vida = vida;
    } else if (vida > 100) {
      System.out.println("La vida no puede ser mayor que " + this.vidaMaxima);
    } else {
      this.vida = 0;
      System.out.println("Has muerto!!!");
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
    if (energia > 0 && energia <= this.energiaMaxima) {
      this.energia = energia;
    } else if (energia > 100) {
      System.out.println("La energia no puede ser mayor que " + this.energiaMaxima);
    } else {
      this.energia = 0;
      System.out.println("Has agotado tu energia! usa una pocion o no podras atacar ni moverte!");
    }
  }

  public void setFuerza(int fuerza) {
    if (fuerza > 0 && fuerza <= this.fuerzaMaxima) {
      this.fuerza = fuerza;
    } else {
      System.out.println("La fuerza debe valer entre 1 y " + this.fuerzaMaxima);
    }
  }

  public void setMochila(Mochila mochila) {
    this.mochila = mochila;
  }

  public void setPosicion(Point posicion) {
    this.posicion = posicion;
  }

  public void setPosicion2(ArrayList<Point> posicion2) {
    this.posicion2 = posicion2;
  }

  /**
   * retona un String coa posicion inicial do personaxe no mapa, exemplos 0,0
   *
   * @param mapa
   * @return
   */
  public Point empezar(Mapa mapa) {
    this.setPosicion(new Point(0, 0));
    return this.getPosicion();
  }

  /**
   * devolve unha cadea con ok, ou o motivo polo que non se move
   *
   * @param mapa
   * @param direccion
   * @return
   */
  public String mover(Mapa mapa, String direccion) {
    String retorno = "";
    // collemos a posición actual do personaxe
    int x = this.getPosicion().x;
    int y = this.getPosicion().y;

    if (x == 0 && "norte".equalsIgnoreCase(direccion)) retorno = "sales do mapa polo norte";
    else if (y == 0 && "oeste".equalsIgnoreCase(direccion)) retorno = "sales do mapa polo oeste";
    else if (x == 99 && "este".equalsIgnoreCase(direccion)) retorno = "sales do mapa polo este";
    else if (y == 99 && "sur".equalsIgnoreCase(direccion)) retorno = "sales do mapa polo sur";
    else {
      boolean continuar = true;
      if ("norte".equalsIgnoreCase(direccion)) x--;
      else if ("sur".equalsIgnoreCase(direccion)) x++;
      else if ("oeste".equalsIgnoreCase(direccion)) y--;
      else if ("este".equalsIgnoreCase(direccion)) y++;
      else {
        continuar = false;
        retorno = "ruta descoñecida";
      }

      // se continuar está a false xa non nos movemos do sitio
      if (continuar) {
        Celda celda = mapa.getCelda(new Point(x, y));
        if (celda.isTransitable()) {
          // como é una celda transitable situamonos nela
          this.setPosicion(celda.getPosicionMapa());
          retorno = "ok";
          // añadimos la posiciona a la ruta recorrida
          this.setRutaRecorrida(this.getPosicion());
          Npcs enemigoActivo = hayEnemigoActivo(celda);
          if (enemigoActivo != null) {
            this.recibimosAtaqueNpcsActivo(enemigoActivo, celda);
          }
        } else {
          retorno = "celda non transitable:" + x + "," + y;
        }
      }
    }
    return retorno;
  }

  public Npcs hayEnemigoActivo(Celda celda) {
    Npcs npcsEnemigo = null;
    // comprombamos que ese inimigo está na celda
    ArrayList<Npcs> personajesCelda = celda.getNpcs();
    for (Npcs npcs : personajesCelda) {
      if (npcs.getTipo().equalsIgnoreCase("enemigoactivo")) {
        npcsEnemigo = npcs;
        break;
      }
    }
    return npcsEnemigo;
  }

  public void imprimePosicion() {
    for (Point posicion : this.getPosicion2()) {
      String cadena;
      double x = posicion.getX();
      double y = posicion.getY();
      cadena = "(" + x + "," + y + ")";
      System.out.println(cadena);
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

  public int calculaGastoEnerxia() {
    int gasto = 3;
    if (mochila.calculaPeso() > 0) {
      gasto = gasto + (int) ((mochila.calculaPeso() / 5) + 0.999);
    }
    return gasto;
  }

  public String cogerMochila(Point coordenadas, Mapa mapa) {
    String retorno = "";
    ArrayList<Objeto> objetosCelda = mapa.getCelda(coordenadas).getObjetos();
    boolean bEncontrado = false;
    // miramos si está la mochila en la celda
    for (Objeto objeto : objetosCelda) {
      if (objeto.getNombre().equalsIgnoreCase("mochila")) {
        bEncontrado = true;
        // la capacidad de la mochila en el objeto está en la propiedad efecto
        Mochila mochila = new Mochila("mochila", objeto.getEfecto());
        mochila.setPesoMax(objetosCelda.get(0).getPeso());
        this.setMochila(mochila);
        retorno = "agregada mochila al personaje";
        // la quitamos de la celda
        mapa.getCelda(coordenadas).getObjetos().remove(objeto);
        break;
      }
    }
    if (!bEncontrado) retorno = "no hay mochila en esta celda";
    return retorno;
  }

  /**
   * El personaje principal o jugado ataca a un enemigo
   *
   * @param nombreEnemigo
   * @param personajesCelda
   * @return
   */
  public int atacar(String nombreEnemigo, Celda celda) {
    int dano = 0;
    Npcs enemigo = null;
    // comprombamos que ese inimigo está na celda
    ArrayList<Npcs> personajesCelda = celda.getNpcs();
    for (Npcs npcs : personajesCelda) {
      if (npcs.getNombre().equalsIgnoreCase(nombreEnemigo)) {
        enemigo = npcs;
        break;
      }
    }

    if (enemigo != null) {
      // Hai inimigo procedemos a calcular cantos puntos de dano fai o inimigo (en funcion da sua
      // enerxia e forza e da defensa do xogador)
      float fuerzaCoef = (this.getFuerza() / 10);
      int energiaUsada;

      if (enemigo.getTipo().equalsIgnoreCase("enemigopasivo")
          || enemigo.getTipo().equalsIgnoreCase("enemigoactivo")) {
        // le atacamos y le quitamos salud dependiendo de la fuerza del jugador y de la defensa del
        // enemigo
        dano = Math.abs((this.getFuerza() / 10) - (enemigo.getDefensa() / 10));
        // le quitamos salud al enemigo
        System.out.println("salud enemigo antes de ataque:" + enemigo.getSalud());
        enemigo.setSalud(enemigo.getSalud() - dano);
        System.out.println("salud enemigo tras ataque:" + enemigo.getSalud());
        // se o enemigo e pasivo e lle queda saúde responderanos á súa vez con un ataque
        if (enemigo.getTipo().equalsIgnoreCase("enemigopasivo") && enemigo.getSalud() > 0) {
          // nos quita salud a nosotros
          int danoRecibido = Math.abs((this.getEnergia() / 10) - (enemigo.getEnergia() / 10));
          System.out.println("salud jugador antes de ataque:" + this.getSalud());
          this.setSalud(this.getSalud() - danoRecibido);
          System.out.println("salud jugador tras ataque:" + this.getSalud());
          if (this.getSalud() <= 0) {
            System.out.println("nos matan, quedamos sin salud");
          }
        } else if (enemigo.getSalud() == 0) {
          // como non lle queda saúde e que o matamos
          this.gestionCadaver(enemigo, celda);
        }
      }
    } else {
      System.out.println("No existe el enemigo en la celda: " + nombreEnemigo);
    }
    return dano;
  }

  public void gestionCadaver(Npcs enemigo, Celda celda) {
    System.out.println("Has matado al pesonaje secundario" + enemigo.getNombre());
    // miramos se ten obxetos e deixámolos na celda na que estaba
    if (!enemigo.getInventario().getObjetos().isEmpty()) {
      for (Objeto obj : enemigo.getInventario().getObjetos()) {
        celda.setObjetos(obj);
      }
    }
    // eliminamos a este personaje secundario de la celda en la que estaba
    celda.getNpcs().remove(enemigo);
  }

  public void recibimosAtaqueNpcsActivo(Npcs enemigo, Celda celda) {
    System.out.println("---somos atacados por:" + enemigo.getNombre());
    System.out.println("salud antes de ataque: " + this.getSalud());
    // nos quita salud a nosotros
    int danoRecibido = Math.abs((this.getEnergia() / 10) - (enemigo.getEnergia() / 10));
    this.setSalud(this.getSalud() - danoRecibido);
    System.out.println("salud tras ataque: " + this.getSalud());
    if (this.getSalud() <= 0) {
      System.out.println("nos matan, quedamos sin salud");
    }
  }
}
