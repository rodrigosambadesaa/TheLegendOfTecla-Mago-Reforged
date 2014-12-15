/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa_e_partida;

/**
 * @author Miguel Alonso Castro, Rodrigo Sambade Saa
 */
import static Utilidades.CONST.TAM_HORIZONTAL;
import static Utilidades.CONST.TAM_VERTICAL;

import Personaje.Jugador;
import Personaje.NPC;
import Personaje.Objeto;
import Personaje.Personaje;
import Utilidades.CONST;
import Utilidades.ConsolaNormal;
import Utilidades.Util;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Mapa extends Objeto {

  private String nombre;
  private HashMap<Point, Celda> celdas = new HashMap<>();
  private Personaje jugador;
  private String descripcion;
  private Personaje jugadorAutomatico;
  private int mapaTamHorizonal;
  private int mapaTamVertical;
  ArrayList<Personaje> personajes_secundarios;
  ArrayList<Objeto> objetos;
  ConsolaNormal consola = new ConsolaNormal();

  public Mapa() {
    // rellemaHashMap();
  }

  public Mapa(Personaje jugador) {
    this.setJugador(jugador);
    rellemaHashMap();
  }

  public Mapa(Mapa copia) {
    this.nombre = copia.getNombre();
    this.descripcion = copia.getDescripcion();
    this.celdas = new HashMap<>(copia.celdas);
    this.jugador = new Personaje(copia.jugador);
  }

  public void setJugador(Personaje jugador) {
    Personaje copia = new Personaje(jugador);
    this.jugador = copia;
  }

  public Mapa(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void rellemaHashMap() {
    int contTipo = 0;
    String valor = "";
    ArrayList objetos = null;
    String tipoPasillo = "";
    this.setMapaTamHorizonal(CONST.TAM_HORIZONTAL);
    this.setMapaTamVertical(CONST.TAM_VERTICAL);
    for (int i = 0; i < CONST.TAM_HORIZONTAL; i++) {
      for (int j = 0; j < CONST.TAM_VERTICAL; j++) {
        tipoPasillo = "pasillo normal";
        if ((i + j) % 5 == 0) {
          tipoPasillo = "pasillo estrecho";
        }
        Celda celda = new Celda(tipoPasillo, new ArrayList(), true);
        if ((i + j) % 9 == 0 && i % 2 == 0) {
          celda.setTransitable(false);
        }
        // solo poñemos obxeto se a celda é transitable
        if (celda.isTransitable()) {
          // aleatorio, solo obxetos en algunha celdas
          if (j % 10 == 0) {
            int efecto = 0;
            double peso = 4;
            String tipoObj = "";
            while (peso > 2) {
              peso = peso / 2;
            }
            if (contTipo == 0) {
              tipoObj = "pocion de salud";
            }
            efecto = 10;
            if (contTipo == 1) {
              tipoObj = "pocion de energía";
            }
            efecto = 5;
            if (contTipo == 2) {
              tipoObj = "veneno";
            }
            efecto = -5;
            if (contTipo == 3) {
              tipoObj = "mapa";
            }
            efecto = 0;

            Objeto objeto = new Objeto("obxeto_" + i + j, peso, tipoObj, efecto);
            contTipo++;
            // reiniciamos conTipo ao chegar a 3 para que volva a poñer os tipos de pocion dede o 0
            if (contTipo == 3) {
              contTipo = 0;
            }
            objetos = new ArrayList();
            objetos.add(objeto);
            celda.setObjetos(objetos);
          }
        }
        // consola.imprimir("celda:" +  i + "," + j + ": " + celda.toString());
        Point p = new Point(i, j);
        celda.setPosicionMapa(p);
        celdas.put(p, celda);
      }
    }

    // colocamos una mochila en la celda 0,0
    Objeto objeto = new Objeto("mochila", 40, "mochila", 5);
    Point pto = new Point(0, 0);
    objeto.setPosicionMapa(pto);
    objeto.setTipo_objeto("mochila");
    objeto.setDescripcion("tu mochila");
    objeto.setPoseedor("jugador");
    objetos.add(objeto);
    this.getCelda(pto).setObjetos(objeto);
    if (!objetos.isEmpty()) {
      this.objetos = objetos;
    }
  }

  public void rellenaHashMap(String ruta) {
    try {
      this.setMapaTamHorizonal(CONST.TAM_HORIZONTAL);
      this.setMapaTamVertical(CONST.TAM_VERTICAL);
      // inicializamos celdas do mapa
      for (int i = 0; i < TAM_HORIZONTAL; i++) {
        for (int j = 0; j < TAM_VERTICAL; j++) {
          Celda celda = new Celda();
          celda.setTransitable(false);
          Point pto = new Point(i, j);
          celda.setPosicionMapa(pto);
          celdas.put(pto, celda);
        }
      }

      String fichDatosMapa = ruta + "mapa.csv";
      String fichNPCS = ruta + "npcs.csv";
      String fichObjetos = ruta + "objetos.csv";
      // si el fichero no existe mostramos mensaje y no leemos los ficheros
      if (!(new File(fichDatosMapa)).exists()) {
        consola.imprimir("La ruta es errónea cargamos ficheros del directorio raíz");
        fichDatosMapa = "mapa.csv";
        fichNPCS = "npcs.csv";
        fichObjetos = "objetos.csv";
      }

      // rellenamos las celdas del mapa con la informacion proveniente del fichero mapa.csv
      ArrayList<Celda> celdas_fichero = Util.leerDatosMapa(fichDatosMapa);
      for (Celda celda : celdas_fichero) {
        celdas.put(celda.getPosicionMapa(), celda);
      }

      // Colocamos en la celda correspondiente a cada personaje
      personajes_secundarios = Util.leerDatosPersonajes(fichNPCS);
      for (Personaje personaje : personajes_secundarios) {
        // buscamos la celda en la que hay que situar el Personaje Secundario
        if (personaje instanceof NPC) {
          NPC npcs = (NPC) personaje;
          Celda celda = celdas.get(npcs.getPosicion());
          celda.setNpcs(npcs);
          celdas.put(npcs.getPosicion(), celda);
        } else if (personaje instanceof Jugador) {
          jugadorAutomatico = (Jugador) personaje;
        }
      }

      // Colocamos cada objeto en su celda
      ArrayList<Objeto> objetos = Util.leerDatosObjetos(fichObjetos);
      this.objetos = objetos;
      for (Objeto objeto : objetos) {
        // buscamos la celda en la que hay que situar el Objeto
        Celda celda = celdas.get(objeto.getPosicionMapa());
        celda.setObjetos(objeto);
      }

    } catch (Exception e) {
      consola.imprimir(e.toString());
    }
  }

  public String mirarCelda(Point coordenadas) {
    String retorno = "No hay objetos en la celda=" + coordenadas.toString();
    Celda celda = this.getCelda(coordenadas);
    if (celda.getObjetos() != null && celda.getObjetos().size() > 0) {
      retorno = "";
      for (Objeto objeto : celda.getObjetos()) {
        retorno += "\n" + objeto.toString();
      }
    }
    return retorno;
  }

  public String mirarObjetoCelda(Point coordenadas, String obj) {

    String retorno = "No hay ese objeto en la celda";
    Celda celda = this.getCelda(coordenadas);
    if (celda.getObjetos() != null && celda.getObjetos().size() > 0) {
      retorno = "";
      for (Objeto objeto : celda.getObjetos()) {
        if (objeto.getNombre().equalsIgnoreCase(obj)) {
          retorno += "\n" + objeto.toString();
          break;
        }
      }
    }
    return retorno;
  }

  @Override
  public void usar(Personaje personaje) {
    consola.imprimir("entrada");
    int x = personaje.getPosicion().x;
    int y = personaje.getPosicion().y;
    String retorno = "\"---------------Mapa do xogo------------------------\n";
    retorno +=
        CONST.CARACTER_TRANSITABLE
            + ": celda transitable, "
            + CONST.CARACTER_NO_TRANSITABLE
            + ": celda intransitable, "
            + CONST.CARACTER_OBJETO
            + ": celda transitable que conten obxeto\n";
    for (int i = 0; i < TAM_HORIZONTAL; i++) {
      for (int j = 0; j < TAM_VERTICAL; j++) {
        Celda celda = celdas.get(new Point(i, j));
        if (x == i && y == j) {
          retorno += CONST.CARACTER_POSICION_ACTUAL;
        } else if (celda.isTransitable()) {
          if (celda.getObjetos() != null && !celda.getObjetos().isEmpty()) {
            retorno += CONST.CARACTER_OBJETO;
          } else {
            retorno += CONST.CARACTER_TRANSITABLE;
          }
        } else {
          retorno += CONST.CARACTER_NO_TRANSITABLE;
        }
      }
      retorno += "\n";
    }
    consola.imprimir(retorno);
  }

  public String pintarMapaParcial(Celda celda) {
    String mapaParcial = "";

    int x = celda.getPosicionMapa().x;
    int y = celda.getPosicionMapa().y;
    mapaParcial = "posicion actual: " + celda.getPosicionMapa() + "\n";
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (i >= 0 && j >= 0) {
          Celda celda_adyacente = celdas.get(new Point(i, j));
          if (x == i && y == j) {
            mapaParcial += CONST.CARACTER_POSICION_ACTUAL;
          } else if (celda_adyacente.isTransitable()) {
            if (celda_adyacente.getObjetos() != null && !celda_adyacente.getObjetos().isEmpty()) {
              mapaParcial += CONST.CARACTER_OBJETO;
            } else {
              mapaParcial += CONST.CARACTER_TRANSITABLE;
            }
          } else {
            mapaParcial += CONST.CARACTER_NO_TRANSITABLE;
          }
        }
      }
      mapaParcial += "\n";
    }
    return mapaParcial;
  }

  public String pintarMapaParcial2(Celda celda, ArrayList<Point> puntosDescubiertos) {
    String mapaParcial = "";

    int x = celda.getPosicionMapa().x;
    int y = celda.getPosicionMapa().y;
    mapaParcial = "posicion actual: " + celda.getPosicionMapa() + "\n";
    for (int i = 0; i < TAM_HORIZONTAL; i++) {
      for (int j = 0; j < TAM_VERTICAL; j++) {
        Celda celda_act = celdas.get(new Point(i, j));
        // si es una celda descubierta pintamos el caracter que le corresponda
        if (puntosDescubiertos.contains(celda_act.getPosicionMapa())) {
          if (x == i && y == j) {
            mapaParcial += CONST.CARACTER_POSICION_ACTUAL;
          } else if (celda_act.isTransitable()) {
            if (celda_act.getObjetos() != null && !celda_act.getObjetos().isEmpty()) {
              mapaParcial += CONST.CARACTER_OBJETO;
            } else {
              mapaParcial += CONST.CARACTER_TRANSITABLE;
            }
          } else {
            mapaParcial += CONST.CARACTER_NO_TRANSITABLE;
          }
        } else {
          // no es una celda descubierta -> pintamos un interrogante
          mapaParcial += CONST.CARACTER_INTERROGANTE;
        }
      }
      mapaParcial += "\n";
    }
    return mapaParcial;
  }

  public Celda getCelda(Point coordenadas) {
    return this.celdas.get(coordenadas);
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public HashMap<Point, Celda> getCeldas() {
    return celdas;
  }

  public void setCeldas(HashMap<Point, Celda> celdas) {
    this.celdas = celdas;
  }

  public Personaje getJugadorAutomatico() {
    return jugadorAutomatico;
  }

  public void setJugadorAutomatico(Personaje jugadorAutomatico) {
    this.jugadorAutomatico = jugadorAutomatico;
  }

  public ArrayList<Personaje> getPersonajes_secundarios() {
    return personajes_secundarios;
  }

  public void setPersonajes_secundarios(ArrayList<Personaje> personajes_secundarios) {
    this.personajes_secundarios = personajes_secundarios;
  }

  public ArrayList<Objeto> getObjetos() {
    return objetos;
  }

  public void setObjetos(ArrayList<Objeto> objetos) {
    this.objetos = objetos;
  }

  public int getMapaTamHorizonal() {
    return mapaTamHorizonal;
  }

  public void setMapaTamHorizonal(int mapaTamHorizonal) {
    this.mapaTamHorizonal = mapaTamHorizonal;
  }

  public int getMapaTamVertical() {
    return mapaTamVertical;
  }

  public void setMapaTamVertical(int mapaTamVertical) {
    this.mapaTamVertical = mapaTamVertical;
  }
}
