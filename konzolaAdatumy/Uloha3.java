package konzolaAdatumy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Uloha3 {

    public static void main(String[] args) {

        ReadFromKeyboard readFromKeyboard = new ReadFromKeyboard();

        while (true) {

            String vstup = readFromKeyboard.readFromKeyboard("Zadaj cislo: \n");
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(vstup);

            if (!m.matches()) {
                System.out.println("Nieje cislo");
            } else {
                System.out.println("Je cislo");
               // break;
            }

        }


    }


}
