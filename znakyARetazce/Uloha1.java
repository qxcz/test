package znakyARetazce;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.util.Scanner;

public class Uloha1 {

  public static String readFromKeyboard(){
    Scanner vstup = new Scanner(System.in);

    System.out.println("Zadajte text: ");
    String s = vstup.nextLine();
    return s;
  }


  public static void transformToCapital() {

    String s = readFromKeyboard();
    char textParse[] = s.toCharArray();

    for (int i=0; i < s.length(); i++ )
    { if (i< s.length()-1)
      { if (textParse[i + 1] != ' ') {textParse[i] = Character.toLowerCase(textParse[i]);}

        else {textParse[i] = Character.toUpperCase(textParse[i]);}
      }
      else {textParse[i] = Character.toUpperCase(textParse[i]);}
    }

    String s2 = String.copyValueOf(textParse);
    System.out.println(s2);

  }

  public static void transformToAt() {

    String s = readFromKeyboard();
    char textParse[] = s.toCharArray();

    for (int i=0; i < s.length(); i++ )
    {
      if ( textParse[i] != ' ') {textParse[i]= '@';}
    }
    String s2 = String.copyValueOf(textParse);
    System.out.println(s2);

  }

  public static void cutLastComma(){

    String s = readFromKeyboard();
    System.out.println(s.substring(0,s.lastIndexOf(",")));


  }

  public static void main(String[] args) {

    //transformToCapital();
    //transformToAt();
    cutLastComma();

  }


}
