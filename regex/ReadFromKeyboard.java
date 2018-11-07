package regex;

import java.util.Scanner;

public class ReadFromKeyboard {

  public String readFromKeyboard() {
    Scanner vstup = new Scanner(System.in);

    System.out.println("Zadajte text: ");
    String s = vstup.nextLine();
    return s;

  }
}