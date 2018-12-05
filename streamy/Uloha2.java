package streamy;

        import java.io.*;
        import java.nio.file.Path;
        import java.nio.file.Paths;
        import java.util.Scanner;

public class Uloha2 {

    public static void main(String[] args) throws IOException {

        readFilePath();

    }

    public static void readFilePath() throws IOException {

        Scanner vstup = new Scanner(System.in);
        System.out.print("Enter file location: ");
        Path p = Paths.get(vstup.nextLine());
        printFile(p);
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
        quitProcess(p);

    }

    public static void quitProcess(Path p) throws IOException {

        Scanner vstup = new Scanner(System.in);
        String k = null;
        while (true) {
            System.out.println("Q - quit;  N - next file; A - append text");
            k = vstup.nextLine();
            if (k.equals("q")) {
                System.exit(0);
            } else if (k.equals("n")) {
                readFilePath();
            } else if (k.equals("a")) {
                addLine(p);
            }
        }

    }

    public static void addLine(Path p) throws IOException {

        Scanner vstup = new Scanner(System.in);
        System.out.println("Enter text to add: ");

        while (true){
            String s = vstup.nextLine();

            if(s.equals("q")){break;}

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(p.toString(),true));
                out.write(s);
                out.newLine();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        printFile(p);

    }

}
