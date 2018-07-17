package edu.unlam.grafo;

public class CostoAlNodo implements Comparable<CostoAlNodo> {

  private int nodo;
  private int costo;

  public CostoAlNodo(int nodo, int costo) {
    this.setNodo(nodo);
    this.costo = costo;
  }

  @Override
  public int compareTo(CostoAlNodo o) {
    return getCosto() - o.getCosto();
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
