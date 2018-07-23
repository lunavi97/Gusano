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
  
  public int getConexion(int nodoInicial, int nodoFinal) {
    return mat.getMatriz(nodoInicial, nodoFinal);
  }

  public void setConexion(int nodoInicial, int nodoFinal, int costo) {
    mat.setMatriz(nodoInicial, nodoFinal, costo);
  }
  
  public MatrizSimetrica getMatriz() {
    return mat;
  }
  
  public int costoTotal() {
    int costo = 0;
    final int cantFilas = getCantNodos();
    final int cantColumnas = cantFilas;
    
    for (int i = 0; i < cantFilas - 1; i++) {
      for (int j = i + 1; j < cantColumnas; j++) {
        costo = getConexion(i, j);
      }
    }
    
    return costo;
  }
}
