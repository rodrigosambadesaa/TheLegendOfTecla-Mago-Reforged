package Mapa_e_partida;

import Documentacion.Axuda;
import Personaje.Mochila;
import Personaje.Personaje;
import java.util.Scanner;

/** Punto de entrada de la primera entrega histórica. */
public class ClasePrincipalP1 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
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
    try (Scanner teclado = new Scanner(System.in)) {
      System.out.println("introduce nombre");
      String nome = teclado.nextLine();
      Mochila mochila = new Mochila();
      Personaje personaje = new Personaje(100, nome, 100, mochila);
      Mapa mapa = new Mapa(personaje);
      personaje.empezar(mapa);
      System.out.println("nombre: " + personaje.getNombre());
      System.out.println("posicion: " + personaje.getPosicionActualString());
      while (true) {
        System.out.println(
            "escribe direccion (norte,sur,este,oeste)(ver comandos:h) posicion actual: "
                + personaje.getPosicionActualString());
        String orde = teclado.nextLine();
        if ("fin".equalsIgnoreCase(orde)) {
          break;
        } else if ("h".equalsIgnoreCase(orde)) {
          System.out.println(Axuda.mostrar());
          System.out.println();
        } else if ("mirar".equalsIgnoreCase(orde)) {
          personaje.mirar(mapa);
          System.out.println();
        } else if ("mapa".equalsIgnoreCase(orde)) {
          mapa.imprimirMapa(personaje);
          System.out.println();
        } else {
          String movimiento = personaje.mover(mapa, orde);
          System.out.println("vida: " + personaje.getVida());
          System.out.println("energia: " + personaje.getEnergia());

          if (movimiento.equalsIgnoreCase("ok")) {
            System.out.println("posicion:" + personaje.getPosicionActualString());
          } else {
            System.out.println("non se moveu:" + movimiento);
          }
          System.out.println();
        }
      }
    } catch (Exception e) {
      System.out.println("ERROR:" + e.toString());
    }
  }
}
