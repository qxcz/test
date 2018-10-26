package vlastnaVynimka.uloha2;

import java.util.Scanner;

public class Main {

  public static void vek(){

    Scanner vstup = new Scanner(System.in);
    System.out.println("Zadaj vek:");
    int vk = vstup.nextInt();

    try {
      if (vk < 18) {
        throw new VlastnaVynimka1("vek+18");
      }
    }
    catch (VlastnaVynimka1 e) {System.err.print(e.getMessage());}
    System.out.println("vek je " +vk );
  }

  public static void delenie() {

    Scanner vstup = new Scanner(System.in);
    System.out.println("Zadaj delitel:");
    int delitel = vstup.nextInt();
    if (delitel==0){throw new VlastnaVynimka2("delenie 0");}
    System.out.println(50/delitel);
  }

  public static void main(String[] args) {




    vek();
    delenie();

  }

}
