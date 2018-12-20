package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Uloha3 {

    public static void main(String[] args) {

        uloha3();

    }

    public static void uloha3 (){

        String obrazok = "Soltis;Marek;0903614242;;marek.soltis@gmail.com";

        Pattern p = Pattern.compile(".+marek.+"); //obrazky
        Matcher m = p.matcher(obrazok);

        System.out.println(m.matches());


    }

}
