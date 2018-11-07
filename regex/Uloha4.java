package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Uloha4 {
    public static void main(String[] args) {

        uloha4();

    }

    public static void uloha4 (){

        String datum = new ReadFromKeyboard().readFromKeyboard();

        Pattern p = Pattern.compile("^[0-3][0-9]/[0-1][0-9]/[0-9]{4}$"); //datum
        Matcher m = p.matcher(datum);

        System.out.println(m.matches());


    }

}
