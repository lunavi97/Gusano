package edu.unlam.grafo;

public class Grafo {

  private final int cantNodos;
  private MatrizSimetrica mat;
  
  public Grafo(final int cantNodos) {
    this.cantNodos = cantNodos;
    mat = new MatrizSimetrica(cantNodos);
  }

  public final int getCantNodos() {
    return cantNodos;
  }

  public void setConexion(int nodoInicial, int nodoFinal, int costo) {
    mat.setMatriz(nodoInicial, nodoFinal, costo);
  }
  
  public MatrizSimetrica getMatriz() {
    return mat;
  }
}
