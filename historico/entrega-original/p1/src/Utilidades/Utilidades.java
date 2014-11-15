/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// autores: Miguel Alonso Castro, Rodrigo Sambade Saa

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

public class Utilidades {

  public static ArrayList<Celda> leerDatosMapa(String rutaFichero) throws FileNotFoundException {
    ArrayList<Celda> lista = new ArrayList<>();
    Scanner scan = new Scanner(new File(rutaFichero));
    String linea;
    String partes[];
    while (scan.hasNextLine()) {
      linea = scan.nextLine();
      partes = linea.split(";");
      if (partes.length == 3) {

        // conversion de string a punto
        String coordenadas[] = partes[0].split(",");
        Integer x;
        Integer y;
        x = Integer.parseInt(coordenadas[0]);
        y = Integer.parseInt(coordenadas[1]);
        Point punto = new Point(x, y);
        Celda celda = new Celda(punto, partes[1], partes[2]);
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
        Npcs npcs =
            new Npcs(
                partes[0],
                partes[1],
                partes[2],
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
                partes[2], Double.parseDouble(partes[7]), partes[5], Integer.parseInt(partes[6]));
        String coordenadas[] = partes[0].split(",");
        objeto.setPosicionMapa(
            new Point(Integer.parseInt(coordenadas[0]), Integer.parseInt(coordenadas[1])));
        objeto.setTipo_objeto(partes[3]);
        objeto.setDescripcion(partes[4]);
        objeto.setPoseedor(partes[1]);
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
    String movimento = "";
    while (scan.hasNextLine()) {
      linea = scan.nextLine();
      // os comentarios non os procesamos
      if (!linea.startsWith("#")) {
        if (linea.contains("mover")) {
          movimento = linea.substring(6);
          pers.mover(mapa, movimento);
          System.out.println("posicion actual: " + pers.getPosicion());
          // System.out.println(pers.mover(mapa, movimento));
        }
      }
    }

    return lista;
  }
}
