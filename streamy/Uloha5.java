package streamy;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;


/** Vstupom metódy bude String predstavujúci cestu k súboru. Metóda zistí, či je daný String súbor alebo priečinok
    Na konzolu vypíš názov súboru alebo priečinku.

    Napíš program, ktorému zadáš cestu na priečinok. Program vypíše názvy všetkých súborov priamo v danom priečinku.

*/

public class Uloha5 {
    public static void main(String[] args) {

        ReadFilePath pat = new ReadFilePath();
        Path p = pat.enterNewFilePath();
        checkFile(p);
    }


  public static void checkFile(Path p){


      if (Files.isDirectory(p)){
          System.out.println("Cesta ukazuje na adresar " + p.toString());

          File folder = new File(p.toString());
          for( File s : folder.listFiles() ) {
              System.out.println(s.getName());
          }

      } else if (Files.isRegularFile(p)) {
          System.out.println("Cesta ukazuje na subor " + p.getFileName());
      }


  }


}
