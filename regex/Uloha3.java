package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Uloha3 {

    public static void main(String[] args) {

        uloha3();

    }

    public static void uloha3 (){

        String obrazok = new ReadFromKeyboard().readFromKeyboard();

        Pattern p = Pattern.compile(".+\\.(jpg|bmp|png|jpeg)$"); //obrazky
        Matcher m = p.matcher(obrazok);

        System.out.println(m.matches());


    }

}
