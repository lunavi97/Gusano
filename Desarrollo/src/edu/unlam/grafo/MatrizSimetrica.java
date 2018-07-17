package edu.unlam.grafo;

import java.util.ArrayList;

public class MatrizSimetrica {

  private Integer vec[];
  private int cantNodos;

  public MatrizSimetrica(int cantNodos) {
    this.cantNodos = cantNodos;
    int longitud = (cantNodos * (cantNodos - 1)) / 2;
    vec = new Integer[longitud];
  }

  public Integer getMatriz(int i, int j) {
    return vec[(int) (i * cantNodos + j - (Math.pow(i, 2) + i * 3 + 2) / 2)];
  }

  public void setMatriz(int i, int j, int costo) {
    vec[(int) (i * cantNodos + j - (Math.pow(i, 2) + i * 3 + 2) / 2)] = costo;
  }

  public ArrayList<Integer> obtenerAdyacentes(int nodo) {
    ArrayList<Integer> list = new ArrayList<Integer>();

    for (int i = 0; i < nodo; i++) {
      if (getMatriz(i, nodo) != null) list.add(i);
    }

    for (int i = nodo + 1; i < cantNodos; i++) {
      if (getMatriz(nodo, i) != null) list.add(i);
    }

    return list;
  }

}
