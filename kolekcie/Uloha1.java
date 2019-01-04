package kolekcie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Uloha1 {

  public static void main(String[] args) {

    Path p1 = Paths.get("c:\\DEV\\Test\\zoznam1.list");
    Path p2 = Paths.get("c:\\DEV\\Test\\zoznam2.list");
    Scanner vstup = new Scanner(System.in);
    List<String> zoznam1 = new ArrayList<>();
    List<String> zoznam2 = new ArrayList<>();
    String k ;

//    while(true){
//      System.out.print("meno: ");
//      k = vstup.nextLine().trim();
//      if (k.equals("q")){break;}
//      else {zoznam.add(k);}
//    }
//    printList(zoznam);
//
//    System.out.println("vloz na prve miesto: ");
//    k = vstup.nextLine();
//    zoznam.add(0,k);
//    printList(zoznam);
//
//    System.out.println("nahrad na 2. miesto: ");
//    k = vstup.nextLine();
//    zoznam.remove(1);
//    zoznam.add(1,k);
//    printList(zoznam);
//
//    saveList(zoznam);

    loadList(zoznam1,p1);
    loadList(zoznam2,p2);
    printList(zoznam1);

    Collections.swap(zoznam1,1,2);
    printList(zoznam1);

    printList(zoznam2);

    System.out.println(zoznam1.isEmpty());

    while (true) {
      System.out.println("\nhladaj meno: ");
      k = vstup.nextLine();
      if(k.equals("q")){break;}
      searchList(zoznam1, k);
    }
    zoznam1.sort(Comparator.naturalOrder());
    printList(zoznam1);

    zoznam2.addAll(zoznam1);
    saveList(zoznam2,p2);

  }

  public static void searchList(List<String> zoznam, String meno){

    if(zoznam.contains(meno)){
      System.out.printf("%s je na %d. pozici.",meno,zoznam.indexOf(meno));
    }
    else{
      System.out.printf("%s nieje v zozname", meno);
    }

  }

  public static void printList(List<String> zoznam){
    int counter=1;
    for (String s: zoznam){
      System.out.printf("%d. %s\n", counter,s);
      counter++;
    }
  }

  public static void saveList(List<String> zoznam, Path p) {
    try {
      Files.deleteIfExists(p);
      Files.write(p, zoznam, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }catch (IOException e){
      System.out.println("Subor neexistuje");
    }
    System.out.println("Saving! " + p);
  }

  public static void loadList(List<String> zoznam, Path p) {
    BufferedReader reader = null;
    String record;
    zoznam.clear();
    try {
      if (Files.isRegularFile(p)) {
        try {
          reader = new BufferedReader(new FileReader(p.toString()));
          while ((record = reader.readLine()) != null) {
            zoznam.add(record);
          }
        } finally {
          if (reader != null) {
            reader.close();
          }
        }
      }
    }catch (IOException e){
      System.out.println(e);
    }

  }

}
