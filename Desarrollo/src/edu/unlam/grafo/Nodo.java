package edu.unlam.grafo;

public class Nodo implements Comparable<Nodo> {

  private int numero;
  private int costo;

  public Nodo(int numero, int costo) {
    this.setNumero(numero);
    this.costo = costo;
  }

  @Override
  public int compareTo(Nodo o) {
    return getCosto() - o.getCosto();
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }
  
  public int getCosto() {
    return costo;
  }
  
  public void setCosto(int costo) {
    this.costo = costo;
  }
}
