package streamy.suborovaDatabaza;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Menu {

    public void showMenu(Path p) throws IOException {

        DB newDB = new DB();

        System.out.println("\nCurrent DB: " + p.getFileName().toString() + " (" + new Property().getProperty("recordCount") + " records)");
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
                showMenu(newDB.createDB());
                break;
            case "2":
                showMenu(newDB.loadDB());
                break;
            case "3":
                newDB.addRecord(p);
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                newDB.listDB(p);
                showMenu(p);
                break;
            case "d":
                break;
            case "q":
                System.exit(0);
            default:
                System.out.println("\nUnsupported option:");
                showMenu(p);
        }

    }


}
