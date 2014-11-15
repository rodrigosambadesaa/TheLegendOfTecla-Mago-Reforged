/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Personaje;

import java.awt.Point;

/**
 * @author miguel.alonso
 */
/*public class Objeto {

private String nombre;
private double peso;
private String tipo;
private int efecto;

public Objeto() {
}


public Objeto(String nombre, double peso, String tipo, int efecto) {
this.setNombre(nombre);
this.setPeso(peso);
this.setTipo(tipo);
this.setEfecto(efecto);
}
//copy constructor
public Objeto(Objeto copia){
this.efecto=copia.getEfecto();
this.nombre=copia.getNombre();
this.peso=copia.getPeso();
this.tipo=copia.getTipo();
}

public String getNombre() {
return nombre;
}

public void setNombre(String nombre) {
if(nombre.length()<100)
this.nombre = nombre;
else
System.out.println("El nombre es demasiado largo");
}

public double getPeso() {
return peso;
}

public void setPeso(double peso) {
if(peso<300 && peso>0)
this.peso = peso;
else
System.out.println("El peso debe tomar un valor entre 0 y 300 kg");
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
if(efecto>-100 && efecto<100)
this.efecto = efecto;
}

@Override
public String toString() {
String retorno = "";
if(this.nombre != null) retorno += this.getNombre();
retorno += ", " + String.valueOf(this.peso);
retorno += ", " + this.tipo;
retorno += ", " + this.efecto;
return retorno;
}



}*/
public class Objeto {

  private Point posicionMapa;
  private String poseedor;
  private String tipo_objeto;
  private String nombre;
  private String descripcion;
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
    this.nombre = nombre;
    this.peso = peso;
    this.tipo = tipo;
    this.efecto = efecto;
  }

  public Objeto(String nombre, double peso) {
    this.nombre = nombre;
    this.peso = peso;
  }

  // copy constructor

  public Objeto(Objeto copia) {
    this.efecto = copia.getEfecto();
    this.nombre = copia.getNombre();
    this.peso = copia.getPeso();
    this.tipo = copia.getTipo();
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
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

  public Point getPosicionMapa() {
    return posicionMapa;
  }

  public void setPosicionMapa(Point posicionMapa) {
    this.posicionMapa = posicionMapa;
  }

  public String getPoseedor() {
    return poseedor;
  }

  public void setPoseedor(String poseedor) {
    this.poseedor = poseedor;
  }

  public String getTipo_objeto() {
    return tipo_objeto;
  }

  public void setTipo_objeto(String tipo_objeto) {
    this.tipo_objeto = tipo_objeto;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  //    @Override
  //    public String toString() {
  //        String retorno = "";
  //        if(this.nombre != null) retorno += this.getNombre();
  //        retorno += ", " + String.valueOf(this.peso);
  //        retorno += ", " + this.tipo;
  //        retorno += ", " + this.efecto;
  //        return retorno;
  //    }

  @Override
  public String toString() {
    return "Objeto{"
        + "posicionMapa="
        + posicionMapa
        + ", poseedor="
        + poseedor
        + ", tipo_objeto="
        + tipo_objeto
        + ", nombre="
        + nombre
        + ", descripcion="
        + descripcion
        + ", peso="
        + peso
        + ", tipo="
        + tipo
        + ", efecto="
        + efecto
        + '}';
  }
}
