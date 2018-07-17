package edu.unlam.grafo;

public class Arista implements Comparable<Arista> {

  private final int nodoInicial, costo, nodoFinal;

  public Arista(int nodoInicial, int costo, int nodoFinal) {
    this.nodoInicial = nodoInicial;
    this.costo = costo;
    this.nodoFinal = nodoFinal;
  }

  @Override
  public int compareTo(Arista o) {
    return getCosto() - o.getCosto();
  }

  public final int getNodoInicial() {
    return nodoInicial;
  }

  public final int getNodoFinal() {
    return nodoFinal;
  }

  public final int getCosto() {
    return costo;
  }

}
