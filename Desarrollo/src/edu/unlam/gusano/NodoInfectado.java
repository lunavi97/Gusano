package edu.unlam.gusano;

public class NodoInfectado {

  private int nodo;
  private int costo;

  public NodoInfectado(int nodo, int costo) {
    this.setNodo(nodo);
    this.setCosto(costo);
  }

  public int getNodo() {
    return nodo;
  }

  public void setNodo(int nodo) {
    this.nodo = nodo;
  }

  public int getCosto() {
    return costo;
  }

  public void setCosto(int costo) {
    this.costo = costo;
  }

}
