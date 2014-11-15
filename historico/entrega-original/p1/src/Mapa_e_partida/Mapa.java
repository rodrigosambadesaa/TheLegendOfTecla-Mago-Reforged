/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa_e_partida;

/**
 * @author Miguel Alonso Castro, Rodrigo Sambade Saa
 */
import Personaje.Npcs;
import Personaje.Objeto;
import Personaje.Personaje;
import Utilidades.Util;
import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Mapa {

  private static final String CARACTER_OBJETO = "0";
  private static final String CARACTER_TRANSITABLE = "-";
  private static final String CARACTER_NO_TRANSITABLE = "X";
  private static final char CARACTER_POSICION_ACTUAL = '\u263A';
  private static final String CARACTER_INTERROGANTE = "?";
  public static final int TAM_HORIZONTAL = 10;
  public static final int TAM_VERTICAL = 10;

  private String nombre;
  private HashMap<Point, Celda> celdas = new HashMap<>();
  private Personaje jugador = new Personaje();
  private String descripcion;

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

  private void rellemaHashMap() {
    int contTipo = 0;
    String valor = "";
    ArrayList objetos = null;
    String tipoPasillo = "";

    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 20; j++) {
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
        // System.out.println("celda:" +  i + "," + j + ": " + celda.toString());
        Point p = new Point(i, j);
        celdas.put(p, celda);
      }
    }
    this.imprimirMapa();
  }

  public void rellenaHashMap(String ruta) {
    //        int contTipo = 0;
    //        String valor = "";
    //        ArrayList objetos = null;
    //        String tipoPasillo = "";
    //
    //        for (int i = 0; i < 100; i++) {
    //            for (int j = 0; j < 100; j++) {
    //                tipoPasillo = "pasillo normal";
    //                if((i+j)%5 == 0)tipoPasillo="pasillo estrecho";
    //                Celda celda = new Celda(tipoPasillo,new ArrayList(),true);
    //                if((i+j)%9 == 0 && i%2==0)celda.setTransitable(false);
    //                //solo poñemos obxeto se a celda é transitable
    //                if(celda.isTransitable()){
    //                    //aleatorio, solo obxetos en algunha celdas
    //                    if(j%10==0){
    //                        int efecto = 0;
    //                        double peso = Math.abs(j-i);
    //                        String tipoObj="";
    //                        while(peso>2){
    //                            peso = peso/2;
    //                        }
    //                        if(contTipo==0) tipoObj="pocion de salud"; efecto = 10;
    //                        if(contTipo==1) tipoObj="pocion de energía"; efecto = 5;
    //                        if(contTipo==2) tipoObj="veneno"; efecto = -5;
    //                        if(contTipo==3) tipoObj="mapa"; efecto = 0;
    //
    //                        Objeto objeto = new Objeto("obxeto_"+i+j, peso,tipoObj,efecto);
    //                        contTipo++;
    //                        //reiniciamos conTipo ao chegar a 3 para que volva a poñer os tipos de
    // pocion dede o 0
    //                        if(contTipo==3)contTipo=0;
    //                        objetos = new ArrayList();
    //                        objetos.add(objeto);
    //                        celda.setObjetos(objetos);
    //                    }
    //                }
    //                //System.out.println("celda:" +  i + "," + j + ": " + celda.toString());
    //                celdas.put(i + "," + j, celda);
    //            }
    //        }
    try {
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
      // this.setCeldas(celdas);
      String fichDatosMapa = ruta + "mapa.csv";
      String fichNPCS = ruta + "npcs.csv";
      String fichObjetos = ruta + "objetos.csv";
      // si el fichero no existe mostramos mensaje y no leemos los ficheros
      if (!(new File(fichDatosMapa)).exists()) {
        System.out.println("La ruta es errónea cargamos ficheros del directorio raíz");
        fichDatosMapa = "mapa.csv";
        fichNPCS = "npcs.csv";
        fichObjetos = "objetos.csv";
      }

      // rellenamos las celdas del mapa con la informacion proveniente del fichero mapa.csv
      ArrayList<Celda> celdas_fichero = Util.leerDatosMapa(fichDatosMapa);
      for (Celda celda : celdas_fichero) {
        // celdas.put(celda.getPosicionMapa(), celda);
        celdas.put(celda.getPosicionMapa(), celda);
      }

      // Colocamos en la celda correspondiente a cada personaje
      ArrayList<Npcs> personajes_secundarios = Util.leerDatosPersonajes(fichNPCS);
      for (Npcs npcs : personajes_secundarios) {
        // buscamos la celda en la que hay que situar el Personaje Secundario
        Celda celda = celdas.get(npcs.getPosicionMapa());
        celda.setNpcs(npcs);
        celdas.put(npcs.getPosicionMapa(), celda);
      }

      // Colocamos cada objeto en su celda
      ArrayList<Objeto> objetos = Util.leerDatosObjetos(fichObjetos);
      for (Objeto objeto : objetos) {
        // buscamos la celda en la que hay que situar el Objeto
        Celda celda = celdas.get(objeto.getPosicionMapa());
        celda.setObjetos(objeto);
      }

    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  public String mirarCelda(Point coordenadas) {
    String retorno = "No hay objetos en la celda";
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

  public void imprimirMapa() {
    char transitable = '\u26F6';
    char noTransitable = 'X';
    char pasilloEstrecho = 'I';
    char caritaSonriente = '\u263A';

    Point actual = new Point();
    actual.setLocation(this.jugador.getPosicionActual());

    Point objetivo = new Point(18, 19);
    Point p = new Point(this.jugador.getPosicionActual());

    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 20; j++) {
        p.setLocation(i, j);

        if (p.equals(actual)) {
          System.out.print("\u263A ");

        } else if (p.equals(objetivo)) {
          System.out.print("+ ");
        } else {
          if (celdas.get(p).isTransitable()) {
            if (celdas.get(p).getDescripcion().equals("pasillo estrecho")) {
              System.out.print("\u2653 ");
            } else {
              System.out.print("\u26F6 ");
            }

          } else {
            System.out.print("X ");
          }
        }
      }
      System.out.println("");
    }
  }

  /**
   * pinta El mapa en formato ascii
   *
   * @return
   */
  public String pintarMapa(Point punto) {
    System.out.println("entrada");
    int x = punto.x;
    int y = punto.y;
    String retorno = "\"---------------Mapa do xogo------------------------\n";
    retorno +=
        CARACTER_TRANSITABLE
            + ": celda transitable, "
            + CARACTER_NO_TRANSITABLE
            + ": celda intransitable, "
            + CARACTER_OBJETO
            + ": celda transitable que conten obxeto\n";
    for (int i = 0; i < TAM_HORIZONTAL; i++) {
      for (int j = 0; j < TAM_VERTICAL; j++) {
        Celda celda = celdas.get(new Point(i, j));
        if (x == i && y == j) {
          retorno += CARACTER_POSICION_ACTUAL;
        } else if (celda.isTransitable()) {
          if (celda.getObjetos() != null && !celda.getObjetos().isEmpty()) {
            retorno += CARACTER_OBJETO;
          } else {
            retorno += CARACTER_TRANSITABLE;
          }
        } else {
          retorno += CARACTER_NO_TRANSITABLE;
        }
      }
      retorno += "\n";
    }
    return retorno;
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
            mapaParcial += CARACTER_POSICION_ACTUAL;
          } else if (celda_adyacente.isTransitable()) {
            if (celda_adyacente.getObjetos() != null && !celda_adyacente.getObjetos().isEmpty()) {
              mapaParcial += CARACTER_OBJETO;
            } else {
              mapaParcial += CARACTER_TRANSITABLE;
            }
          } else {
            mapaParcial += CARACTER_NO_TRANSITABLE;
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
            mapaParcial += CARACTER_POSICION_ACTUAL;
          } else if (celda_act.isTransitable()) {
            if (celda_act.getObjetos() != null && !celda_act.getObjetos().isEmpty()) {
              mapaParcial += CARACTER_OBJETO;
            } else {
              mapaParcial += CARACTER_TRANSITABLE;
            }
          } else {
            mapaParcial += CARACTER_NO_TRANSITABLE;
          }
        } else {
          // no es una celda descubierta -> pintamos un interrogante
          mapaParcial += CARACTER_INTERROGANTE;
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
}
