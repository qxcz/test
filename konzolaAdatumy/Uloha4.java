package konzolaAdatumy;

public class Uloha4 {

    public static void main(String[] args) {

        ReadFromKeyboard readFromKeyboard = new ReadFromKeyboard();
        String vstup = readFromKeyboard.readFromKeyboard("Priklad: ");

        Kalkulacka kalkulacka = new Kalkulacka(vstup);

        kalkulacka.pocitaj();


    }


}
