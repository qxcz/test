package vlastnaVynimka;

import vynimky.Osoba;

public class Main {

    public static void main(String[] args) throws InvalidAgeException {

        Osoba o = new Osoba("mare","soltis",20);

        if (o.getVek()<18){

            throw new InvalidAgeException("Osoba nie je dospela");
        }
        else {
            System.out.println("Osoba je dospela");
        }

        if (o.getMeno().length()>4) {

            throw new AgeTooLongException("Meno je priliz dlhe");

        } else {
            System.out.println("Meno je ok");
        }

    }
}
