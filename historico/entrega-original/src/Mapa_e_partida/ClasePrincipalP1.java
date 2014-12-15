/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa_e_partida;

import Personaje.Guerrero;
import Personaje.Mago;
import Personaje.Personaje;
import Utilidades.ConsolaNormal;
import interfaces.CargadorJuego;
import java.awt.Point;
import juego.CargadorJuegoDeFicheros;
import juego.CargadorJuegoPorDefecto;
import juego.Juego;

/**
 * @author Miguel Alonso Castro, Rodrigo Sambade Saa
 */
public class ClasePrincipalP1 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    ConsolaNormal consola = new ConsolaNormal();
    // TODO code application logic here
    consola.imprimir(
        "    ███        ▄█    █▄       ▄████████       ▄█          ▄████████    ▄██████▄    "
            + " ▄████████ ███▄▄▄▄   ████████▄                    \n"
            + "▀█████████▄   ███    ███     ███    ███      ███         ███    ███   ███    ███  "
            + " ███    ███ ███▀▀▀██▄ ███   ▀███                   \n"
            + "   ▀███▀▀██   ███    ███     ███    █▀       ███         ███    █▀    ███    █▀   "
            + " ███    █▀  ███   ███ ███    ███                   \n"
            + "    ███   ▀  ▄███▄▄▄▄███▄▄  ▄███▄▄▄          ███        ▄███▄▄▄      ▄███        "
            + " ▄███▄▄▄     ███   ███ ███    ███                   \n"
            + "    ███     ▀▀███▀▀▀▀███▀  ▀▀███▀▀▀          ███       ▀▀███▀▀▀     ▀▀███ ████▄ "
            + " ▀▀███▀▀▀     ███   ███ ███    ███                   \n"
            + "    ███       ███    ███     ███    █▄       ███         ███    █▄    ███    ███  "
            + " ███    █▄  ███   ███ ███    ███                   \n"
            + "    ███       ███    ███     ███    ███      ███▌    ▄   ███    ███   ███    ███  "
            + " ███    ███ ███   ███ ███   ▄███                   \n"
            + "   ▄████▀     ███    █▀      ██████████      █████▄▄██   ██████████   ████████▀   "
            + " ██████████  ▀█   █▀  ████████▀                    \n"
            + "                                             ▀                                      "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                                                                    "
            + "                                                 \n"
            + "                                              ▄██████▄     ▄████████          ███   "
            + "     ▄████████  ▄████████  ▄█          ▄████████ \n"
            + "                                             ███    ███   ███    ███     "
            + " ▀█████████▄   ███    ███ ███    ███ ███         ███    ███ \n"
            + "                                             ███    ███   ███    █▀         "
            + " ▀███▀▀██   ███    █▀  ███    █▀  ███         ███    ███ \n"
            + "                                             ███    ███  ▄███▄▄▄              ███  "
            + " ▀  ▄███▄▄▄     ███        ███         ███    ███ \n"
            + "                                             ███    ███ ▀▀███▀▀▀              ███   "
            + "  ▀▀███▀▀▀     ███        ███       ▀███████████ \n"
            + "                                             ███    ███   ███                 ███   "
            + "    ███    █▄  ███    █▄  ███         ███    ███ \n"
            + "                                             ███    ███   ███                 ███   "
            + "    ███    ███ ███    ███ ███▌    ▄   ███    ███ \n"
            + "                                              ▀██████▀    ███                ▄████▀ "
            + "    ██████████ ████████▀  █████▄▄██   ███    █▀  \n"
            + "                                                                                    "
            + "                          ▀                      ");
    try {
      // *****************************************************************
      String orde = "";
      // cargamos el mapa con toda la informacion, personajes y objetos
      orde = consola.leer("cargar mapa de ficheros(s|n):");
      CargadorJuego cargador = null;
      if ("s".equalsIgnoreCase(orde)) {
        cargador = new CargadorJuegoDeFicheros();
      } else {
        cargador = new CargadorJuegoPorDefecto();
      }
      Juego juego = cargador.cargarJuego();

      Personaje pers = null;

      orde = consola.leer("jugada automática(s/n):");
      if ("s".equalsIgnoreCase(orde)) {
        pers = juego.getMapa().getJugadorAutomatico();
        pers.cogerMochila(new Point(0, 0), juego.getMapa());
        orde = consola.leer("indica ruta de comandos (en blanco lee del directorio raíz): ");
        juego.jugadaAutomatica(orde);
      } else {
        orde = consola.leer("Nombre personaje:");
        String tipo_pers = consola.leer("tipo personaje (guerrero|mago):");
        if ("guerrero".equalsIgnoreCase(tipo_pers)) {
          pers = new Guerrero(100, "", 100, null, new Point(0, 0));
        } else {
          pers = new Mago(100, "", 100, null, new Point(0, 0));
        }
        pers.setNombre(orde);
        juego.jugadaNueva(pers);
      }

      // pers.setPosicion(new Point(4,3)); //probas quitar

      while (pers.getVida() > 0) {
        try {
          orde = consola.leer("Indica comando:");
          if (juego.jugada(orde).equals("fin")) break;
        } catch (Exception ex) {
          consola.imprimir(ex.toString());
        }
      }
      // *****************************************************************
      /*String orde = "";
                  //cargamos el mapa con toda la informacion, personajes y objetos
                  Mapa mapa = new Mapa();
                  orde = consola.leer("comando cargar directorio(en blanco lee del directorio raíz):");
                  mapa.rellenaHashMap(orde);


                  Personaje pers = null;
                  orde = consola.leer("jugada automática(s/n):");
                  if("s".equalsIgnoreCase(orde)){
                      //en el fichero de personaje hay uno que debería ser el jugador
                      pers = mapa.getJugadorAutomatico();
                      consola.imprimir(pers.cogerMochila(new Point(0, 0), mapa));
                      orde = consola.leer("indica ruta de comandos (en blanco lee del directorio raíz): ");
                      Util.leerComandos(orde, pers, mapa);
                  }

                  //si el personaje es null es que no se ha creado en una jugada automática y lo tenemos que crear
                  if(pers == null){
                      orde = consola.leer("Nombre personaje:");
                      String tipo_pers = consola.leer("tipo personaje (guerrero|mago):");
                      if("guerrero".equalsIgnoreCase(tipo_pers)){
                          pers = new Guerrero(orde);
                      }else{
                          pers = new Mago(orde);
                      }
                      pers.setNombre(orde);
                      pers.setMapa(mapa); //mapa sobre el que juega
                      pers.setEnergia(100);
                      pers.setVida(100);
                      pers.setSalud(100);
                      pers.setFuerza(100);
                      //hacemos que el personaje comience en la celda 0,0
                      pers.setPosicion(new Point(0, 0));
                      pers.cogerMochila(pers.getPosicion(), mapa);
                      pers.setRutaRecorrida(pers.getPosicion());
                      mapa.usar(pers);
                  }

                  consola.imprimir("Posicion actual do personaje: " + pers.getPosicion());
                  while (pers.getVida() > 0) {
                      try{
                          //acciones obligatorias antes de cada orden (turno de npc's)
                          consola.imprimir("Posicion actual do personaje: " + pers.getPosicion());
                          consola.imprimir("Vida: " + pers.getVida() + ", Energia: " + pers.getEnergia());
                          consola.imprimir("indica orden (fin para terminar): ");

                          //si al entrar a una celda hay un personaje que nos pueda atacar
                          if (mapa.getCelda(pers.getPosicion()).isNpc()) {
                              //comprobamos que sea un enemigo activo, que es el que puede atacarnos
                              NPC npc = mapa.getCelda(pers.getPosicion()).getNPC();
                              if(npc instanceof Activo){
                                  npc.atacar(pers);
                              }
                          }



                          //ahora el turno del jugador
                          orde = consola.leer("Indica comando:");
                          if ("fin".equalsIgnoreCase(orde)) {
                              break;
                          }else if("h".equalsIgnoreCase(orde)){
                              //mostramos a axuda
                              consola.imprimir(Axuda.Mostrar());
                          }else if(orde.contains("mirar")){
                              String objeto = "";
                              //mostramos solo la descripcion de un objeto si pone p.e mirar espada, en caso de poner mirar mostramos los que tenga la celda
                              if(orde.split(" ").length>1){
                                  objeto = orde.split(" ")[1];
                                  consola.imprimir(mapa.mirarObjetoCelda(pers.getPosicion(),objeto));
                              }else{
                                  consola.imprimir(mapa.mirarCelda(pers.getPosicion()));
                              }
                          }else if("mapa".equalsIgnoreCase(orde)){
                              if(pers.getMochila()!=null && pers.getMochila().tieneObjeto("mapa")){
                                  mapa.usar(pers);
                              }else{
                                  consola.imprimir("No se pinta el mapa hasta que lo tengas en la mochila");
                              }
                          }else if("mapa_parcial".equalsIgnoreCase(orde)){
                              //consola.imprimir(mapa.pintarMapaParcial(mapa.getCelda(pers.getPosicion())));
                              consola.imprimir("Celdas descubiertas:" + pers.getRutaDescubierta(CONST.TAM_HORIZONTAL));
                              consola.imprimir(mapa.pintarMapaParcial2(mapa.getCelda(pers.getPosicion()),pers.getRutaDescubierta(CONST.TAM_HORIZONTAL)));
                          }else if(orde.contains("coger")){
                              if(orde.split(" ").length==2){
                                  String nombreObjeto = orde.split(" ")[1];
                                  //si no tiene mochila y pide cogerla vamos a comprobar que existe en esta celda
                                  if(pers.getMochila() == null && "mochila".equalsIgnoreCase(nombreObjeto)){
                                      consola.imprimir(pers.cogerMochila(pers.getPosicion(), mapa));
                                  }else if(pers.getMochila()!=null){
                                      //no es un objeto mochila y ya tiene Mochila por lo que intentamos coger el objeto
                                      consola.imprimir(pers.coger(new Objeto(nombreObjeto),mapa.getCelda(pers.getPosicion())));
                                  }else{
                                      consola.imprimir("No tienes mochila");
                                  }
                              }else{
                                  consola.imprimir("la orden necesita 2 palabras con espacio en medio");
                              }
                          }else if(orde.contains("tirar")){
                              String nombreObjeto = orde.split(" ")[1];
                              //comprobamos que teña Mochila
                              if(pers.getMochila()!=null){
                                  consola.imprimir(pers.tirar(new Objeto(nombreObjeto),mapa.getCelda(pers.getPosicion())));
                              }else{
                                  consola.imprimir("No tienes mochila");
                              }
                          }else if(orde.contains("usar")){
                              if(orde.split(" ").length==2){
                                  String objeto_usado = orde.split(" ")[1];
                                  if(pers.getMochila()!=null){
                                      Objeto obj = pers.getMochila().objetoBuscado(objeto_usado);
                                      if(obj==null){
                                         consola.imprimir("no tienes el objeto en la mochila para usarlo");
                                      }else{
                                          if(obj.getTipo_objeto().equalsIgnoreCase("pocima")){
                                              pers.setEnergia(pers.getEnergia() + obj.getEfecto());
                                              consola.imprimir("energia actual:" + pers.getEnergia());
                                              pers.getMochila().eliminarObjeto(obj);
                                          }
                                      }
                                  }else{
                                      consola.imprimir("no tienes mochila");
                                  }
                              }else{
                                  consola.imprimir("la orden necesita 2 palabras con espacio en medio");
                              }
                          }else if("inventario".equalsIgnoreCase(orde)){
                              //comprobamos si tiene mochila y que tiene en ella
                              if(pers.getMochila()!=null){
                                  consola.imprimir("Contenido mochila:\n" + pers.getMochila().ojearInventario());
                              }else{
                                  consola.imprimir("Inventario: no tiene mochila");
                              }

                          } else if (orde.contains("atacar")) {
                              pers.atacar(new Personaje(orde.split(" ")[1]));
                          } else {
                              //pers.mover(mapa, orde);
                              new ComandoMover(orde, mapa, pers).ejecutar();
                          }

                      }catch(Exception ex){
                          consola.imprimir(ex.toString());
                      }
                  }
      */
    } catch (Exception e) {
      consola.imprimir("ERROR:" + e.toString());
    }
  }
}
