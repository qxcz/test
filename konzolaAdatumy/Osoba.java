package konzolaAdatumy;

public class Osoba {

    private String meno;
    private String priezvisko;

    public Osoba(String meno, String priezvisko) {
        this.meno = meno;
        this.priezvisko = priezvisko;
    }

    @Override
    public String toString() {
        return "Osoba{" +
          "meno='" + meno + '\'' +
          ", priezvisko='" + priezvisko + '\'' +
          '}';
    }

    public String getMeno() {
        return meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }
}
