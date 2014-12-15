/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package documentacion;

/**
 * @author miguel.alonso
 */
public final class Axuda {
  public static String Mostrar() {
    String sRetorno = "*********************************************************\n";
    sRetorno = "comandos:\n";
    sRetorno += "-movemento:\n";
    sRetorno += " norte\n";
    sRetorno += " sur\n";
    sRetorno += " este\n";
    sRetorno += " oeste\n";
    sRetorno += "\n-finalizar xogo\n";
    sRetorno += " fin\n";
    sRetorno += "\n-mirar\n";
    sRetorno +=
        " mirar: permite mirar na celda na que está situado o personaxe se hay algún obxeto para"
            + " recoller\n";
    sRetorno += "\n-mapa\n";
    sRetorno += " mapa: permite ver o mapa do xogo\n";
    sRetorno += "\n-inventario\n";
    sRetorno += " inventario: permite ver el inventario\n";
    sRetorno += "\n-coger\n";
    sRetorno += " coger nombre: permite cogere el objeto con ese nombre\n";
    sRetorno += "\n-tirar\n";
    sRetorno += " tirar nombre: permite tirar el objeto con ese nombre\n";
    sRetorno += "\n-usar\n";
    sRetorno += " usar nombre: permite usar el objeto con ese nombre\n";
    sRetorno += "\n-mapa parcial\n";
    sRetorno += " mapa_parcial: muestra el mapa parcial\n";
    sRetorno += "*********************************************************\n";
    return sRetorno;
  }
}
