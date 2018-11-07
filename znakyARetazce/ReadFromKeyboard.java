package znakyARetazce;

import java.util.Scanner;

public class ReadFromKeyboard
{


    public String readFromKeyboard(String text)
    {
        Scanner vstup = new Scanner(System.in);
        System.out.println(text);
        String s = vstup.nextLine();
        return s;

    }
}
