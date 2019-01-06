package kolekcie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Uloha14 {

    public static void main(String[] args) {
        Scanner vstup = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (true){

            String k = vstup.nextLine();
            if(k.equals("q")|| k.equals("Q")){break;}
            else{
                list.add(k);
            }

        }
        System.out.println(list);
    }

}
