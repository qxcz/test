package streamy;

import java.awt.event.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Uloha1 {

    public static void main(String[] args) throws IOException {

        printFile();

    }

    public static String readFilePath() {

        Scanner vstup = new Scanner(System.in);
        System.out.print("Enter file location: ");
        String s = vstup.nextLine();
        return s.trim();
    }

    public static void printFile() throws IOException {
        String s;
        Path p = Paths.get(readFilePath());
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
        if (!quitProcess()) {
            printFile();
        }
    }

    public static boolean quitProcess() {

        while (true) {
            System.out.println("Q - quit  N - next file");
            Scanner vstup = new Scanner(System.in);
            char k = vstup.next().charAt(0);
            if (k == 'q') {
                return true;
            } else if (k == 'n') {
                return false;
            }
        }
    }


}
