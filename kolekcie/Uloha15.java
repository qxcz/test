package kolekcie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Uloha15 {

    public static void main(String[] args) {

        Collection<String> collection = new ArrayList<>();
        Collection<String> collection2 = new HashSet<>();

        ((ArrayList<String>) collection).add("prvy");
        ((ArrayList<String>) collection).add("druhy");
        ((ArrayList<String>) collection).add("treti");

        collection2.add("stvrty");
        collection2.add("piaty");
        collection2.add("siesty");

        printCollection(collection);
        printCollection(collection2);

    }

    public static void printCollection(Collection<String> nasaKolekcia){

        int i=1;
        for(String s : nasaKolekcia){
            System.out.printf("%d. %s\n",i,s);
            i++;
        }
    }

}
