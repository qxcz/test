package znakyARetazce;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.util.Scanner;

public class Uloha1 {


  public static void transformToCapital() {


    Scanner vstup = new Scanner(System.in);

    System.out.println("Zadajte text: ");
    String s = vstup.nextLine();
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

  public static void main(String[] args) {

    transformToCapital();

  }


}
