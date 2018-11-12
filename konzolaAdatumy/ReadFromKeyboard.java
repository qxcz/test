package konzolaAdatumy;

import java.util.Scanner;

public class ReadFromKeyboard
{


    public String readFromKeyboard(String text)
    {
        Scanner vstup = new Scanner(System.in);
        System.out.print(text);
        String s = vstup.nextLine();
        return s;

    }
}
