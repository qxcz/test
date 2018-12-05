package streamy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 prečíta jednoduchý textový súbor a vypíše jeho text na konzolu.
 k textovému súboru pridá ďalší riadok.
 nájde najdlhšie slovo z textového súboru a vypíše ho na konzolu.
 vypíše veľkosť súboru na konzolu.
*/

public class Uloha3 {

    public static void main(String[] args) throws IOException {

        readFilePath();

    }


    public static void readFilePath() throws IOException {

        Scanner vstup = new Scanner(System.in);
        System.out.print("Enter file location: ");
        Path p = Paths.get(vstup.nextLine().trim());

        findLongestWord(p);
    }

    public static void printFile(Path p) throws IOException {

        String s;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(p.toString()));
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void findLongestWord (Path p) throws IOException {

        String s;
        FileInputStream fileInputStream = new FileInputStream(p.toString());
        Scanner scanner = new Scanner(fileInputStream);
        Pattern pa = Pattern.compile("[a-zA-Z0-9]+");
        scanner.useDelimiter("\\s+");
        List<String> list = new ArrayList<>();
        s = "";

        while (scanner.hasNext()) {
            String k = scanner.next();
            if (k.length() > s.length()) {
                Matcher ma = pa.matcher(k);
                if (ma.matches()) {
                    s = k;
                    list.clear();
                    list.add(k);
                }
            } else if (k.length() == s.length()) {
                Matcher ma = pa.matcher(k);
                if (ma.matches()) {
                    list.add(k);
                }
            }
        }
        System.out.println("\nNajdlhsie slovo(a) v subore maju dlzku " + s.length() + " znakov:\n");
        for (String slovo : list) {
            System.out.println(slovo);
        }
        countSize(p);
        quitProcess(p);

    }

    public static void quitProcess(Path p) throws IOException {

        Scanner vstup = new Scanner(System.in);
        String k = null;
        while (true) {
            System.out.println("\nN - next file; \nA - append text; \nP - print file; \nQ - quit;  \n");
            k = vstup.nextLine();
            if (k.equals("q")) {
                System.exit(0);
            } else if (k.equals("n")) {
                readFilePath();
            } else if (k.equals("a")) {
                addLine(p);
            } else if (k.equals("p")){
                printFile(p);
            }

        }

    }

    public static void addLine(Path p) throws IOException {

        Scanner vstup = new Scanner(System.in);
        System.out.println("Enter text to add: ");

        while (true) {
            String s = vstup.nextLine();

            if (s.equals("q")) {
                break;
            }
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(p.toString(), true));
                out.write(s);
                out.newLine();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printFile(p);

    }

    public static void countSize(Path p) throws IOException{

        FileInputStream in = null;
        FileOutputStream out = null;
        int c =0;

        try {
            in = new FileInputStream(p.toString());

            while ((in.read()) !=-1){ c++;}
        } finally {
            if (in !=null){in.close();}
        }
        System.out.println("\nVelkost suboru je " + c + " bajtov." );
    }

}
