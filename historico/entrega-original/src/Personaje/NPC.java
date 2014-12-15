package Personaje;

import Mapa_e_partida.Mapa;
import java.awt.Point;
import java.util.ArrayList;

// En esta clase no se permite el aliasing. Falta limitar los valores de los setters.
// Ojito con estas dos cosas al meter nuevos atributos.
// Tambien tengo que revisar los constructores, que les faltan casi todos los atributos.
public class NPC extends Personaje {

  protected int probabilidadCritico;
  private String respuesta;
  private boolean activo;
  private Mochila inventario;
  private Mapa mapa;
  private boolean amigable;

  public NPC(String nombre) {
    super(nombre);
    this.crearMochila();
  }

  public NPC(int vida, String nombre, int energia, Mochila mochila, Point posicion) {
    super(vida, nombre, energia, mochila, posicion);
    this.crearMochila();
  }

  public NPC(int vida, String nombre, int energia, Mochila mochila) {
    super(vida, nombre, energia, mochila);
    this.crearMochila();
  }

  public NPC(Personaje copia) {
    super(copia);
    this.crearMochila();
  }

  private void crearMochila() {
    // le creamos una mochila con un array de objetos vacio
    Mochila mochila = new Mochila("", 5, 9);
    mochila.setObjetos(new ArrayList<Objeto>());
    this.setInventario(mochila);
  }

  // getters

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

  //    public void setNombre(String nombre) {
  //        this.nombre = nombre;
  //    }
  //
  //    public void setTipo(String tipo) {
  //        this.tipo = tipo;
  //    }
  //
  //    public void setSalud(int salud) {
  //        if (salud > 100) {
  //            consola.imprimir("La salud no puede ser mayor que 100");
  //        } else if (salud > 0 && salud < 101) {
  //            this.salud = salud;
  //        } else {
  //            this.gestionCadaver();
  //            this.salud = 0;
  //            this.activo = false;
  //        }
  //    }
  //
  //    public void setSaludMaxima(int saludMaxima) {
  //        this.saludMaxima = saludMaxima;
  //    }
  //
  //    public void setEnergia(int energia) {
  //        if (energia > 0 && energia < 101) {
  //            this.energia = energia;
  //        } else if (energia > 100) {
  //            consola.imprimir("La energia no puede ser mayor que 100");
  //        } else {
  //            this.energia = 0;
  //            consola.imprimir(this.nombre + " ha agotado su energia");
  //        }
  //    }
  //
  //    public void setEnergiaMaxima(int energiaMaxima) {
  //        this.energiaMaxima = energiaMaxima;
  //    }
  //
  //    public void setFuerza(int fuerza) {
  //        this.fuerza = fuerza;
  //    }
  //
  //    public void setFuerzaMaxima(int fuerzaMaxima) {
  //        this.fuerzaMaxima = fuerzaMaxima;
  //    }

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

  /**
   * método del personaje secundario que puede atacar al jugador
   *
   * @param personaje
   * @throws Exception
   */
  @Override
  public void atacar(Personaje personaje) throws Exception {

    if (!(personaje instanceof Jugador)) {
      throw new Exception("no es el jugador y no puede ser atacado");
    } else {
      if (personaje.getPosicion().toString().equals(this.getPosicion().toString())) {
        throw new Exception("no puedo atacar al jugador porque no estamos en la misma celda");
      }
    }
  }

  public void gestionCadaver() {
    consola.imprimir("+++(RIP) Has matado a " + this.nombre);
    this.mapa.getCelda(this.posicion).ponerObjetos(this.inventario.getObjetos());
    this.inventario.vaciaMochila();
  }

  @Override
  public String toString() {
    return "Npcs{"
        + "posicion="
        + posicion
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
