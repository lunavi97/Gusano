package edu.unlam.gusano;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

import edu.unlam.grafo.Arista;
import edu.unlam.grafo.Nodo;
import edu.unlam.grafo.Grafo;

public class Gusano extends EjercicioOIA {

  private Grafo grafo;
  private ArrayList<Integer> posiblesInfectadores;
  private HashMap<Integer, Integer> nodosInfectados;
  private HashMap<Integer, ArrayList<Integer>> adyacentes;

  public Gusano(File entrada, File salida) throws FileNotFoundException {
    super(entrada, salida);
    lectura();
  }

  @Override
  public void resolver() {
    buscarPosiblesInfectadores();
    try {
      escritura();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void escritura() throws IOException {
    PrintWriter pw = new PrintWriter(new FileWriter(salida));

    if (posiblesInfectadores == null) {
      pw.println("No hay gusanos");
    }
    else if (posiblesInfectadores.isEmpty()) {
      pw.println("Tiempos no coinciden");
    }
    else {
      for(Integer infectador : posiblesInfectadores){
        pw.println(infectador + 1);
      }
    }

    pw.close();
  }

  public void buscarPosiblesInfectadores(){
    if(!nodosInfectados.keySet().isEmpty()) {
      Iterator<Integer> iterador = nodosInfectados.keySet().iterator();
      int primerNodo = iterador.next();
      Integer[] distancia = dijkstra(primerNodo);
      posiblesInfectadores = buscarNodosSospechosos(distancia, nodosInfectados.get(primerNodo));
      determinarOrigenes(iterador);
    }
  }

  private void determinarOrigenes(Iterator<Integer> iterador) {
    while(iterador.hasNext()){
      int nodoActual = iterador.next();
      Integer[] distancia = dijkstra(nodoActual);
      evaluarSospechosos(distancia, nodoActual);
    }
  }

  private void evaluarSospechosos(final Integer[] distancia, final int nodoActual) {
    for(int i = posiblesInfectadores.size() - 1; i >= 0; i--){
      if(distancia[posiblesInfectadores.get(i)] != nodosInfectados.get(nodoActual)){
        posiblesInfectadores.remove(i);
      }
    }
  }

  private ArrayList<Integer> buscarNodosSospechosos(final Integer[] distancia, final Integer primerNodo) {
    ArrayList<Integer> nodosSospechosos = new ArrayList<>();
    for(int i=0; i < distancia.length; i++){
      if(distancia[i] == primerNodo){
        nodosSospechosos.add(i);
      }
    }
    return nodosSospechosos;
  }

  /**
   * Dijkstra
   * Para encontrar el camino más corto
   * O((|V|+|A|)log|V|)
   * @param origen - Vértice origen
   * @return Camino más corto desde el origen
   */
  public Integer[] dijkstra(int origen) {
    final int INFINITO = grafo.costoTotal() + 1;
    final int cantNodos = grafo.getCantNodos();

    Integer[] distancia = new Integer[cantNodos];
    boolean[] visitado = new boolean[cantNodos];

    for (int i = 0; i < cantNodos; i++) {
      distancia[i] = INFINITO;
    }
    distancia[origen] = 0;

    PriorityQueue<Nodo> pq = new PriorityQueue<Nodo>();

    pq.offer(new Nodo(origen, 0));
    while (!pq.isEmpty()) {
      Nodo nodoActual = pq.poll();
      int numNodoActual = nodoActual.getNumero();
      int costoNodoActual = nodoActual.getCosto();

      for(Integer numNodoAdyacente: grafo.getMatriz().obtenerAdyacentes(numNodoActual)){
        int costoArista = grafo.getConexion(numNodoActual, numNodoAdyacente);

        int distanciaCalculada = costoArista + costoNodoActual;
        if (!visitado[numNodoAdyacente] && 
            distanciaCalculada < distancia[numNodoAdyacente]) {

          distancia[numNodoAdyacente] = distanciaCalculada;
          Nodo nodoInsertar = new Nodo(
              numNodoAdyacente, distancia[numNodoAdyacente]);

          if (pq.contains(nodoInsertar)) pq.remove(nodoInsertar);
          pq.add(nodoInsertar);
        }
      }
    }

    return distancia;
  }


  private void lectura() throws FileNotFoundException {
    Scanner sc = new Scanner(entrada);
    ArrayList<Arista> aristas = new ArrayList<Arista>();
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
      if(!adyacentes.containsKey(nodoInicial)){
        adyacentes.put(nodoInicial, new ArrayList<Integer>());
      }
      adyacentes.get(nodoInicial).add(nodoFinal);
      if(!adyacentes.containsKey(nodoFinal)){
        adyacentes.put(nodoFinal, new ArrayList<Integer>());
      }
      adyacentes.get(nodoFinal).add(nodoInicial);
    }
    int cantidadDeNodos = Collections.max(adyacentes.keySet())+1;
    grafo = new Grafo(cantidadDeNodos);
    setearAristasDeGrafo(aristas);
    nodosInfectados = new HashMap<>();
    int cantidadDeInfectados = sc.nextInt();
    for(int j=0; j<cantidadDeInfectados; j++){
      nodosInfectados.put(sc.nextInt() - 1, sc.nextInt());
    }
    sc.close();
  }

  private void setearAristasDeGrafo(ArrayList<Arista> aristas) {
    for (Arista arista : aristas) {
      grafo.setConexion(arista.getNodoInicial(), arista.getNodoFinal(), arista.getCosto());
    }
  }

}
