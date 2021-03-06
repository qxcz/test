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

    Property myProperty = new Property();
    Constants myConstanst = new Constants();
    Scanner vstup = new Scanner(System.in);


    public Path createDB() throws IOException {

        Path defaultParentPath = myConstanst.getDefaultParentPath();

        System.out.println("\nCreating new DB!");
        System.out.println("Default path: " + defaultParentPath + " (Enter - OK | C - Change path)");
        String k = vstup.nextLine();
        switch (k) {

            case "c":
                defaultParentPath = setPath();
                break;
            case "":
                break;
            default:
                System.out.println("Unknown option!");
                createDB();
        }

        String defaultExtension = ".dbf";
        StringBuilder builder = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();

        while (true) {
            System.out.println("Enter DB name:");
            k = vstup.nextLine();
            builder.append(defaultParentPath.toString()).append("\\").append(k).append(defaultExtension);
            Path dbPath = Paths.get(builder.toString().trim());
            if (Files.isRegularFile(dbPath)) {
                System.out.println("Error: file already exists!");
            } else {
                String dbName = (builder1.append(k).append(defaultExtension)).toString();

                List<String> lines = Arrays.asList(dbName, "0", createHeader());
                Files.write(dbPath, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                myProperty.setDbName(dbName);
                myProperty.setDbPath(dbPath.getParent().toString());
                myProperty.setDbRecordCount(0);
                myProperty.setDbColumnCount(myConstanst.getHeaderCount());
                myProperty.saveProperty();
                return dbPath;
            }
        }

    }

    public Integer getHeader(Path p) throws IOException {

        BufferedReader reader = null;
        String record;
        char delimiter = ';';
        Integer count = 0;
        try {
            reader = new BufferedReader((new FileReader(p.toString())));
            int lines = 0;

            while ((record = reader.readLine()) != null) {
                lines++;
                if (lines == 3) {
                    myConstanst.setHeader(record);
                    for (int i = 0; i < record.length(); i++) {
                        if (record.charAt(i) == delimiter) {
                            count++;
                        }
                    }
                    return count + 1;
                }
            }

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

    public String createHeader() {

        int i = 0;
        String k;
        StringBuilder builder = new StringBuilder();
        do {
            i++;
            System.out.println("Enter name of the " + i + ". column (q-quit): ");
            k = vstup.nextLine();
            switch (k) {
                case "q":
                    break;
                case "":
                    System.out.println("Error: column name cannot be empty!");
                    i--;
                    break;
                default:
                    if (i == 1) {
                        builder.append(k);
                    } else {
                        builder.append(";").append(k);
                    }
                    ;
                    break;
            }
        } while (!k.equals("q"));
        System.out.println(builder);
        myConstanst.setHeader(builder.toString());
        myConstanst.setHeaderCount(i - 1);
        return builder.toString();
    }


    public Path setPath() {
        System.out.println("Set valid path: ");
        Path newPath = Paths.get(vstup.nextLine().trim());
        if (Files.isDirectory(newPath)) {
            return newPath;
        } else {
            System.out.println("Invalid path!");
            setPath();
        }
        return null;
    }

    public ArrayList<String> loadDB() throws IOException {

        Path defaultParentPath = myConstanst.getDefaultParentPath();
        System.out.println("Default lookup path: " + defaultParentPath + " (Enter - OK | C - Change path)");
        String in = vstup.nextLine();
        switch (in) {

            case "c":
                defaultParentPath = setPath();
                break;
            case "":
                break;
            case "q":
                return null;
            default:
                System.out.println("Unknown option!");
                loadDB();
        }

        Pattern p = Pattern.compile(".+\\.(dbf)$"); //regexp pre .dbf
        String record;
        BufferedReader reader = null;

        System.out.println("Existing DB files: ");

        File folder = new File(defaultParentPath.toString());
        ArrayList<String> dbList = new ArrayList<>();
        dbList.clear();
        //  Menu menu = new Menu();

        for (File s : folder.listFiles()) {

            Matcher m = p.matcher(s.toString());
            if (m.matches()) {
                System.out.println(s.getName());
                dbList.add(s.getName());
            }
        }
        if (dbList.size() == 0) {
            System.out.println("\nNo valid database file in folder!");
            checkDBCfg();
        } else {
            System.out.println("\nEnter DB name:");
            String k = vstup.nextLine();
            StringBuilder builder = new StringBuilder();
            Path newDBPath = Paths.get((builder.append(defaultParentPath.toString()).append("\\").append(k)).toString());


            checkDBFile(newDBPath);

            ArrayList<String> database = loadDirect(newDBPath);
            myProperty.setDbName(k);
            myProperty.setDbRecordCount(database.size());
            myProperty.setDbPath(defaultParentPath.toString());
            myProperty.setDbColumnCount(getHeader(newDBPath));
            myProperty.saveProperty();

            return database;
        }
        return null;
    }

    public ArrayList<String> loadDirect(Path p) throws IOException {

        ArrayList<String> database = new ArrayList<String>();
        BufferedReader reader = null;
        String record;

        try {
            reader = new BufferedReader(new FileReader(p.toString()));
            int lines = 0;
            while ((record = reader.readLine()) != null) {
                lines++;
                if (lines > 3) {
                    database.add(record);
                }
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        getHeader(p);
        return database;
    }


    public ArrayList<String> checkDBCfg() throws IOException {

        File config = new File(myConstanst.getDefaultConfig().toString());
        ArrayList<String> database;
        Menu menu = new Menu();

        if (config.exists()) {
            try {
                String openDB = myProperty.getProp("name");
                String path = myProperty.getProp("path");
                StringBuilder builder1 = new StringBuilder();
                builder1.append(path).append("\\").append(openDB);
                Path activeDBpath = Paths.get(builder1.toString());
                if (Files.isRegularFile(activeDBpath)) {

                    System.out.println(activeDBpath);
                    checkDBFile(activeDBpath);
                    return (loadDirect(activeDBpath));
                } else {
                    System.out.printf("Configuration File Error: Path points to a non existing file!");
                    menu();
                }

            } catch (Exception e) {
                System.out.printf("Configuration File Error: file corrupted!");
                menu();
            }

        } else {
            menu();
        }
        return null;
    }

    private ArrayList<String> menu() throws IOException {
        ArrayList<String> database;
        System.out.println("\nNo active DB.");
        System.out.println("1 - Create new DB.");
        System.out.println("2 - Open existing DB.");
        Scanner vstup = new Scanner(System.in);
        String k = vstup.nextLine();

        switch (k) {
            case "1":
                database = loadDirect(createDB());
                return database;
            case "2":
                database = loadDB();
                return database;
            default:
                System.out.println("\nUnsupported option:");
                checkDBCfg();
        }
        return null;
    }

    public void checkDBFile(Path p) throws IOException {

        String dbName = p.getFileName().toString();
        File newDBfile = new File(p.toString());
        if (newDBfile.exists() && newDBfile.isFile()) {

            BufferedReader reader = new BufferedReader(new FileReader(p.toString()));
            String firsLine = reader.readLine();
            reader.close();
            if (!firsLine.equals(dbName)) {
                System.out.println("\nError: Incorrect DB file format! Create new DB, or load another file.");
                myProperty.setDbName("");
                myProperty.setDbRecordCount(0);
                myProperty.setDbPath("");
                myProperty.saveProperty();
                checkDBCfg();
            }
        } else {
            System.out.println("\nError: File does not exist! Create new DB, or load another file.");
            myProperty.setDbName("");
            myProperty.setDbRecordCount(0);
            myProperty.setDbPath("");
            myProperty.saveProperty();
            checkDBCfg();
        }
    }

    public ArrayList<String> addRecord(ArrayList<String> database) throws IOException {

        String k;
        String header = myConstanst.getHeader();
        String[] data = header.split(";");
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + ": ");
            k = vstup.nextLine();
            if (i == 0) {
                builder.append(k);
            } else {
                builder.append(";").append(k);
            }
        }
        database.add(builder.toString());
        Integer newRecordcount = Integer.parseInt(myProperty.getProp("recordCount")) + 1;
        myProperty.setCount(newRecordcount);
        saveDB(database);
        return database;

    }

    public ArrayList<String> listDB(ArrayList<String> database) {

        System.out.println(myConstanst.getHeader().replaceAll(";", " | "));

        for (int i = 0; i < database.size(); i++) {
            String record = database.get(i).replaceAll(";", ", ");
            System.out.println(i + 1 + ". " + record);
        }
        return database;
    }


    public void saveDB(ArrayList<String> database) throws IOException {

        ArrayList<String> database2;
        database2 = (ArrayList<String>) database.clone();

        String name = myProperty.getProp("name");
        String recordCount = myProperty.getProp("recordCount");
        String dbPath = myProperty.getProp("path");
        String header = myConstanst.getHeader();

        StringBuilder builder = new StringBuilder();
        builder.append(dbPath).append("\\").append(name);

        Path p = Paths.get(builder.toString());
        Files.deleteIfExists(p);
        database2.add(0, name);
        database2.add(1, recordCount);
        database2.add(2, header);
        Files.write(p, database2, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.println("Saving! " + p);

    }

    public ArrayList<String> deleteRecord(ArrayList<String> database) {

        Scanner vstup = new Scanner(System.in);
        System.out.println("Select record to delete: ");
        int recordToDelete = vstup.nextInt();
        database.remove(recordToDelete - 1);
        Integer newRecordCount = Integer.parseInt(myProperty.getProp("recordCount")) - 1;
        myProperty.setCount(newRecordCount);
        return database;

    }

    public ArrayList<String> searchDB(ArrayList<String> database) {
        System.out.println("String to search: ");
        String searchPattern = vstup.nextLine().trim().toLowerCase();
        ArrayList<String> searchList = new ArrayList<>();
        Integer counter = 0;
        Pattern p = Pattern.compile(".*" + searchPattern + ".*"); //regexp
        StringBuilder builder = new StringBuilder();

        for (String s : database) {
            Matcher m = p.matcher(s.toLowerCase());
            counter++;
            if (m.matches()) {
                builder.append(counter.toString()).append(". ").append(s).toString();
                searchList.add(builder.toString());
                builder.setLength(0);
            }
        }
        if (searchList.size() == 0) {
            System.out.println("\nNo records found!");
        } else {
            System.out.println("\nFound " + searchList.size() + " records:");
            for (String found : searchList) {
                System.out.println(found.replaceAll(";", ", "));
            }
        }
        return database;
    }

    public ArrayList<String> editRecord(ArrayList<String> database) {

        System.out.println("Enter number of record to edit: ");
        String intToEdit = vstup.nextLine();
        if (intToEdit.equals("q")) {
            return database;
        }
        try {
            Integer recordToEdit = Integer.parseInt(intToEdit);

            if (recordToEdit > database.size() || recordToEdit <= 0) {
                System.out.println("Error: Record out of range!");
                editRecord(database);
            } else {
                StringBuilder builder = new StringBuilder();
                String header = myConstanst.getHeader();
                String[] headerData = header.split(";");
                String record = database.get(recordToEdit - 1);
                String[] recordData = record.split(";");

                for (int i = 0; i < recordData.length; i++) {
                    System.out.print("\n" + headerData[i] + " (" + recordData[i] + "): ");
                    String in = vstup.nextLine();

                    if (in.equals("")) {
                        if (i == 0) {
                            builder.append(recordData[i]);
                        } else {
                            builder.append(";").append(recordData[i]);
                        }
                    } else {
                        if (i == 0) {
                            builder.append(in);
                        } else {
                            builder.append(";").append(in);
                        }

                    }
                }
                database.remove(recordToEdit - 1);
                database.add(recordToEdit - 1, builder.toString());

            }
            return database;
        } catch (NumberFormatException e) {
            System.out.println("Error: incorrect value!");
            editRecord(database);
        }
        return null;
    }
}

