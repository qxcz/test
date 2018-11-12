package konzolaAdatumy;

public class Uloha2 {

    public static void main(String[] args) {

     ReadFromKeyboard readFromKeyboard = new ReadFromKeyboard();

        Osoba osoba = new Osoba(readFromKeyboard.readFromKeyboard("Meno: "),readFromKeyboard.readFromKeyboard("Priezvisko: "));

        System.out.println(osoba.getMeno());
        System.out.println(osoba.getPriezvisko());
    }


}
