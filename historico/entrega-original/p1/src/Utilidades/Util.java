/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Mapa_e_partida.Celda;
import Mapa_e_partida.Mapa;
import Personaje.Npcs;
import Personaje.Objeto;
import Personaje.Personaje;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {
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
        System.out.println(celda.toString());
      }
    }
    return lista;
  }

  public static ArrayList<Npcs> leerDatosPersonajes(String rutaFichero)
      throws FileNotFoundException {
    ArrayList<Npcs> lista = new ArrayList<>();
    Scanner scan = new Scanner(new File(rutaFichero));
    String linea;
    String partes[];
    while (scan.hasNextLine()) {
      linea = scan.nextLine();
      partes = linea.split(";");
      if (partes.length == 8) {
        int x = Integer.valueOf(partes[0].split(",")[0]);
        int y = Integer.valueOf(partes[0].split(",")[1]);
        Point pto = new Point(x, y);
        Npcs npcs =
            new Npcs(
                pto,
                partes[2],
                partes[1],
                Integer.parseInt(partes[3]),
                Integer.parseInt(partes[4]),
                Integer.parseInt(partes[5]),
                Integer.parseInt(partes[6]),
                partes[7]);
        lista.add(npcs);
        System.out.println(npcs.toString());
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
        System.out.println(objeto.toString());
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
      linea = scan.nextLine();
      // os comentarios non os procesamos
      if (!linea.startsWith("#")) {
        System.out.println("comando: " + linea);
        if (linea.contains("mover")) {
          orde = linea.split(" ")[1];
          String movimiento = pers.mover(mapa, orde);
          if (movimiento.equalsIgnoreCase("ok")) {
            System.out.println("posicion:" + pers.getPosicion());
            // pers.setRutaRecorrida(pers.getPosicion());
          } else {
            System.out.println("non se moveu:" + movimiento);
          }
        } else if (linea.contains("mirar")) {
          String objeto = "";
          // mostramos solo la descripcion de un objeto si pone p.e mirar espada, en caso de poner
          // mirar mostramos los que tenga la celda
          if (orde.split(" ").length > 1) {
            objeto = orde.split(" ")[1];
            System.out.println(mapa.mirarObjetoCelda(pers.getPosicion(), objeto));
          } else {
            System.out.println(mapa.mirarCelda(pers.getPosicion()));
          }
        } else if (linea.contains("coger")) {
          orde = linea.split(" ")[1];
          System.out.println(pers.getMochila().cogerObjeto(pers.getPosicion(), mapa, orde));
        } else if (linea.contains("tirar")) {
          orde = linea.split(" ")[1];
          System.out.println(pers.getMochila().tirarObjeto(pers.getPosicion(), mapa, orde));
        } else if (linea.contains("usar")) {
          orde = linea.split(" ")[1];
          Objeto obj = pers.getMochila().objetoBuscado(orde);
          if (obj == null) {
            System.out.println("no tienes el objeto en la mochila para usarlo");
          } else {
            if (obj.getTipo_objeto().equalsIgnoreCase("pocima")) {
              pers.setEnergia(pers.getEnergia() + obj.getEfecto());
              System.out.println("energia actual:" + pers.getEnergia());
              pers.getMochila().eliminarObjeto(obj);
            }
          }
        } else if (linea.contains("inventario")) {
          System.out.println(pers.getMochila().ojearInventario());
        }
      }
    }

    return lista;
  }
}
