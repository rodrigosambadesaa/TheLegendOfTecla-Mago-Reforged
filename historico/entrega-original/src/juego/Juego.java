package juego;

import Mapa_e_partida.Mapa;
import Personaje.Objeto;
import Personaje.Personaje;
import Utilidades.CONST;
import Utilidades.ConsolaNormal;
import Utilidades.Util;
import comandos.ComandoAtacar;
import comandos.ComandoCoger;
import comandos.ComandoCompuesto;
import comandos.ComandoMirar;
import comandos.ComandoMover;
import comandos.ComandoRepetido;
import comandos.ComandoTirar;
import documentacion.Axuda;
import excepciones.ComandoExcepcion;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Juego {
  private Mapa mapa;
  private Personaje jugador;
  private ArrayList<Personaje> personajes;
  private ArrayList<Objeto> objetos;
  private ConsolaNormal consola = new ConsolaNormal();

  public Juego() {}

  public Juego(
      Mapa mapa, Personaje jugador, ArrayList<Personaje> personajes, ArrayList<Objeto> objetos) {
    this.mapa = mapa;
    this.jugador = jugador;
    this.personajes = personajes;
    this.objetos = objetos;
  }

  public Mapa getMapa() {
    return mapa;
  }

  public void setMapa(Mapa mapa) {
    this.mapa = mapa;
  }

  public Personaje getJugador() {
    return jugador;
  }

  public void setJugador(Personaje jugador) {
    this.jugador = jugador;
  }

  public ArrayList<Personaje> getPersonajes() {
    return personajes;
  }

  public void setPersonajes(ArrayList<Personaje> personajes) {
    this.personajes = personajes;
  }

  public ArrayList<Objeto> getObjetos() {
    return objetos;
  }

  public void setObjetos(ArrayList<Objeto> objetos) {
    this.objetos = objetos;
  }

  public void cargarMapaDeFichero(String ruta) {
    this.mapa = new Mapa();
    // llamamos al que carga los ficheros que es el que lleva como parámetro la ruta donde están
    this.mapa.rellenaHashMap(ruta);
    jugador = this.mapa.getJugadorAutomatico();
    personajes = this.mapa.getPersonajes_secundarios();
    objetos = this.mapa.getObjetos();
    jugador.setMapa(mapa);

    // si hay algún objeto con dueño se lo ponemos en su mochila
    for (Objeto objeto : this.objetos) {
      if (!objeto.getPoseedor().equals("")
          && !objeto.getPoseedor().equals("jugador")
          && !objeto.getPoseedor().equals(".")) {
        for (Personaje npc : this.personajes) {
          if (npc.getNombre().equalsIgnoreCase(objeto.getPoseedor())) {
            npc.getMochila().setObjetos(objeto);
          }
        }
      }
    }
  }

  public void cargarMapaAleatorio() throws Exception {
    // creamos un personaje sin nombre y sin mochila, colocado en la celda 0,0
    Personaje jugador = new Personaje(100, "", 100, null, new Point(0, 0));
    jugador.setSalud(100);
    jugador.setFuerza(100);

    // en este constructor Mapa crea un mapa aleatorio
    this.mapa = new Mapa(jugador);
    this.objetos = this.mapa.getObjetos();

    jugador.setMapa(this.getMapa());
    jugador.setRutaRecorrida(jugador.getPosicion());
    mapa.usar(jugador);
    consola.imprimir("Posicion actual do personaje: " + jugador.getPosicion());
  }

  public void jugadaAutomatica(String rutaFichComandos) throws FileNotFoundException {
    Util.leerComandos(rutaFichComandos, this.getJugador(), this.getMapa());
  }

  public void jugadaNueva(Personaje pers) throws Exception {
    this.jugador = pers;
    this.jugador.setSalud(100);
    this.jugador.setFuerza(100);
    this.jugador.cogerMochila(new Point(0, 0), this.getMapa());
    this.jugador.getMochila().setCapacidad(5);
    jugador.setMapa(this.getMapa());
    jugador.setRutaRecorrida(jugador.getPosicion());
  }

  public String jugada(String orde) throws Exception {
    String cad = "";
    if ("fin".equalsIgnoreCase(orde)) {
      cad = "fin";
    } else if ("h".equalsIgnoreCase(orde)) {
      // mostramos a axuda
      consola.imprimir(Axuda.Mostrar());
    } else if (orde.contains(",")) {
      // si trae coma es un comando compuesto
      new ComandoCompuesto(orde, this).ejecutar();
    } else if (orde.matches(".*[0-9].*")) {
      // comprobamos que teña 3 argumentos, que o terceiro sexa un nuemro e que conteña mover e unha
      // direccion
      if (orde.split(" ").length == 3) {
        String veces = orde.split(" ")[2];
        String cmd = orde.split(" ")[0] + " " + orde.split(" ")[1];
        if (orde.contains("norte")
            || orde.contains("sur")
            || orde.contains("este")
            || orde.contains("oeste")) {
          new ComandoRepetido(new ComandoMover(cmd, mapa, this.jugador), Integer.parseInt(veces));
        } else {
          throw new ComandoExcepcion(
              "falta direccion de movimiento en el segundo parametro: norte, sur, este, oeste");
        }
      } else {
        throw new ComandoExcepcion(
            "comando con numero de argumentos incorrectos, exemplo correcto: mover este 5");
      }

    } else if (orde.contains("mirar")) {
      //            String objeto = "";
      //            //mostramos solo la descripcion de un objeto si pone p.e mirar espada, en caso
      // de poner mirar mostramos los que tenga la celda
      //            if(orde.split(" ").length>1){
      //                objeto = orde.split(" ")[1];
      //                consola.imprimir(mapa.mirarObjetoCelda(this.jugador.getPosicion(),objeto));
      //            }else{
      //                consola.imprimir(mapa.mirarCelda(this.jugador.getPosicion()));
      //            }
      new ComandoMirar(orde, this).ejecutar();
    } else if ("mapa".equalsIgnoreCase(orde)) {
      if (this.jugador.getMochila() != null && this.jugador.getMochila().tieneObjeto("mapa")) {
        mapa.usar(this.jugador);
      } else {
        consola.imprimir("No se pinta el mapa hasta que lo tengas en la mochila");
      }
    } else if ("mapa_parcial".equalsIgnoreCase(orde)) {
      // consola.imprimir(mapa.pintarMapaParcial(mapa.getCelda(pers.getPosicion())));
      consola.imprimir(
          "Celdas descubiertas:" + this.jugador.getRutaDescubierta(CONST.TAM_HORIZONTAL));
      consola.imprimir(
          mapa.pintarMapaParcial2(
              mapa.getCelda(this.jugador.getPosicion()),
              this.jugador.getRutaDescubierta(CONST.TAM_HORIZONTAL)));
    } else if (orde.contains("coger")) {
      //            if(orde.split(" ").length==2){
      //                String nombreObjeto = orde.split(" ")[1];
      //                //si no tiene mochila y pide cogerla vamos a comprobar que existe en esta
      // celda
      //                if("mochila".equalsIgnoreCase(nombreObjeto)){
      //                    if(this.jugador.getMochila() == null){
      //                        throw new Exception("ya tienes mochila");
      //                    }else{
      //                        this.jugador.cogerMochila(this.jugador.getPosicion(), mapa);
      //                    }
      //                }else if(this.jugador.getMochila()!=null){
      //                    //no es un objeto mochila y ya tiene Mochila por lo que intentamos coger
      // el objeto
      //                    consola.imprimir(this.jugador.coger(new
      // Objeto(nombreObjeto),mapa.getCelda(this.jugador.getPosicion())));
      //                }else{
      //                    consola.imprimir("No tienes mochila");
      //                }
      //            }else{
      //                consola.imprimir("la orden necesita 2 palabras con espacio en medio");
      //            }
      new ComandoCoger(orde, this).ejecutar();
    } else if (orde.contains("tirar")) {
      //            String nombreObjeto = orde.split(" ")[1];
      //            //comprobamos que teña Mochila
      //            if(this.jugador.getMochila()!=null){
      //                consola.imprimir(this.jugador.tirar(new
      // Objeto(nombreObjeto),mapa.getCelda(this.jugador.getPosicion())));
      //            }else{
      //                consola.imprimir("No tienes mochila");
      //            }
      new ComandoTirar(orde, this).ejecutar();
    } else if (orde.contains("usar")) {
      if (orde.split(" ").length == 2) {
        String objeto_usado = orde.split(" ")[1];
        if (this.jugador.getMochila() != null) {
          Objeto obj = this.jugador.getMochila().objetoBuscado(objeto_usado);
          if (obj == null) {
            consola.imprimir("no tienes el objeto en la mochila para usarlo");
          } else {
            if (obj.getTipo_objeto().equalsIgnoreCase("pocima")) {
              this.jugador.setEnergia(this.jugador.getEnergia() + obj.getEfecto());
              consola.imprimir("energia actual:" + this.jugador.getEnergia());
              this.jugador.getMochila().eliminarObjeto(obj);
            }
          }
        } else {
          consola.imprimir("no tienes mochila");
        }
      } else {
        consola.imprimir("la orden necesita 2 palabras con espacio en medio");
      }
    } else if ("inventario".equalsIgnoreCase(orde)) {
      // comprobamos si tiene mochila y que tiene en ella
      if (this.jugador.getMochila() != null) {
        consola.imprimir("Contenido mochila:\n" + this.jugador.getMochila().ojearInventario());
      } else {
        consola.imprimir("Inventario: no tiene mochila");
      }

    } else if (orde.contains("atacar")) {
      // this.jugador.atacar(new Personaje(orde.split(" ")[1]));
      new ComandoAtacar(orde, this).ejecutar();
    } else {
      // this.jugador.mover(mapa, orde);
      new ComandoMover(orde, mapa, this.jugador).ejecutar();
    }
    return cad;
  }
}
