package streamy;

import java.io.Serializable;

public class Osoba implements Serializable {

  private static final long serialVersionUID = 1L;


  private String meno;
  private String priezvisko;
  private String vek;
  private transient String heslo;

  public Osoba(String meno, String priezvisko, String vek, String heslo) {
    this.meno = meno;
    this.priezvisko = priezvisko;
    this.vek = vek;
    this.heslo = heslo;
  }

  public String getMeno() {
    return meno;
  }

  public void setMeno(String meno) {
    this.meno = meno;
  }

  public String getPriezvisko() {
    return priezvisko;
  }

  public void setPriezvisko(String priezvisko) {
    this.priezvisko = priezvisko;
  }

  public String getVek() {
    return vek;
  }

  public void setVek(String vek) {
    this.vek = vek;
  }

  public String getHeslo() {
    return heslo;
  }

  public void setHeslo(String heslo) {
    this.heslo = heslo;
  }
}
