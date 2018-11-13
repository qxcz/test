package konzolaAdatumy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.SocketHandler;

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

    public void narodeniny (){
        Scanner vstup = new Scanner(System.in);
        System.out.println("Zadajte datum narodenia: ");
        LocalDate daumNarodenia = LocalDate.parse(vstup.nextLine());
        System.out.println("Pocet zobrazeni: ");
        Integer pocetzobrazeni = vstup.nextInt();
        Integer rokNarodenia = daumNarodenia.getYear();
        Integer mesiacNarodenia = daumNarodenia.getMonth().getValue();
        Integer denNarodenia = daumNarodenia.getDayOfMonth();
        Integer aktualnyRok = LocalDate.now().getYear();

        for (int i=0;i<pocetzobrazeni;i++){

            System.out.println("Rok " + (aktualnyRok+i) + ": " + Dni. LocalDate.of(aktualnyRok+i,mesiacNarodenia,denNarodenia).getDayOfWeek().getValue());

        }





    }

}
