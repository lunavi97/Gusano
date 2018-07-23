package edu.unlam.gusano;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.CompareFiles;

/**
 * Tests de la clase Gusano.
 */
public class GusanoTests {

  private Gusano gusano;

  private final String FOLDERLOTE = ".." + File.separator +
      "Preparacion de la Prueba" + File.separator + "Lote de Prueba";

  private final String FOLDERENTRADA = FOLDERLOTE +
      File.separator + "Entrada";

  private final String FOLDERSALIDAESPERADA = FOLDERLOTE +
      File.separator + "Salida Esperada";

  private final String FOLDERSALIDAOBTENIDA = ".." + File.separator +
      "Ejecucion de la Prueba" + File.separator + "Salida Obtenida";

  private File[] listaDeArchivos;

  private CompareFiles cf;

  @Before
  public void setUp() throws Exception {
    File folder = new File(FOLDERENTRADA);
    listaDeArchivos = folder.listFiles();
    cf = new CompareFiles();
  }

  /**
   * Ejecutar resolvedor de ejercicio por cada entrada y comparar
   * la salida esperada con la obtenida.
   * @throws FileNotFoundException
   */
  @Test
  public void test() throws FileNotFoundException {
    // Iterar por cada archivo de entrada
    for (int i = 0; i < listaDeArchivos.length; i++) {
      File fileEntrada = listaDeArchivos[i].getAbsoluteFile();

      String nombreEntrada = listaDeArchivos[i].getName();
      String nombreSalida = nombreEntrada.substring(0,
          nombreEntrada.length() - 2) + "out";
      String pathSalidaObtenida = FOLDERSALIDAOBTENIDA + 
          File.separator + nombreSalida;

      File fileSalidaObtenida = new File(pathSalidaObtenida);

      // Construir clase de ejercicio
      gusano = new Gusano(fileEntrada, fileSalidaObtenida);

      // Resolver
      gusano.resolver();

      File fileSalidaEsperada = new File(FOLDERSALIDAESPERADA +
          File.separator + nombreSalida);

      // Comparar el contenido de la salida esperada con el de la salida obtenida
      Assert.assertTrue("Tienen que tener igual contenido",
          cf.haveSameContent(fileSalidaEsperada, fileSalidaObtenida));
    }
  }

}
