package Mapa_e_partida;

import Personaje.Objeto;
import Personaje.Personaje;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Mapa de veinte por veinte de la primera entrega. */
public class Mapa {
  private static final int TAMANO = 20;
  private final Map<Point, Celda> celdas = new HashMap<>();

  public Mapa(Personaje jugador) {
    rellenarCeldas(jugador);
  }

  private void rellenarCeldas(Personaje jugador) {
    int contTipo = 0;

    for (int i = 0; i < TAMANO; i++) {
      for (int j = 0; j < TAMANO; j++) {
        String tipoPasillo = "pasillo normal";
        if ((i + j) % 5 == 0) {
          tipoPasillo = "pasillo estrecho";
        }
        Celda celda = new Celda(tipoPasillo, new ArrayList<>(), true);
        if ((i + j) % 9 == 0 && i % 2 == 0) {
          celda.setTransitable(false);
        }
        // solo poñemos obxeto se a celda é transitable
        if (celda.isTransitable()) {
          // aleatorio, solo obxetos en algunha celdas
          if (j % 10 == 0) {
            int efecto = 0;
            double peso = 4;
            String tipoObj;
            while (peso > 2) {
              peso = peso / 2;
            }
            if (contTipo == 0) {
              tipoObj = "pocion de salud";
              efecto = 10;
            } else if (contTipo == 1) {
              tipoObj = "pocion de energía";
              efecto = 5;
            } else {
              tipoObj = "veneno";
              efecto = -5;
            }

            Objeto objeto = new Objeto("obxeto_" + i + j, peso, tipoObj, efecto);
            contTipo++;
            // reiniciamos conTipo ao chegar a 3 para que volva a poñer os tipos de pocion dede o 0
            if (contTipo == 3) {
              contTipo = 0;
            }
            ArrayList<Objeto> objetos = new ArrayList<>();
            objetos.add(objeto);
            celda.setObjetos(objetos);
          }
        }
        Point p = new Point(i, j);
        celdas.put(p, celda);
      }
    }
    this.imprimirMapa(jugador);
  }

  public Celda getCelda(Point coordenadas) {
    return this.celdas.get(coordenadas);
  }

  public void imprimirMapa(Personaje jugador) {
    Point actual = jugador.getPosicionActual();
    Point objetivo = new Point(TAMANO - 2, TAMANO - 1);

    for (int i = 0; i < TAMANO; i++) {
      for (int j = 0; j < TAMANO; j++) {

        Point p = new Point(i, j);
        if (p.equals(actual)) {
          System.out.print("\u263A ");

        } else if (p.equals(objetivo)) {
          System.out.print("+ ");
        } else if (celdas.get(p).isTransitable()) {
          if (celdas.get(p).getDescripcion().equals("pasillo estrecho")) {
            System.out.print("\u2653 ");
          } else {
            System.out.print("\u26F6 ");
          }
        } else {
          System.out.print("X ");
        }
      }
      System.out.println();
    }
  }
}
