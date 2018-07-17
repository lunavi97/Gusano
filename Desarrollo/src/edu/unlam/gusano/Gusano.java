package edu.unlam.gusano;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import edu.unlam.grafo.Arista;
import edu.unlam.grafo.CostoAlNodo;
import edu.unlam.grafo.Grafo;
import edu.unlam.grafo.Nodo;

public class Gusano extends EjercicioOIA {

  private ArrayList<Arista> aristas;
  private int cantNodos;
  private int cantNodosInfectados;
  private NodoInfectado[] nodosInfectados;
  private Grafo grafo;

  public Gusano(File entrada, File salida) throws FileNotFoundException {
    super(entrada, salida);
    lectura();
  }

  @Override
  public void resolver() {
    Integer[][] matrizResultados = new Integer[cantNodosInfectados][cantNodos];

    for (int i = 0; i < cantNodosInfectados; i++) {
      matrizResultados[i] = dijkstra(nodosInfectados[i].getNodo());
    }

    // Encontrar los nodos resultado
    for (int i = 0; i < cantNodosInfectados; i++) {
    }
  }

  /**
   * Reducir el costo de arista
   * @param distancia - Vector de distancias
   * @param predecesor - Vector de predecesores
   * @param v - Vértice origen
   * @param w - Vértice destino
   */
  private void relajarArista(Integer[] distancia, int v, int w) {
    int longitud = grafo.getMatriz().getMatriz(v, w);
    Integer valor = distancia[w];
    if (valor > distancia[v] + longitud) {
      distancia[w] = distancia[v] + longitud;
    }
  }

  /**
   * Dijkstra
   * Para encontrar el camino más corto
   * O((|V|+|A|)log|V|)
   * @param origen - Vértice origen
   * @return Camino más corto desde el origen
   */
  public Integer[] dijkstra(int origen) {
    Integer[] distancia = new Integer[cantNodos];
    distancia[origen] = 0;
    PriorityQueue<CostoAlNodo> pq = new PriorityQueue<CostoAlNodo>();

    pq.offer(new CostoAlNodo(origen, 0));
    while (!pq.isEmpty()) {
      CostoAlNodo actual = pq.poll();
      int vertice = actual.getNodo();
      int longitudCamino = actual.getCosto();

      if (longitudCamino == distancia[vertice]) {
        for (Integer i : grafo.getMatriz().obtenerAdyacentes(vertice)) {
          relajarArista(distancia, vertice, i);
          pq.offer(new CostoAlNodo(i, distancia[i]));
        }
      }
    }

    return distancia;
  }


  private void lectura() throws FileNotFoundException {
    Scanner sc = new Scanner(entrada);
    ArrayList<Nodo> listaDeNodosAuxiliar = new ArrayList<Nodo>();
    aristas = new ArrayList<Arista>();
    int cantAristas = sc.nextInt();

    for (int i = 0; i < cantAristas; i++) {
      int nodoInicial = sc.nextInt() - 1;
      int costo = sc.nextInt();
      int nodoFinal = sc.nextInt() - 1;
      if (nodoInicial > nodoFinal) {
        int aux = nodoFinal;
        nodoFinal = nodoInicial;
        nodoInicial = aux;
      }

      aristas.add(new Arista(nodoInicial, costo, nodoFinal));
      Nodo nIni = new Nodo(nodoInicial);
      if (listaDeNodosAuxiliar.contains(nIni)) {
        listaDeNodosAuxiliar.add(nIni);
      }
      Nodo nFin = new Nodo(nodoFinal);
      if (listaDeNodosAuxiliar.contains(nFin)) {
        listaDeNodosAuxiliar.add(nFin);
      }
    }

    cantNodos = listaDeNodosAuxiliar.size();
    grafo = new Grafo(cantNodos);
    setearAristas();

    cantNodosInfectados = sc.nextInt();
    nodosInfectados = new NodoInfectado[cantNodosInfectados];
    for (int i = 0; i < nodosInfectados.length; i++) {
      nodosInfectados[i] = new NodoInfectado(sc.nextInt() - 1, sc.nextInt());
    }

    sc.close();
  }

  private void setearAristas() {
    for (Arista arista : aristas) {
      grafo.setConexion(arista.getNodoInicial(), arista.getNodoFinal(), arista.getCosto());
    }
  }

}
