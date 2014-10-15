package Personaje;

/** Objeto genérico de la primera entrega. */
public class Objeto {

  private String nombre;
  private double peso;
  private String tipo;
  private int efecto;

  public Objeto() {}

  /**
   * Constructo con catro parametros, nome do <b>obxeto</b>, peso, tipo de <i>obxeto</i>, efecto
   *
   * @param nombre String nome que recibe ese obxeto
   * @param peso
   * @param tipo
   * @param efecto
   */
  public Objeto(String nombre, double peso, String tipo, int efecto) {
    this.setNombre(nombre);
    this.setPeso(peso);
    this.setTipo(tipo);
    this.setEfecto(efecto);
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    if (nombre.length() < 100) {
      this.nombre = nombre;
    } else {
      System.out.println("El nombre es demasiado largo");
    }
  }

  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    if (peso < 300 && peso > 0) {
      this.peso = peso;
    } else {
      System.out.println("El peso debe tomar un valor entre 0 y 300 kg");
    }
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public int getEfecto() {
    return efecto;
  }

  public void setEfecto(int efecto) {
    if (efecto > -100 && efecto < 100) {
      this.efecto = efecto;
    }
  }

  @Override
  public String toString() {
    String retorno = this.nombre == null ? "" : this.getNombre();
    retorno += ", " + this.peso;
    retorno += ", " + this.tipo;
    retorno += ", " + this.efecto;
    return retorno;
  }
}
