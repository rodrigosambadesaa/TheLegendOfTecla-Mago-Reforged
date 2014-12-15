/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Mapa_e_partida.Celda;
import Mapa_e_partida.Mapa;
import Personaje.Activo;
import Personaje.Amigo;
import Personaje.Enemigo;
import Personaje.Guerrero;
import Personaje.Mago;
import Personaje.Mochila;
import Personaje.Objeto;
import Personaje.Pasivo;
import Personaje.Personaje;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {
  static ConsolaNormal consola = new ConsolaNormal();

  public static ArrayList<Celda> leerDatosMapa(String rutaFichero) throws FileNotFoundException {
    ArrayList<Celda> lista = new ArrayList<>();
    Scanner scan = new Scanner(new File(rutaFichero));
    String linea;
    String partes[];
    while (scan.hasNextLine()) {
      linea = scan.nextLine();
      partes = linea.split(";");
      if (partes.length == 3) {
        int x = Integer.valueOf(partes[0].split(",")[0]);
        int y = Integer.valueOf(partes[0].split(",")[1]);
        Point pto = new Point(x, y);
        Celda celda = new Celda(pto, partes[1], partes[2]);
        celda.setTransitable(true);
        lista.add(celda);
        consola.imprimir(celda.toString());
      }
    }
    return lista;
  }

  public static ArrayList<Personaje> leerDatosPersonajes(String rutaFichero)
      throws FileNotFoundException {
    ArrayList<Personaje> lista = new ArrayList<>();
    Scanner scan = new Scanner(new File(rutaFichero));
    String linea;
    String partes[];
    while (scan.hasNextLine()) {
      linea = scan.nextLine();
      partes = linea.split(";");
      if (partes.length == 9) {
        int x = Integer.valueOf(partes[0].split(",")[0]);
        int y = Integer.valueOf(partes[0].split(",")[1]);
        Point pto = new Point(x, y);

        if (partes[1].equalsIgnoreCase("jugador")) {
          // Creamos o xogador como Guerrero ou Mago segundo poña no último campo
          if (partes[8].equalsIgnoreCase("Guerrero")) {
            Guerrero personaje =
                new Guerrero(
                    Integer.parseInt(partes[3]),
                    partes[2],
                    Integer.parseInt(partes[4]),
                    new Mochila(),
                    pto);
            personaje.setSalud(Integer.parseInt(partes[5]));
            personaje.setFuerza(Integer.parseInt(partes[6]));
            personaje.setDefensa(100);
            lista.add(personaje);
          } else {
            Mago personaje =
                new Mago(
                    Integer.parseInt(partes[3]),
                    partes[2],
                    Integer.parseInt(partes[4]),
                    new Mochila(),
                    pto);
            personaje.setSalud(Integer.parseInt(partes[5]));
            personaje.setFuerza(Integer.parseInt(partes[6]));
            personaje.setDefensa(100);
            lista.add(personaje);
          }
        } else if (partes[1].equalsIgnoreCase("amigo")) {
          Amigo personaje =
              new Amigo(
                  Integer.parseInt(partes[3]),
                  partes[2],
                  Integer.parseInt(partes[4]),
                  new Mochila(),
                  pto);
          personaje.setSalud(Integer.parseInt(partes[5]));
          personaje.setFuerza(Integer.parseInt(partes[6]));
          personaje.setRespuesta(partes[7]);
          personaje.setNombre(partes[2]);
          personaje.setTipo(partes[1]);
          personaje.setDefensa(50);
          lista.add(personaje);
        } else if (partes[1].equalsIgnoreCase("enemigo")) {
          Enemigo personaje =
              new Enemigo(
                  Integer.parseInt(partes[3]),
                  partes[2],
                  Integer.parseInt(partes[4]),
                  new Mochila(),
                  pto);
          personaje.setSalud(Integer.parseInt(partes[5]));
          personaje.setFuerza(Integer.parseInt(partes[6]));
          personaje.setRespuesta(partes[7]);
          personaje.setTipo(partes[1]);
          personaje.setDefensa(50);
          lista.add(personaje);
        } else if (partes[1].equalsIgnoreCase("enemigoactivo")) {
          Activo personaje =
              new Activo(
                  Integer.parseInt(partes[3]),
                  partes[2],
                  Integer.parseInt(partes[4]),
                  new Mochila(),
                  pto);
          personaje.setSalud(Integer.parseInt(partes[5]));
          personaje.setFuerza(Integer.parseInt(partes[6]));
          personaje.setRespuesta(partes[7]);
          personaje.setTipo(partes[1]);
          personaje.setDefensa(50);
          lista.add(personaje);
        } else if (partes[1].equalsIgnoreCase("enemigopasivo")) {
          Pasivo personaje =
              new Pasivo(
                  Integer.parseInt(partes[3]),
                  partes[2],
                  Integer.parseInt(partes[4]),
                  new Mochila(),
                  pto);
          personaje.setSalud(Integer.parseInt(partes[5]));
          personaje.setFuerza(Integer.parseInt(partes[6]));
          personaje.setRespuesta(partes[7]);
          personaje.setTipo(partes[1]);
          personaje.setDefensa(50);
          lista.add(personaje);
        }
      }
    }
    return lista;
  }

  public static ArrayList<Objeto> leerDatosObjetos(String rutaFichero)
      throws FileNotFoundException {
    ArrayList<Objeto> lista = new ArrayList<>();
    Scanner scan = new Scanner(new File(rutaFichero));
    String linea;
    String partes[];
    while (scan.hasNextLine()) {
      linea = scan.nextLine();
      partes = linea.split(";");

      if (partes.length == 8) {
        Objeto objeto =
            new Objeto(
                partes[3], Double.parseDouble(partes[7]), partes[5], Integer.parseInt(partes[6]));
        int x = Integer.valueOf(partes[0].split(",")[0]);
        int y = Integer.valueOf(partes[0].split(",")[1]);
        Point pto = new Point(x, y);
        objeto.setPosicionMapa(pto);
        objeto.setTipo_objeto(partes[2]);
        objeto.setDescripcion(partes[4]);
        objeto.setPoseedor(partes[1]);
        lista.add(objeto);
        consola.imprimir(objeto.toString());
      }
    }
    return lista;
  }

  public static ArrayList<Objeto> leerComandos(String rutaFichero, Personaje pers, Mapa mapa)
      throws FileNotFoundException {
    ArrayList<Objeto> lista = new ArrayList<>();
    rutaFichero += "comandos.txt";
    Scanner scan = new Scanner(new File(rutaFichero));
    String linea;
    String orde = "";
    while (scan.hasNextLine()) {
      try {

        linea = scan.nextLine();
        // os comentarios non os procesamos
        if (!linea.startsWith("#")) {
          consola.imprimir("comando: " + linea);
          if (linea.contains("mover")) {
            pers.mover(mapa, linea);
          } else if (linea.contains("mirar")) {
            String objeto = "";
            // mostramos solo la descripcion de un objeto si pone p.e mirar espada, en caso de poner
            // mirar mostramos los que tenga la celda
            if (orde.split(" ").length > 1) {
              objeto = orde.split(" ")[1];
              consola.imprimir(mapa.mirarObjetoCelda(pers.getPosicion(), objeto));
            } else {
              consola.imprimir(mapa.mirarCelda(pers.getPosicion()));
            }
          } else if (linea.contains("coger")) {
            orde = linea.split(" ")[1];
            Objeto obj = new Objeto(orde);
            consola.imprimir(pers.coger(obj, mapa.getCelda(pers.getPosicion())));
          } else if (linea.contains("tirar")) {
            orde = linea.split(" ")[1];
            Objeto obj = new Objeto(orde);
            consola.imprimir(pers.tirar(obj, mapa.getCelda(pers.getPosicion())));
          } else if (linea.contains("usar")) {
            orde = linea.split(" ")[1];
            Objeto obj = pers.getMochila().objetoBuscado(orde);
            if (obj == null) {
              consola.imprimir("no tienes el objeto en la mochila para usarlo");
            } else {
              if (obj.getTipo_objeto().equalsIgnoreCase("pocima")) {
                pers.setEnergia(pers.getEnergia() + obj.getEfecto());
                consola.imprimir("energia actual:" + pers.getEnergia());
                pers.getMochila().eliminarObjeto(obj);
              }
            }
          } else if (linea.contains("inventario")) {
            consola.imprimir(pers.getMochila().ojearInventario());
          }
        }
      } catch (Exception ex) {
        consola.imprimir(ex.toString());
      }
    }

    return lista;
  }
}
