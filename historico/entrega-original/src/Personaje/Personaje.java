/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import Mapa_e_partida.Celda;
import Mapa_e_partida.Mapa;
import Utilidades.ConsolaNormal;
import excepciones.ComandoExcepcion;
import excepciones.ExcepcionMover;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author miguel.alonso
 */
public class Personaje {
  private int vida;
  private int vidaMaxima;
  protected int energiaMaxima;
  protected int salud;
  private int saludMaxima;
  protected String nombre;
  protected int energia;
  protected Mochila mochila;
  protected Point posicion;
  private ArrayList<Point> rutaRecorrida;
  protected int fuerza;
  private int fuerzaMaxima;
  private ArrayList<Point> posicion2;
  protected String tipo;
  protected int defensa;
  protected Mapa mapa;
  protected ConsolaNormal consola = new ConsolaNormal();

  public Personaje(String nombre) {
    this.setNombre(nombre);
    this.setEnergiaMaxima(100);
    this.setFuerzaMaxima(100);
    this.setVidaMaxima(100);
    posicion2 = new ArrayList<>();
    rutaRecorrida = new ArrayList<>();
  }

  public Personaje(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    this(nombre);
    this.vida = vida;
    this.energia = energia;
    this.mochila = mochila;
    this.posicion = posicion;
  }

  public Personaje(int vida, String nombre, int energia, Mochila mochila) {
    this(nombre);
    this.setVida(vida);
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
    if (salud < 0) salud = 0;
    if (salud > 100) {
      consola.imprimir("La salud no puede ser mayor que 100");
    } else if (salud >= 0 && salud < 101) {
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

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public Mapa getMapa() {
    return mapa;
  }

  public void setMapa(Mapa mapa) {
    this.mapa = mapa;
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

  public int getDefensa() {
    return defensa;
  }

  public void setDefensa(int defensa) {
    this.defensa = defensa;
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
      consola.imprimir("La vida no puede ser mayor que " + this.vidaMaxima);
    } else {
      this.vida = 0;
      consola.imprimir("Has muerto!!!");
    }
  }

  public void setNombre(String nombre) {
    if (nombre.length() < 100) {
      this.nombre = nombre;
    } else {
      consola.imprimir("El nombre no puede ser tan largo");
    }
  }

  public void setEnergia(int energia) {
    if (energia > 0 && energia <= this.energiaMaxima) {
      this.energia = energia;
    } else if (energia > 100) {
      consola.imprimir("La energia no puede ser mayor que " + this.energiaMaxima);
    } else {
      this.energia = 0;
      consola.imprimir("Has agotado tu energia! usa una pocion o no podras atacar ni moverte!");
    }
  }

  public void setFuerza(int fuerza) {
    if (fuerza > 0 && fuerza <= this.fuerzaMaxima) {
      this.fuerza = fuerza;
    } else {
      consola.imprimir("La fuerza debe valer entre 1 y " + this.fuerzaMaxima);
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
   * @param comando
   * @return
   * @throws excepciones.ExcepcionMover
   * @throws excepciones.ComandoExcepcion
   */
  public void mover(Mapa mapa, String comando) throws ExcepcionMover, ComandoExcepcion, Exception {
    String direccion = comando;
    if (direccion.split(" ").length != 2) {
      throw new ComandoExcepcion("comando con numero de argumentos incorrectos");
    } else {
      direccion = direccion.split(" ")[1];
    }
    if (!direccion.equalsIgnoreCase("norte")
        && !direccion.equalsIgnoreCase("sur")
        && !direccion.equalsIgnoreCase("este")
        && !direccion.equalsIgnoreCase("oeste")) {
      throw new ComandoExcepcion("comando descoñecido");
    }
    // collemos a posición actual do personaxe
    int x = this.getPosicion().x;
    int y = this.getPosicion().y;

    // coa x movemonos en vertical e o coa y en horizontal
    if (x == 0 && "norte".equalsIgnoreCase(direccion))
      throw new ExcepcionMover("sales do mapa polo norte");
    else if (y == 0 && "oeste".equalsIgnoreCase(direccion))
      throw new ExcepcionMover("sales do mapa polo oeste");
    else if (y == (mapa.getMapaTamHorizonal() - 1) && "este".equalsIgnoreCase(direccion))
      throw new ExcepcionMover("sales do mapa polo este");
    else if (x == (mapa.getMapaTamVertical() - 1) && "sur".equalsIgnoreCase(direccion))
      throw new ExcepcionMover("sales do mapa polo sur");
    else {
      if ("norte".equalsIgnoreCase(direccion)) x--;
      else if ("sur".equalsIgnoreCase(direccion)) x++;
      else if ("oeste".equalsIgnoreCase(direccion)) y--;
      else if ("este".equalsIgnoreCase(direccion)) y++;

      // intentamos movernos a la nueva celda
      Celda celda = mapa.getCelda(new Point(x, y));
      if (celda.isTransitable()) {
        // como é una celda transitable situamonos nela
        this.setPosicion(celda.getPosicionMapa());

        // restamos enerxia en cada movimento
        if (this.getEnergia() >= this.calculaGastoEnerxia()) {
          consola.imprimir("posicion:" + this.getPosicion());
          this.setEnergia(this.getEnergia() - this.calculaGastoEnerxia());
        } else {
          throw new ExcepcionMover("Non tes suficiente enerxia para moverte!");
        }

        // si al entrar a una celda hay un personaje que nos pueda atacar
        NPC npc = mapa.getCelda(this.getPosicion()).getNPC();
        if (npc != null) {
          // comprobamos que sea un enemigo activo, que es el que puede atacarnos
          if (npc instanceof Activo) {
            npc.atacar(this);
          }
        }

        // añadimos la posiciona a la ruta recorrida
        this.setRutaRecorrida(this.getPosicion());
        NPC enemigoActivo = hayEnemigoActivo(celda);
        if (enemigoActivo != null) {
          this.recibimosAtaqueNpcsActivo(enemigoActivo, celda);
        }
      } else {
        throw new ExcepcionMover("celda non transitable:" + x + "," + y);
      }
    }
    consola.imprimir("///Posicion jugador:" + this.getPosicionString());
  }

  public String coger(Objeto objetoACoger, Celda celda) throws Exception {
    String retorno = "sin objeto";

    boolean bExiste = false;
    if (celda.getObjetos() != null) {
      for (Objeto objeto : celda.getObjetos()) {
        // comprobamos que el objeto que quiere coger sea el pedido
        if (objetoACoger.getNombre().equalsIgnoreCase(objeto.getNombre())) {
          bExiste = true;
          // si no excede el número de objetos en la mochila su capacidad continuamos
          if (this.mochila.getObjetos().size() < this.mochila.getCapacidad()) {
            if (objeto.getTipo_objeto().equalsIgnoreCase("arma")
                && this.mochila.tieneTipoObjeto("arma")) {
              // consola.imprimir("solo puedes tener un arma");
              throw new Exception("sólo puedes coger un arma");
            } else {
              // ahora comprobamos que no se exceda el peso maximo en la mochila
              if (this.getMochila().getPesoMax()
                  >= this.getMochila().getPesoActual() + objeto.getPeso()) {
                this.mochila.setObjetos(objeto);
                retorno = "agregado";
                // lo quitamos de la celda
                celda.getObjetos().remove(objeto);
                // salimos porque solo puede coger un objeto
                break;
              } else {
                // retorno = "con el objeto encontrado excedemos el peso máximo de la mochila";
                throw new Exception(
                    "con el objeto encontrado excedemos el peso máximo de la mochila");
              }
            }
          } else {
            throw new Exception("la mochila ya esta al limite de capacidad");
          }
        }
      }
      if (!bExiste) {
        // retorno = "no existe el objeto que quieres coger:" + nombreObjeto;
        throw new Exception("no existe el objeto que quieres coger:" + objetoACoger.getNombre());
      }
      consola.imprimir(
          "cogido:"
              + objetoACoger.getNombre()
              + ", en celda:"
              + celda.getPosicionMapa().toString());
    }
    return retorno;
  }

  public String tirar(Objeto objetoACoger, Celda celda) throws Exception {
    String retorno = "no hay objetos en la mochila";
    boolean bExiste = false;
    if (this.mochila.getObjetos().size() > 0) {
      Objeto objetoABorrar = null;
      for (Objeto objeto : this.mochila.getObjetos()) {
        if (objetoACoger.getNombre().equalsIgnoreCase(objeto.getNombre())) {
          bExiste = true;
          // dejamos el objeto en la celda que estamos
          celda.setObjetos(objeto);
          objetoABorrar = objeto;
          retorno = "tirado objeto: " + objeto.toString() + "\n";
        }
      }
      if (!bExiste) {
        // retorno = "no existe el objeto que quieres tirar:" + nombreObjeto;
        throw new Exception("no existe el objeto que quieres coger:" + objetoACoger.getNombre());
      }

      if (objetoABorrar != null) {
        // borramos el objeto de la mochila
        this.mochila.getObjetos().remove(objetoABorrar);
      }
    }
    consola.imprimir(
        "tirado:" + objetoACoger.getNombre() + ", en celda:" + celda.getPosicionMapa().toString());
    return retorno;
  }

  public NPC hayEnemigoActivo(Celda celda) {
    NPC npcsEnemigo = null;
    // comprombamos que ese inimigo está na celda
    ArrayList<NPC> personajesCelda = celda.getNpcs();
    for (NPC npcs : personajesCelda) {
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
      consola.imprimir(cadena);
    }
  }

  public void mirar(Mapa mapa) {
    Celda celda = mapa.getCelda(this.getPosicionActual());
    if (celda.getObjetos().isEmpty()) {
      consola.imprimir("Non hai obxetos na celda");
    } else {
      consola.imprimir("Tipo obxeto:" + celda.getObjetos().get(0).getTipo());
    }
  }

  public int calculaGastoEnerxia() {
    int gasto = 1;
    if (mochila.calculaPeso() > 0) {
      gasto = gasto + (int) ((mochila.calculaPeso() / 7) + 0.999);
    }
    return gasto;
  }

  public void cogerMochila(Point coordenadas, Mapa mapa) throws Exception {

    ArrayList<Objeto> objetosCelda = mapa.getCelda(coordenadas).getObjetos();
    boolean bEncontrado = false;
    // miramos si está la mochila en la celda
    for (Objeto objeto : objetosCelda) {
      if (objeto.getNombre().equalsIgnoreCase("mochila")) {
        bEncontrado = true;
        // la capacidad de la mochila en el objeto está en la propiedad efecto
        Mochila mochila = new Mochila("mochila", objeto.getEfecto());
        mochila.setPesoMax(objetosCelda.get(0).getPeso());
        mochila.setCapacidad(5);
        mochila.setBolsillos(9);
        this.setMochila(mochila);

        // la quitamos de la celda
        mapa.getCelda(coordenadas).getObjetos().remove(objeto);
        break;
      }
    }
    if (!bEncontrado) {
      throw new Exception("no hay mochila en esta celda");
    }
    consola.imprimir("agregada mochila al personaje");
  }

  public void atacar(Personaje personaje) throws Exception {
    int dano = 0;
    // comprobamos que tenemos mapa
    if (this.mapa == null) {
      throw new ComandoExcepcion("no tenemos mapa para comprobar si en la celda hay algún NPC");
    }
    // miramos si hay ese personaje en la celda en la que está el jugador
    NPC npcs = this.mapa.getCelda(this.getPosicion()).personajeNombre(personaje.getNombre());
    if (npcs == null) {
      throw new ComandoExcepcion(
          "no hay ningun personaje con ese nombre en la celda:" + personaje.getNombre());
    }

    // Hai personaje NPC procedemos a calcular cantos puntos de dano fai o inimigo (en funcion da
    // sua enerxia e forza e da defensa do xogador)
    float fuerzaCoef = (this.getFuerza() / 10);
    int energiaUsada;
    // if (npcs.getTipo().equalsIgnoreCase("enemigopasivo") ||
    // npcs.getTipo().equalsIgnoreCase("enemigoactivo")) {
    if ((npcs instanceof Pasivo) || (npcs instanceof Activo)) {
      // le atacamos y le quitamos salud dependiendo de la fuerza del jugador y de la defensa del
      // npcs
      dano = Math.abs((this.getFuerza() / 10) - (npcs.getDefensa() / 10));
      // le quitamos salud al npcs
      consola.imprimir("salud npcs antes de ataque:" + npcs.getSalud());
      npcs.setSalud(npcs.getSalud() - dano);
      consola.imprimir("salud npcs tras ataque:" + npcs.getSalud());
      // se o npcs e pasivo e lle queda saúde responderanos á súa vez con un ataque
      // if(npcs.getTipo().equalsIgnoreCase("enemigopasivo") && npcs.getSalud()>0){
      // se é un enemigo pasivo e ten saúde
      if ((npcs instanceof Pasivo) && npcs.getSalud() > 0) {
        // nos quita salud a nosotros
        int danoRecibido = Math.abs((this.getEnergia() / 10) - (npcs.getEnergia() / 10));
        consola.imprimir("salud jugador antes de ataque:" + this.getSalud());
        this.setSalud(this.getSalud() - danoRecibido);
        consola.imprimir("salud jugador tras ataque:" + this.getSalud());
        if (this.getSalud() <= 0) {
          consola.imprimir("nos matan, quedamos sin salud");
        }
      }
      // si no tiene salud gestionamos su cadaver
      if (npcs.getSalud() <= 0) {
        gestionCadaver((NPC) npcs, this.mapa.getCelda(npcs.getPosicion()));
      }
    }
  }

  public void gestionCadaver(NPC npcs, Celda celda) {
    consola.imprimir("Has matado al pesonaje secundario: " + npcs.getNombre());
    // miramos se ten obxetos e deixámolos na celda na que estaba
    if (!npcs.getInventario().getObjetos().isEmpty()) {
      for (Objeto obj : npcs.getInventario().getObjetos()) {
        celda.setObjetos(obj);
      }
    }
    // eliminamos a este personaje secundario de la celda en la que estaba
    celda.getNpcs().remove(npcs);
  }

  public void recibimosAtaqueNpcsActivo(NPC enemigo, Celda celda) {
    consola.imprimir("---somos atacados por:" + enemigo.getNombre());
    consola.imprimir("salud antes de ataque: " + this.getSalud());
    // nos quita salud a nosotros
    int danoRecibido = Math.abs((this.getEnergia() / 10) - (enemigo.getEnergia() / 10));
    this.setSalud(this.getSalud() - danoRecibido);
    consola.imprimir("salud tras ataque: " + this.getSalud());
    if (this.getSalud() <= 0) {
      consola.imprimir("nos matan, quedamos sin salud");
    }
  }
}
