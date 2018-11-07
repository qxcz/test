package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test1 {

    public static void main(String[] args) {

      prehladaj(".+@.+\\..+","f@gmail.");


    }

    private static void prehladaj (String pattern, String text){

      Pattern p = Pattern.compile(pattern);
      Matcher m = p.matcher(text);
      boolean found = false;

      while (m.find()) {

          System.out.println("text: "  + m.group());
          System.out.println("zaciatok: " + m.start());
          System.out.println("koniec: " + m.end());
          found = true;
      }

      if (!found){
          System.out.println("nic");
      }

    }




}
