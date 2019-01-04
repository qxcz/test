package kolekcie;

import java.util.HashMap;
import java.util.Map;

public class Uloha2 {

  public static void main(String[] args) {

    Osoba osoba1 = new Osoba();
    osoba1.setMeno("Marek");
    osoba1.setPriezvisko("Soltis");
    osoba1.setVek(41);

    Osoba osoba2 = new Osoba();
    osoba2.setMeno("Maria");
    osoba2.setPriezvisko("Soltis");
    osoba2.setVek(35);

    Osoba osoba3 = new Osoba();
    osoba3.setMeno("Jozef");
    osoba3.setPriezvisko("Franko");
    osoba3.setVek(77);


    Map<Integer,Osoba> map = new HashMap<>();

  }

}
