package streamy.suborovaDatabaza;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DB {


    public Path createDB() throws IOException {

        Path defaultParentPath = new Constants().getDefaultParentPath();

        String defaultExtension = ".dbf";
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();

        System.out.println("\nCreating new DB!");
        System.out.println("Enter name:");
        Scanner vstup = new Scanner(System.in);
        String k = vstup.nextLine();

        builder.append(defaultParentPath.toString()).append("\\").append(k).append(defaultExtension);
        Path dbPath = Paths.get(builder.toString().trim());
        String dbName = (builder1.append(k).append(defaultExtension)).toString();

        List<String> lines = Arrays.asList(dbName, "0", "priezvisko;meno;telefon1;telefon2;email");
        Files.write(dbPath, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        new Property().createPropertyFile(k);

        System.out.println(dbPath);
        return dbPath;

    }

    public Path loadDB() throws IOException {

        Path defaultParentPath = new Constants().getDefaultParentPath();
        defaultParentPath = (!Files.isDirectory(defaultParentPath)) ? defaultParentPath.getParent() : defaultParentPath;
        Pattern p = Pattern.compile(".+\\.(dbf)$"); //regexp pre .dbf

        System.out.println("\nExisting DB files: ");

        File folder = new File(defaultParentPath.toString());
        ArrayList<String> dbList = new ArrayList<>();
        dbList.clear();
        Menu menu = new Menu();

        for (File s : folder.listFiles()) {

            Matcher m = p.matcher(s.toString());
            if (m.matches()) {
                System.out.println(s.getName());
                dbList.add(s.getName());
            }
        }
        if (dbList.size() == 0) {
            System.out.println("\nNo DB to open:");
            checkDBCfg();
        } else {
            System.out.println("\nEnter DB name:");
            Scanner vstup = new Scanner(System.in);
            String k = vstup.nextLine();
            StringBuilder builder = new StringBuilder();
            Path newDBPath = Paths.get((builder.append(defaultParentPath.toString()).append("\\").append(k)).toString());

            checkDBFile(newDBPath);

            BufferedReader reader = new BufferedReader((new FileReader(newDBPath.toString())));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();
            Integer recordCount = lines - 3;

            new Property().setProperty(k, recordCount.toString());

            return newDBPath;
        }
        return null;
    }


    public void checkDBCfg() throws IOException {

        Path defaultDBConfig = new Constants().getDefaultConfig();
        File config = new File(defaultDBConfig.toString());
        Menu menu = new Menu();

        if (config.exists()) {
            String openDB = new Property().getProperty("name");

            if (!openDB.equals("")) {

                Path defaultPath = new Constants().getDefaultParentPath();
                StringBuilder builder = new StringBuilder();
                builder.append(defaultPath.toString()).append("\\").append(openDB);
                Path activeDBpath = Paths.get(builder.toString());
                checkDBFile(activeDBpath);
                menu.showMenu(activeDBpath);
            } else menu(menu);

        } else {
            menu(menu);
        }
    }

    private void menu(Menu menu) throws IOException {
        System.out.println("\nNo active DB.");
        System.out.println("1 - Create new DB.");
        System.out.println("2 - Open existing DB.");
        Scanner vstup = new Scanner(System.in);
        String k = vstup.nextLine();

        switch (k) {
            case "1":
                menu.showMenu(createDB());
                break;
            case "2":
                menu.showMenu(loadDB());
                break;
            default:
                System.out.println("\nUnsupported option:");
                checkDBCfg();
        }
    }

    public void checkDBFile(Path p) throws IOException {

        String dbName = p.getFileName().toString();
        File newDBfile = new File(p.toString());
        Property property = new Property();
        if (newDBfile.exists() && newDBfile.isFile()) {

            BufferedReader reader = new BufferedReader(new FileReader(p.toString()));
            String firsLine = reader.readLine();
            if (!firsLine.equals(dbName)) {
                System.out.println("\nIncorrect DB file format! Create new DB, or load another file.");

                property.setProperty("", "");
                checkDBCfg();
            }
        } else {
            System.out.println("\nFile does not exist! Create new DB, or load another file.");
            property.setProperty("", "");
            checkDBCfg();
        }
    }

    public void addRecord(Path p) throws IOException {

        Osoba osoba = new Osoba();
        Menu menu = new Menu();
        Property property = new Property();

        Scanner vstup = new Scanner(System.in);
        System.out.println("\nPriezvisko: ");
        osoba.setPriezvisko(vstup.nextLine());
        System.out.println("Meno: ");
        osoba.setMeno(vstup.nextLine());
        System.out.println("Telefon 1: ");
        osoba.setTelefon1(vstup.nextLine());
        System.out.println("Telefon 2: ");
        osoba.setTelefon2(vstup.nextLine());
        System.out.println("email: ");
        osoba.setEmail(vstup.nextLine());

        StringBuilder builder = new StringBuilder();
        builder.append(osoba
                .getPriezvisko()).append(";")
                .append(osoba.getMeno()).append(";")
                .append(osoba.getTelefon1()).append(";")
                .append(osoba.getTelefon2()).append(";")
                .append(osoba.getEmail());

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(p.toString(), true));
            out.write(builder.toString());
            out.newLine();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer newRecordcount = Integer.parseInt(property.getProperty("recordCount")) + 1;

        property.setProperty(property.getProperty("name"), newRecordcount.toString());
        menu.showMenu(p);

    }

    public void listDB(Path p) throws IOException {

        String s;
        BufferedReader in = null;
        Pattern r = Pattern.compile(";");

        try {
            in = new BufferedReader(new FileReader(p.toString()));
            int counter = 0;
            while ((s = in.readLine()) != null) {
                counter++;
                if (counter > 3) {
                    String[] splitLine = s.split(";");
                    System.out.println(counter - 3 +
                            ".  " + splitLine[0]
                            + ", " + splitLine[1]
                            + ", " + splitLine[2]
                            + ", " + splitLine[3]
                            + ", " + splitLine[4]);
                }
            }

        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
//
//
//    public void delRecord() {
//
//    }
//
//
//    public void searchDB() {
//
//    }

