package streamy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * zmaže súbor na základe zadaného Stringu, ktorý ako hodnotu má cestu k súboru.
 *
 *
 *
 */

public class Uloha7 {

    public static void main(String[] args) throws IOException {

        ReadFilePath pat = new ReadFilePath();
        Path p = pat.enterNewFilePath();

        checkFile(p);
        menu(p);


    }

    public static void checkFile(Path p) throws IOException {

        CountSize fileSize = new CountSize();
        String[] args = {};

        if (Files.isDirectory(p)) {
            System.out.println("Cesta ukazuje na adresar " + p.toString());

            File folder = new File(p.toString());
            for (File s : folder.listFiles()) {
                System.out.println(s.getName());
            }
            main(args);
        } else if (Files.isRegularFile(p)) {
            System.out.println("Cesta ukazuje na subor " + p.getFileName());
            System.out.println("\nVelkost suboru je " + fileSize.getSize(p) + " bajtov.");
        }   else {
            System.out.println("\nSubor neexistuje.\n");
            main(args);
        }


    }

    public static void menu(Path p) throws IOException {

        Scanner vstup = new Scanner(System.in);

        System.out.println("\nZ - zmazat; \nQ - quit; \nN - novy subor;\n");
        String k = vstup.nextLine();
        String[] args = {};

        switch (k) {
            case "z":
                Files.deleteIfExists(p);
                System.out.println("Subor bol zmazany.\n");
                main(args);
            case "q":
                System.exit(0);
            case "n":
                main(args);
            default:
                System.out.println("Neznama volba.");
                menu(p);
        }

    }


}
