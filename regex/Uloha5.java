package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Uloha5 {

    public static void searchText() {

        String text = new ReadFromKeyboard().readFromKeyboard();
      //  Pattern p = Pattern.compile("\\b[A-ZÁČĎÉĚÍŇÓŘŠŤŮÚÝŽ][a-zA-ZáčďéěíňóřšťůúýžÁČĎÉĚÍŇÓŘŠŤŮÚÝŽ]*\\b");
        Pattern r = Pattern.compile("\\b\\d+\\b");
       // Matcher m = p.matcher(text);
        Matcher n = r.matcher(text);
        boolean found = false;

        while (n.find()) {

            System.out.println(n.group());
            found = true;
        }

        if (!found) {
            System.out.println("nic");
        }


    }

    public static void main(String[] args) {
        searchText();
    }

}