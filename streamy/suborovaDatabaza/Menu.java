package streamy.suborovaDatabaza;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

  public void showMenu(ArrayList<String> database) throws IOException {

    DB newDB = new DB();
    Property myproperty = new Property();
    Scanner vstup = new Scanner(System.in);
    String currentDB = myproperty.getProp("name");

    System.out.println("\nActive DB: " + currentDB + " (" + myproperty.getProp("recordCount") + " records)");

    String k = vstup.nextLine().trim();

    switch (k) {
      case "create":
        showMenu(newDB.loadDirect(newDB.createDB()));
        break;
      case "load":
        newDB.saveDB(database);
        showMenu(newDB.loadDB());
        break;
      case "add":
        showMenu(newDB.addRecord(database));
        break;
      case "delrecord":
        showMenu(newDB.deleteRecord(database));
        break;
      case "search":
        showMenu(newDB.searchDB(database));
        break;
      case "list":
        showMenu(newDB.listDB(database));
        break;
      case "deldb":
        break;
      case "q":
        newDB.saveDB(database);
        System.exit(0);
      default:
        System.out.println("Unknown command! Following commands adapted (so far):");
        System.out.println("create - create new DB | load - load existing DB | deldb - delete existing DB | list - list all records | add - add new record | delrecord - delete record | search - search DB | q - quit");
        showMenu(database);
        break;
    }

  }


}
