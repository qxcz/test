package streamy.suborovaDatabaza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) throws IOException {

        DB newDB = new DB();
        Property myproperty = new Property();
        Scanner vstup = new Scanner(System.in);
        String currentDB = myproperty.getProp("name");
        String k;
        ArrayList<String> database;

        database = newDB.checkDBCfg();


        while (true) {
            System.out.println("\nActive DB: " + currentDB + " (" + myproperty.getProp("recordCount") + " records)");

            k = vstup.nextLine().trim();

            switch (k) {
                case "create":
                    database=newDB.loadDirect(newDB.createDB());
                    break;
                case "load":
                    newDB.saveDB(database);
                    database = newDB.loadDB();
                    break;
                case "add":
                    database = newDB.addRecord(database);
                    break;
                case "delrecord":
                    database = newDB.deleteRecord(database);
                    break;
                case "search":
                    newDB.searchDB(database);
                    break;
                case "list":
                    newDB.listDB(database);
                    break;
                case "deldb":
                    break;
                case "q":
                    newDB.saveDB(database);
                    System.exit(0);
                default:
                    System.out.println("Unknown command! Following commands adapted (so far):");
                    System.out.println("create - create new DB | load - load existing DB | deldb - delete existing DB | list - list all records | add - add new record | delrecord - delete record | search - search DB | q - quit");
                    break;
            }
        }
    }


}
