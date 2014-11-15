package Personaje;

import Mapa_e_partida.Mapa;
import java.awt.Point;
import java.util.Random;

// En esta clase no se permite el aliasing. Falta limitar los valores de los setters.
// Ojito con estas dos cosas al meter nuevos atributos.
// Tambien tengo que revisar los constructores, que les faltan casi todos los atributos.
public class Npcs {

  private Point posicionMapa;
  private String nombre;
  private String tipo;
  private int salud;
  private int saludMaxima;
  private int energia;
  private int energiaMaxima;
  private int fuerza;
  private int fuerzaMaxima;
  private int defensa;
  private int probabilidadCritico;
  private String respuesta;
  private boolean activo;
  private Mochila inventario;
  private Mapa mapa;
  private boolean amigable;

  // constructores
  public Npcs() {
    this.posicionMapa = new Point(posicionMapa);
    this.inventario = new Mochila();
  }

  public Npcs(
      Point posicionMapa,
      String nombre,
      String tipo,
      int salud,
      int energia,
      int fuerza,
      int defensa,
      String respuesta) {
    this.posicionMapa = new Point(posicionMapa);
    this.nombre = nombre;
    this.tipo = tipo;
    this.salud = salud;
    this.energia = energia;
    this.fuerza = fuerza;
    this.defensa = defensa;
    this.respuesta = respuesta;
    this.inventario = new Mochila();
  }

  public Npcs(
      String posicionMapa,
      String nombre,
      String tipo,
      int salud,
      int energia,
      int fuerza,
      int defensa,
      String respuesta) {
    Integer x;
    Integer y;
    String coordenadas[];

    coordenadas = posicionMapa.split(",");
    x = Integer.parseInt(coordenadas[0]);
    y = Integer.parseInt(coordenadas[1]);
    Point punto = new Point(x, y);
    this.posicionMapa = punto; // conversion string a punto
    this.nombre = nombre;
    this.tipo = tipo;
    this.salud = salud;
    this.energia = energia;
    this.fuerza = fuerza;
    this.defensa = defensa;
    this.respuesta = respuesta;
  }

  // copy constructor
  public Npcs(Npcs copia) {
    this.posicionMapa = copia.getPosicionMapa();
    this.nombre = copia.getNombre();
    this.tipo = copia.getTipo();
    this.salud = copia.getSalud();
    this.saludMaxima = copia.getSaludMaxima();
    this.energia = copia.energiaMaxima;
    this.energiaMaxima = copia.getEnergiaMaxima();
    this.fuerza = copia.getFuerza();
    this.fuerzaMaxima = copia.getFuerzaMaxima();
    this.defensa = copia.getDefensa();
    this.probabilidadCritico = copia.getProbabilidadCritico();
    this.respuesta = copia.getRespuesta();
    this.activo = copia.isActivo();
    this.inventario = copia.getInventario();
    this.mapa = copia.getMapa();
    this.amigable = copia.isAmigable();
  }

  // getters

  public Point getPosicionMapa() {
    Point copia = new Point(this.posicionMapa);
    return copia;
  }

  public String getNombre() {
    return nombre;
  }

  public String getTipo() {
    return tipo;
  }

  public int getSalud() {
    return salud;
  }

  public int getSaludMaxima() {
    return saludMaxima;
  }

  public int getEnergia() {
    return energia;
  }

  public int getEnergiaMaxima() {
    return energiaMaxima;
  }

  public int getFuerza() {
    return fuerza;
  }

  public int getFuerzaMaxima() {
    return fuerzaMaxima;
  }

  public int getDefensa() {
    return defensa;
  }

  public int getProbabilidadCritico() {
    return probabilidadCritico;
  }

  public String getRespuesta() {
    return respuesta;
  }

  public boolean isActivo() {
    return activo;
  }

  public Mochila getInventario() {
    Mochila copia = new Mochila(this.inventario);
    return copia;
  }

  public Mapa getMapa() {
    Mapa copia = new Mapa(this.mapa);
    return copia;
  }

  public boolean isAmigable() {
    return this.amigable;
  }

  // setters
  public void setPosicionMapa(Point posicionMapa) {
    Point copia = new Point(posicionMapa);
    this.posicionMapa = copia;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public void setSalud(int salud) {
    if (salud > 100) {
      System.out.println("La salud no puede ser mayor que 100");
    } else if (salud > 0 && salud < 101) {
      this.salud = salud;
    } else {
      this.gestionCadaver();
      this.salud = 0;
      this.activo = false;
    }
  }

  public void setSaludMaxima(int saludMaxima) {
    this.saludMaxima = saludMaxima;
  }

  public void setEnergia(int energia) {
    if (energia > 0 && energia < 101) {
      this.energia = energia;
    } else if (energia > 100) {
      System.out.println("La energia no puede ser mayor que 100");
    } else {
      this.energia = 0;
      System.out.println(this.nombre + " ha agotado su energia");
    }
  }

  public void setEnergiaMaxima(int energiaMaxima) {
    this.energiaMaxima = energiaMaxima;
  }

  public void setFuerza(int fuerza) {
    this.fuerza = fuerza;
  }

  public void setFuerzaMaxima(int fuerzaMaxima) {
    this.fuerzaMaxima = fuerzaMaxima;
  }

  public void setDefensa(int defensa) {
    this.defensa = defensa;
  }

  public void setProbabilidadCritico(int probabilidadCritico) {
    this.probabilidadCritico = probabilidadCritico;
  }

  public void setRespuesta(String respuesta) {
    this.respuesta = respuesta;
  }

  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public void setInventario(Mochila objetos) {
    if (objetos != null) {
      this.inventario = new Mochila(objetos);
    }
  }

  public void setAmigable(boolean amigable) {
    this.amigable = amigable;
  }

  // metodos
  public int atacar(Personaje jugador) {
    int daño = 0;

    // calcular cantos puntos de daño fai o inimigo (en funcion da sua enerxia e forza e da defensa
    // do xogador)
    float fuerzaCoef = (this.getFuerza() / 10);
    int energiaUsada;

    // se o inimigo non ten enerxia non ataca
    if (this.energia > 0) {
      if (this.energia > ((int) this.energiaMaxima / 10)) {
        energiaUsada = (int) this.energiaMaxima / 10;
      } else {
        energiaUsada = this.energia;
      }

      // calculo de impacto critico
      Random aleatorio = new Random();
      int tirada = aleatorio.nextInt(99);
      int critico = 4;
      if (tirada >= (100 - this.probabilidadCritico)) {
        System.out.println(this.nombre + " ha hecho un impacto critico!");
        critico = 2;
      }

      // funcion para calcular o dano
      daño =
          ((int)
              ((fuerzaCoef * energiaUsada)
                  / critico)); // en caso de retirar o critico, sustituilo aqui por 4

      // restar vida ao xogador
      jugador.setVida(jugador.getVida() - daño);

      // restar enerxia ao npc
      this.setEnergia(this.getEnergia() - energiaUsada);

      return daño;
    } else {
      System.out.println(this.nombre + " no tiene suficiente energia para atacar");
      return daño;
    }
  }

  public void gestionCadaver() {
    System.out.println("Has matado a " + this.nombre);
    this.mapa.getCelda(this.posicionMapa).ponerObjetos(this.inventario.getObjetos());
    this.inventario.vaciaMochila();
  }

  @Override
  public String toString() {
    return "Npcs{"
        + "posicionMapa="
        + posicionMapa
        + ", nombre="
        + nombre
        + ", tipo="
        + tipo
        + ", salud="
        + salud
        + ", energia="
        + energia
        + ", fuerza="
        + fuerza
        + ", defensa="
        + defensa
        + ", respuesta="
        + respuesta
        + '}';
  }
}
