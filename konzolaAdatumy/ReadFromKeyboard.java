package konzolaAdatumy;

import java.time.LocalTime;
import java.util.Date;
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

    public Date readDate (Date datum) {
        return datum;
    }

    public LocalTime pracDoba () {
        Scanner vstup = new Scanner(System.in);
        System.out.print("Zadajte cas prichodu: ");
        String s = vstup.nextLine();
        LocalTime prichod = LocalTime.parse(s);
        System.out.print("Zadajte dlzku prac doby: ");
        String dlzkaPracDoby = vstup.nextLine();

        LocalTime odchod = prichod.plusMinutes((Long.parseLong(dlzkaPracDoby)*60) + 30);

        return odchod;
    }

}
