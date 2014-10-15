package Documentacion;

/** Ayuda textual de la primera entrega. */
public final class Axuda {
  private Axuda() {}

  /**
   * @return listado de comandos disponible
   */
  public static String mostrar() {
    return """
    comandos:
    -movemento:
    norte
    sur
    este
    oeste

    -finalizar xogo
    fin

    -mirar
    mirar: permite mirar na celda na que está situado o personaxe se hai algún obxeto para recoller

    -mapa
    mapa: permite ver o mapa do xogo
     ☺ xogador
     X celda non transitable
     + obxetivo
    *********************************************************
    """;
  }
}
