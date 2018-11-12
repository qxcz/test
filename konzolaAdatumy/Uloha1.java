package konzolaAdatumy;

import java.util.Scanner;

public class Uloha1 {


    public static void main(String[] args) {

        Scanner vstup = new Scanner(System.in);

       while (true) {

           System.out.print("text: ");
           String s = vstup.nextLine();

           if(s.equals("q")){
               System.out.println("Exit!");
               break;}
           System.out.println(s);
       }

    }

}
