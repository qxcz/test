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
      System.out.println("Enter new DB name:");
      k = vstup.nextLine();
      builder.append(defaultParentPath.toString()).append("\\").append(k).append(defaultExtension);
      Path dbPath = Paths.get(builder.toString().trim());
      if (Files.isRegularFile(dbPath)) {
        System.out.println("File already exists!");
      } else {
        String dbName = (builder1.append(k).append(defaultExtension)).toString();
        List<String> lines = Arrays.asList(dbName, "0", "priezvisko;meno;telefon1;telefon2;email");
        Files.write(dbPath, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        myProperty.createPropertyFile(dbPath);
        System.out.println(dbPath);
        return dbPath;
      }
    }

  }


  public Path setPath() {
    System.out.println("Set valid path: ");
    Path newPath = Paths.get(vstup.nextLine());
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
      default:
        System.out.println("Unknown option!");
        loadDB();
    }

    Pattern p = Pattern.compile(".+\\.(dbf)$"); //regexp pre .dbf
    String record;
    BufferedReader reader = null;

    System.out.println("\nExisting DB files: ");

    File folder = new File(defaultParentPath.toString());
    ArrayList<String> dbList = new ArrayList<>();
    dbList.clear();
    //Menu menu = new Menu();

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

      ArrayList<String> database = new ArrayList<String>();

      try {
        reader = new BufferedReader((new FileReader(newDBPath.toString())));
        int lines = 0;
        while ((record = reader.readLine()) != null) {
          lines++;
          if (lines > 3) {
            database.add(record);
          }
        }
        Integer recordCount = lines - 3;
        myProperty.setDbName(k);
        myProperty.setDbRecordCount(recordCount);
        myProperty.setDbPath(defaultParentPath.toString());
        myProperty.saveProperty();

      } finally {
        if (reader != null) {
          reader.close();
        }
      }

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

    return database;
  }


  public void checkDBCfg() throws IOException {

    File config = new File(myConstanst.getDefaultConfig().toString());
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
          menu.showMenu(loadDirect(activeDBpath));
        } else {
          System.out.printf("Configuration File Error: Path points to a non existing file!");
          menu(menu);
        }

      } catch (Exception e) {
        System.out.printf("Configuration File Error: file damaged!");
        menu(menu);
      }

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
        menu.showMenu(loadDirect(createDB()));
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
    if (newDBfile.exists() && newDBfile.isFile()) {

      BufferedReader reader = new BufferedReader(new FileReader(p.toString()));
      String firsLine = reader.readLine();
      reader.close();
      if (!firsLine.equals(dbName)) {
        System.out.println("\nIncorrect DB file format! Create new DB, or load another file.");
        myProperty.setDbName("");
        myProperty.setDbRecordCount(0);
        myProperty.setDbPath("");
        myProperty.saveProperty();
        checkDBCfg();
      }
    } else {
      System.out.println("\nFile does not exist! Create new DB, or load another file.");
      myProperty.setDbName("");
      myProperty.setDbRecordCount(0);
      myProperty.setDbPath("");
      myProperty.saveProperty();
      checkDBCfg();
    }
  }

  public ArrayList<String> addRecord(ArrayList<String> database) throws IOException {

    Osoba osoba = new Osoba();

    System.out.print("\nPriezvisko: ");
    osoba.setPriezvisko(vstup.nextLine());
    System.out.print("Meno: ");
    osoba.setMeno(vstup.nextLine());
    System.out.print("Telefon 1: ");
    osoba.setTelefon1(vstup.nextLine());
    System.out.print("Telefon 2: ");
    osoba.setTelefon2(vstup.nextLine());
    System.out.print("email: ");
    osoba.setEmail(vstup.nextLine());

    StringBuilder builder = new StringBuilder();
    builder.append(osoba
      .getPriezvisko()).append(";")
      .append(osoba.getMeno()).append(";")
      .append(osoba.getTelefon1()).append(";")
      .append(osoba.getTelefon2()).append(";")
      .append(osoba.getEmail());

    database.add(builder.toString());

    Integer newRecordcount = Integer.parseInt(myProperty.getProp("recordCount")) + 1;

    myProperty.setCount(newRecordcount);
    return database;

  }

  public ArrayList<String> listDB(ArrayList<String> database) {

    for (int i = 0; i < database.size(); i++) {
      String record = database.get(i);
      String[] splitLine = record.split(";");
      System.out.println(i + 1 +
        ".  " + splitLine[0]
        + ", " + splitLine[1]
        + ", " + splitLine[2]
        + ", " + splitLine[3]
        + ", " + splitLine[4]);

    }
    return database;
  }

  public void saveDB(ArrayList<String> database) throws IOException {


    String name = myProperty.getProp("name");
    String recordCount = myProperty.getProp("recordCount");
    String dbPath = myProperty.getProp("path");
    String header = myConstanst.getHeader();
    StringBuilder builder = new StringBuilder();

    builder.append(dbPath).append("\\").append(name);
    Path p = Paths.get(builder.toString());
    Files.deleteIfExists(p);
    database.add(0, name);
    database.add(1, recordCount);
    database.add(2, header);
    Files.write(p, database, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
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

  public ArrayList<String> searchDB (ArrayList<String> database){
    System.out.println("String to search: ");
    String searchPattern = vstup.nextLine().trim().toLowerCase();
    ArrayList<String> searchList = new ArrayList<>();
    Integer counter = 0;
    Pattern p = Pattern.compile(".+"+searchPattern+".+"); //regexp
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
    if (searchList.size()==0){
      System.out.println("No records found!");
    }else {
      System.out.println("Found " + searchList.size() + " records:\n");
      for (String found : searchList){
        System.out.println(found);
      }
    }
    return database;
  }
}
//

//    public void searchDB() {
//
//    }

