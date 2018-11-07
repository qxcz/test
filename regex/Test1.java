package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

  public static boolean searchText (String pattern, String text) {

    Pattern p = Pattern.compile(pattern);
    Matcher m = p.matcher(text);
    return m.matches();


//    while (m.find()) {
//
//      System.out.println("text: " + m.group());
//      found = true;
//    }
//
//    if(!found){
//      System.out.println("nic");
//    }
  }

  public static void checkMail(){

    String mail= new ReadFromKeyboard().readFromKeyboard();

    Pattern p = Pattern.compile(".+@.+\\..+");
    Matcher m = p.matcher(mail);
    System.out.println(m.matches());

  }

  public static void checkPassword (){

    String pass = new ReadFromKeyboard().readFromKeyboard();

    if (!searchText(".{8,}",pass)) {
      System.out.println("nema 8 znakov");
      checkPassword();
    }
    if (!searchText(".*[A-Z].*",pass)){
      System.out.println("nema velke pismeno");
      checkPassword();
    }
    if (!searchText(".*[0-9].*",pass)){
      System.out.println("nema cislo");
      checkPassword();
    }



  }

  public static void main(String[] args) {

    //checkMail();
    checkPassword();

  }

}



