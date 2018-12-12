package streamy;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;

/**
 * zmaže súbor na základe zadaného Stringu, ktorý ako hodnotu má cestu k súboru.
 * skopíruje súbor z jedného priečinku do druhého.
 * ytvorí nový súbor s týmto názvom
 */

public class UlohyFileOperations3 {

    static ReadFilePath pat = new ReadFilePath();

    public static void main(String[] args) throws IOException {

        Path p = pat.enterNewFilePath();
        menu(p);


    }

    public static void checkFile(Path p) throws IOException {

        CountSize fileSize = new CountSize();
        String[] args = {};

        if (Files.isDirectory(p)) {
            System.out.println("Cesta ukazuje na adresar " + p.toString());


            menu(p);
        } else if (Files.isRegularFile(p)) {
            System.out.println("\nCesta ukazuje na subor " + p.getFileName());
            System.out.println("Velkost suboru je " + fileSize.getSize(p) + " bajtov.");
            menu(p);
        } else {
            System.out.println("\nSubor neexistuje.\n");
            main(args);
        }

    }

    public static void listParent(Path p) throws IOException{

        p = (!Files.isDirectory(p)) ? p.getParent() : p;

        System.out.println("Zoznam suborov v adresari: " + p.toString()+"\n");
        File folder = new File(p.toString());
        for (File s : folder.listFiles()) {
            if (s.isDirectory()) {
                System.out.println("(D)"+s.getName());
            }
            else System.out.println(s.getName()+" "+ s.getTotalSpace());
        }
        menu(p);
    }


    public static void menu(Path p) throws IOException {

        Scanner vstup = new Scanner(System.in);

        System.out.println("\n("+p.toString()+")");

        System.out.println("Z - zmazat; K - kopirovat; L - list parent; N - novy subor; V - vytvorit subor; I - info o subore; Q - quit; ");
        String k = vstup.nextLine();
        String[] args = {};

        switch (k) {
            case "z":
                Files.deleteIfExists(p);
                System.out.println("Subor bol zmazany.\n");
                main(args);
                break;
            case "q":
                System.exit(0);
                break;
            case "n":
                main(args);
                break;
            case "k":
                copy(p);
                break;
            case "v":
                createFile(getName());
                break;
            case "i":
                checkFile(p);
                break;
            case "l":
                listParent(p);
                break;
            default:
                System.out.println("Neznama volba.");
                menu(p);
                break;
        }

    }

    public static void copy(Path p) throws IOException {

        System.out.println("\nKopirovat do.");
        Path copyPath = pat.enterNewFilePath();

        StringBuilder builder = new StringBuilder();
        builder.append(copyPath).append("\\").append(p.getFileName());
        Path buildPath = Paths.get(builder.toString());

        if (Files.isDirectory(copyPath) && Files.isRegularFile(p)) {
            Files.copy(p, buildPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nHotovo!");

            System.out.println(copyPath + ":\n");
            File folder = new File(copyPath.toString());
            for (File s : folder.listFiles()) {
                System.out.println(s.getName());
            }
        } else System.out.println("\nChyba!");

        menu(p);

    }
    public static String getName(){
        Scanner vstup = new Scanner(System.in);
        System.out.println("\nNazov suboru: ");
        String k = vstup.nextLine();
        k = (k.length()<=1) ? "default.txt" : k;
        return k;


    }

    public static void createFile(String fileName) throws IOException{

        Path createPath = pat.enterNewFilePath();


        if (!Files.isDirectory(createPath)){
            System.out.println("\nNeplatny nazov.\n");
            createFile(fileName);
        }
        else {
            StringBuilder builder = new StringBuilder();
            builder.append(createPath).append("\\").append(fileName);
         //   System.out.println(builder);

            Path copyPath = Paths.get(builder.toString());
            String data = "";
            Files.write(copyPath,data.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            System.out.println("\nHotovo!\n");
            menu(copyPath);
        }

    }


}
