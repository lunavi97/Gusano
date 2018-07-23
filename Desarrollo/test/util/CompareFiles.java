package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Clase comparadora de archivos.
 */
public class CompareFiles {

  public CompareFiles() {}

  /**
   * Comparar el contenido de dos archivos.
   * @param file1 - Primer archivo
   * @param file2 - Segundo archivo
   * @return true si tienen el mismo contenido; false si es distinto
   * @throws FileNotFoundException
   */
  public boolean haveSameContent(File file1, File file2) throws FileNotFoundException {
    Scanner sc1 = new Scanner(file1);
    Scanner sc2 = new Scanner(file2);

    while (sc1.hasNextLine() && sc2.hasNextLine()) {
      String str1 = sc1.nextLine();
      String str2 = sc2.nextLine();

      if (!str1.equals(str2)) {
        sc1.close();
        sc2.close();
        return false;
      }
    }

    if (sc1.hasNextLine() || sc2.hasNextLine()) {
      sc1.close();
      sc2.close();
      return false;
    }

    sc1.close();
    sc2.close();
    return true;
  }

}
