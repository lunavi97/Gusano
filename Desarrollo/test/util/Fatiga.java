package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class Fatiga {

  private final int costo = 50;
  private final int cantAristas = 30000;
  private final int cantNodos = 1000;
  private final int cantNodosInfectados = 100;
  private final int horaArribo = 20000;
  
  public Fatiga() {}

  public void crearArchivo(File archivo) throws IOException {
    PrintWriter pw = new PrintWriter(new FileWriter(archivo));
    ThreadLocalRandom random = ThreadLocalRandom.current();

    pw.println(cantAristas);
    for(int i = 0; i < cantAristas; i++){
      pw.println(random.nextInt(1, cantNodos + 1) +
          " " + costo + " " + random.nextInt(1, cantNodos + 1));
    }

    pw.println(cantNodosInfectados);
    for(int i = 0; i < cantNodosInfectados; i++){
      pw.println(random.nextInt(1, cantNodos + 1) + " " + horaArribo);
    }

    pw.close();
  }

  public static void main(String[] args) throws IOException {
    Fatiga fatiga = new Fatiga();
    String lote = ".." + File.separator + "Preparacion de la Prueba" +
        File.separator + "Lote de Prueba";
    File fileFatiga = new File(lote + File.separator +
        "Entrada" + File.separator + "08_fatiga.in");
    fatiga.crearArchivo(fileFatiga);
  }

}
