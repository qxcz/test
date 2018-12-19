package streamy.suborovaDatabaza;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public void showMenu(ArrayList<String> database) throws IOException {

        DB newDB = new DB();
        Property myproperty = new Property();
        String currentDB = myproperty.getProp("name");

        System.out.println("\nCurrent DB: " + currentDB + " (" + myproperty.getProp("recordCount") + " records)");
        System.out.println("1 - Create new DB.");
        System.out.println("2 - Load new DB.");
        System.out.println("3 - Add record.");
        System.out.println("4 - Delete record.");
        System.out.println("5 - Search.");
        System.out.println("6 - List all records.");
        System.out.println("D - Delete DB.");
        System.out.println("Q - Quit.");

        Scanner vstup = new Scanner(System.in);
        String k = vstup.nextLine();

        switch (k) {
            case "1":
                showMenu(newDB.loadDirect(newDB.createDB()));
                break;
            case "2":
                newDB.saveDB(database);
                showMenu(newDB.loadDB());
                break;
            case "3":
                showMenu(newDB.addRecord(database));
                break;
            case "4":
                showMenu(newDB.deleteRecord(database));
                break;
            case "5":
                break;
            case "6":
                showMenu(newDB.listDB(database));
                break;
            case "d":
                break;
            case "q":
                newDB.saveDB(database);
                System.exit(0);
            default:
                System.out.println("\nUnsupported option:");
                showMenu(database);
                break;
        }

    }


}
