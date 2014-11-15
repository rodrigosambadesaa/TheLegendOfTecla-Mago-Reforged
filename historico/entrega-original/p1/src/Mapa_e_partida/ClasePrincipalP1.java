/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa_e_partida;

import Personaje.Objeto;
import Personaje.Personaje;
import Utilidades.Util;
import documentacion.Axuda;
import java.awt.Point;
import java.util.Scanner;

/**
 * @author Miguel Alonso Castro, Rodrigo Sambade Saa
 */
public class ClasePrincipalP1 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    System.out.println(
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
    /*     try {
        String nome = "";
        Scanner name = new Scanner(System.in);
        System.out.println("introduce nombre");
        nome = name.nextLine();
        Mochila mochila = new Mochila();
        Personaje personaje = new Personaje(100, nome, 100, mochila);
        Mapa mapa = new Mapa(personaje);
        personaje.empezar(mapa);
        System.out.println("nombre: " + personaje.getNombre());
        System.out.println("posicion: " + personaje.getPosicionActualString());
        String movimiento = "";
        String orde = "";



        while (true) {

            //if enemigoActivo, atacar ao personaxe

        Scanner keyboard = new Scanner(System.in);
            System.out.println("escribe direccion (norte,sur,este,oeste)(ver comandos:h) posicion actual: " + personaje.getPosicionActualString());
            orde = keyboard.nextLine();
            //salimos con fin
            if ("fin".equalsIgnoreCase(orde)) {
                break;
            }else if("h".equalsIgnoreCase(orde)){
                System.out.println(Axuda.Mostrar());
                System.out.println("");
            }else if("mirar".equalsIgnoreCase(orde)){
                //temos que mirar se hai algún obxeto dentro da celda
                personaje.mirar(mapa);
                System.out.println("");
            }else if("mapa".equalsIgnoreCase(orde)){
                mapa.imprimirMapa();
                System.out.println("");
            }else{
                movimiento = personaje.mover(mapa, orde);
                System.out.println("vida: " + personaje.getVida());
                System.out.println("energia: " + personaje.getEnergia());

                if (movimiento.equalsIgnoreCase("ok")) {
                    System.out.println("posicion:" +personaje.getPosicionActualString());
                } else {
                    System.out.println("non se moveu:" + movimiento);
                }
                System.out.println("");


            }





        }
    } catch (Exception e) {
        System.out.println("ERROR:" + e.toString());
    }*/

    try {
      //            Mapa mapa = new Mapa();
      //            Mochila mochila = new Mochila();
      //
      //            String nome = "";
      //            Scanner name = new Scanner(System.in);
      //            System.out.println("introduce nombre");
      //            nome = name.nextLine();
      //            Personaje personaje = new Personaje(100, nome, 100, mochila);
      //            personaje.empezar(mapa);
      //            System.out.println("nombre: " + personaje.getNombre());
      //            System.out.println("posicion:" + personaje.getPosicion());
      //            //personaje.setPosicion("0,8");
      //            //String movimiento = personaje.mover(mapa, "oeste");
      //            String movimiento = "";
      //            String orde = "";
      //            while (true) {
      //                Scanner keyboard = new Scanner(System.in);
      //                System.out.println("escribe direccion (norte,sur,este,oeste)(ver comandos:h)
      // posicion actual: " + personaje.getPosicion());
      //                orde = keyboard.nextLine();
      //                //salimos con fin
      //                if ("fin".equalsIgnoreCase(orde)) {
      //                    break;
      //                }else if("h".equalsIgnoreCase(orde)){
      //                    System.out.println(Axuda.Mostrar());
      //                }else if("mirar".equalsIgnoreCase(orde)){
      //                    //temos que mirar se hai algún obxeto dentro da celda
      //                    Celda celda =mapa.getCelda(personaje.getPosicion());
      //                    if(celda.getObjetos().isEmpty()){
      //                        System.out.println("Non hai obxetos na celda");
      //                    }else{
      //                        System.out.println("Tipo obxeto:"
      // +celda.getObjetos().get(0).getTipo());
      //                        System.out.println("Nombe obxeto:"
      // +celda.getObjetos().get(0).getNombre());
      //                        System.out.println("efecto obxeto:"
      // +celda.getObjetos().get(0).getEfecto());
      //                        System.out.println("peso obxeto:"
      // +celda.getObjetos().get(0).getPeso());
      //                        System.out.println("Descripcion celda:" +celda.getDescripcion());
      //                    }
      //                }else if("mapa".equalsIgnoreCase(orde)){
      //                    System.out.println(mapa.pintarMapa());
      //                }else{
      //                    movimiento = personaje.mover(mapa, orde);
      //                    System.out.println("vida: " + personaje.getVida());
      //                    System.out.println("energia: " + personaje.getEnergia());
      //
      //                    if (movimiento.equalsIgnoreCase("ok")) {
      //                        System.out.println("posicion:" + personaje.getPosicion());
      //                    } else {
      //                        System.out.println("non se moveu:" + movimiento);
      //                    }
      //                }
      //            }
      // Si es un objeto de tipo Mochila creamos un objeto Mochila para pasarselo al personaje
      // protagonista
      //            if("mochila".equalsIgnoreCase(objeto.getTipo_objeto())){
      //                Mochila mochila = new Mochila();
      //                mochila.setPesoActual(0);
      //                mochila.setPesoMax(objeto.getPeso());
      //                mochila.setDescripcion(objeto.getDescripcion());
      //            }

      String orde = "";
      // cargamos el mapa con toda la informacion, personajes y objetos
      Mapa mapa = new Mapa();
      Scanner keyboard = new Scanner(System.in);
      System.out.println("comando cargar directorio(en blanco lee del directorio raíz): ");
      orde = keyboard.nextLine();
      mapa.rellenaHashMap(orde);
      System.out.println("Nombre personaje: ");
      orde = keyboard.nextLine();
      Personaje pers = new Personaje();
      pers.setEnergiaMaxima(100);
      pers.setVidaMaxima(100);
      pers.setSaludMaxima(100);
      pers.setEnergia(100);
      pers.setVida(100);
      pers.setSalud(100);
      pers.setFuerza(100);
      // hacemos que el personaje comience en la celda 0,0
      pers.setPosicion(new Point(0, 0));
      pers.cogerMochila(pers.getPosicion(), mapa);
      pers.setRutaRecorrida(pers.getPosicion());

      System.out.println(mapa.pintarMapa(pers.getPosicion()));

      pers.setNombre(orde);
      System.out.println("jugada automática(s/n): ");
      orde = keyboard.nextLine();
      if ("s".equalsIgnoreCase(orde)) {
        // hacemos que el personaje coja automaticamente la mochila de la celda 1
        System.out.println(pers.cogerMochila(new Point(0, 0), mapa));
        System.out.println("indica ruta de comandos (en blanco lee del directorio raíz): ");
        orde = keyboard.nextLine();
        Util.leerComandos(orde, pers, mapa);
      }
      System.out.println("Posicion actual do personaje: " + pers.getPosicion());

      while (pers.getVida() > 0) {

        // acciones obligatorias antes de cada orden (turno de npc's)
        System.out.println("Posicion actual do personaje: " + pers.getPosicion());
        System.out.println("Vida: " + pers.getVida() + "Energia: " + pers.getEnergia());
        System.out.println("indica orden (fin para terminar): ");

        if (mapa.getCelda(pers.getPosicion()).isNpc()) {
          for (int i = 0; i < mapa.getCelda(pers.getPosicion()).getNpcs().size(); i++) {
            if (mapa.getCelda(pers.getPosicion()).getNpcs().get(i).isAmigable()) {
            } else {
              if (mapa.getCelda(pers.getPosicion()).getNpcs().get(i).isActivo()) {
                mapa.getCelda(pers.getPosicion()).getNpcs().get(i).atacar(pers);
              }
            }
          }
        }

        // ahora el turno del jugador
        orde = keyboard.nextLine();
        if ("fin".equalsIgnoreCase(orde)) {
          break;
        } else if ("h".equalsIgnoreCase(orde)) {
          // mostramos a axuda
          System.out.println(Axuda.Mostrar());
        } else if (orde.contains("mirar")) {
          String objeto = "";
          // mostramos solo la descripcion de un objeto si pone p.e mirar espada, en caso de poner
          // mirar mostramos los que tenga la celda
          if (orde.split(" ").length > 1) {
            objeto = orde.split(" ")[1];
            System.out.println(mapa.mirarObjetoCelda(pers.getPosicion(), objeto));
          } else {
            System.out.println(mapa.mirarCelda(pers.getPosicion()));
          }
        } else if ("mapa".equalsIgnoreCase(orde)) {
          if (pers.getMochila() != null && pers.getMochila().tieneObjeto("mapa")) {
            System.out.println(mapa.pintarMapa(pers.getPosicion()));
          } else {
            System.out.println("No se pinta el mapa hasta que lo tengas en la mochila");
          }
        } else if ("mapa_parcial".equalsIgnoreCase(orde)) {
          // System.out.println(mapa.pintarMapaParcial(mapa.getCelda(pers.getPosicion())));
          System.out.println("Celdas descubiertas:" + pers.getRutaDescubierta(Mapa.TAM_HORIZONTAL));
          System.out.println(
              mapa.pintarMapaParcial2(
                  mapa.getCelda(pers.getPosicion()), pers.getRutaDescubierta(Mapa.TAM_HORIZONTAL)));
        } else if (orde.contains("coger")) {
          if (orde.split(" ").length == 2) {
            String nombreObjeto = orde.split(" ")[1];
            // si no tiene mochila y pide cogerla vamos a comprobar que existe en esta celda
            if (pers.getMochila() == null && "mochila".equalsIgnoreCase(nombreObjeto)) {
              System.out.println(pers.cogerMochila(pers.getPosicion(), mapa));
            } else if (pers.getMochila() != null) {
              // no es un objeto mochila y ya tiene Mochila por lo que intentamos coger el objeto
              System.out.println(
                  pers.getMochila().cogerObjeto(pers.getPosicion(), mapa, nombreObjeto));
            } else {
              System.out.println("No tienes mochila");
            }
          } else {
            System.out.println("la orden necesita 2 palabras con espacio en medio");
          }
        } else if (orde.contains("tirar")) {
          String nombreObjeto = orde.split(" ")[1];
          // comprobamos que teña Mochila
          if (pers.getMochila() != null) {
            System.out.println(
                pers.getMochila().tirarObjeto(pers.getPosicion(), mapa, nombreObjeto));
          } else {
            System.out.println("No tienes mochila");
          }
        } else if (orde.contains("usar")) {
          if (orde.split(" ").length == 2) {
            String objeto_usado = orde.split(" ")[1];
            if (pers.getMochila() != null) {
              Objeto obj = pers.getMochila().objetoBuscado(objeto_usado);
              if (obj == null) {
                System.out.println("no tienes el objeto en la mochila para usarlo");
              } else {
                if (obj.getTipo_objeto().equalsIgnoreCase("pocima")) {
                  pers.setEnergia(pers.getEnergia() + obj.getEfecto());
                  System.out.println("energia actual:" + pers.getEnergia());
                  pers.getMochila().eliminarObjeto(obj);
                }
              }
            } else {
              System.out.println("no tienes mochila");
            }
          } else {
            System.out.println("la orden necesita 2 palabras con espacio en medio");
          }
        } else if ("inventario".equalsIgnoreCase(orde)) {
          // comprobamos si tiene mochila y que tiene en ella
          if (pers.getMochila() != null) {
            System.out.println("Contenido mochila:\n" + pers.getMochila().ojearInventario());
          } else {
            System.out.println("Inventario: no tiene mochila");
          }

        } else if (orde.contains("atacar")) {
          if (orde.split(" ").length == 2) {
            // Npcs enemigo = new Npcs(orde[1], pers.getPosicion());
            String nombreEnemigo = orde.split(" ")[1];
            pers.atacar(nombreEnemigo, mapa.getCelda(pers.getPosicion()));
          } else {
            System.out.println("la orden necesita 2 palabras con espacio en medio");
          }

        } else {
          String movimiento = pers.mover(mapa, orde);
          if (movimiento.equalsIgnoreCase("ok")) {
            if (pers.getEnergia() >= pers.calculaGastoEnerxia()) {
              System.out.println("posicion:" + pers.getPosicion());
              pers.setEnergia(pers.getEnergia() - pers.calculaGastoEnerxia());
            } else {
              System.out.println("Non tes suficiente enerxia para moverte!");
            }

            // pers.setRutaRecorrida(pers.getPosicion());
          } else {
            System.out.println("non se moveu:" + movimiento);
          }
        }
      }

    } catch (Exception e) {
      System.out.println("ERROR:" + e.toString());
    }
  }
}
