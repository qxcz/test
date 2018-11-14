package konzolaAdatumy;

import java.time.LocalDate;

public class Sviatky {


  public static String sviatkyArray(String den) {

    String sviatky[][] = new String[15][2];
    String aktualnySviatok = "";
    String pracovnyDen = "Pracovny den.";
    StringBuilder builder = new StringBuilder();
    StringBuilder builder1 = new StringBuilder();
    int j;
    int indexOfNextSviatok;

    sviatky[0][0] = "01-01";
    sviatky[0][1] = "Deň vzniku Slovenskej republiky - štátny sviatok";
    sviatky[1][0] = "01-06";
    sviatky[1][1] = "Zjavenie Pána (Traja králi) - deň pracovného pokoja";
    sviatky[2][0] = "03-30";
    sviatky[2][1] = "Veľký piatok - deň pracovného pokoja";
    sviatky[3][0] = "04-02";
    sviatky[3][1] = "Veľkonočný pondelok - deň pracovného pokoja";
    sviatky[4][0] = "05-01";
    sviatky[4][1] = "Sviatok práce - deň pracovného pokoja";
    sviatky[5][0] = "05-08";
    sviatky[5][1] = "Deň víťazstva nad fašizmom - deň pracovného pokoja";
    sviatky[6][0] = "07-05";
    sviatky[6][1] = "Sviatok svätého Cyrila a Metoda - štátny sviatok";
    sviatky[7][0] = "08-29";
    sviatky[7][1] = "Výročie SNP - štátny sviatok";
    sviatky[8][0] = "09-01";
    sviatky[8][1] = "Deň Ústavy Slovenskej republiky - štátny sviatok";
    sviatky[9][0] = "09-15";
    sviatky[9][1] = "Sedembolestná Panna Mária - deň pracovného pokoja";
    sviatky[10][0] = "11-01";
    sviatky[10][1] = "Sviatok všetkých svätých - deň pracovného pokoja";
    sviatky[11][0] = "11-17";
    sviatky[11][1] = "Deň boja za slobodu a demokraciu - štátny sviatok";
    sviatky[12][0] = "12-24";
    sviatky[12][1] = "Štedrý deň - deň pracovného pokoja";
    sviatky[13][0] = "12-25";
    sviatky[13][1] = "Prvý sviatok vianočný - deň pracovného pokoja";
    sviatky[14][0] = "12-26";
    sviatky[14][1] = "Druhý sviatok vianočný - deň pracovného pokoja";

    for (int i = 0; i < sviatky.length; i++) {

      if (den.equals(sviatky[i][0])) {
        aktualnySviatok = sviatky[i][1];
      } else aktualnySviatok = "Pracovny den.";
    }

    if (!aktualnySviatok.equals(pracovnyDen)) {
      return aktualnySviatok;
    } else {

      j = 0;
      indexOfNextSviatok = 16;

      while (j < 365) {
        builder.append(LocalDate.now().plusDays(j).getMonth().getValue()).append("-").append(LocalDate.now().plusDays(j).getDayOfMonth());
        for (int i = 0; i < 15; i++) {
          if (builder.toString().equals(sviatky[i][0])) {
            indexOfNextSviatok = i;
            break;
          }
        }
        if (indexOfNextSviatok != 16) {
          break;
        }
        j++;
        builder.setLength(0);
      }
      builder1.append("pracovy den, ale najblizsi sviatok bude ").append(sviatky[indexOfNextSviatok][1]);
      builder1.append(" o ").append(j).append(" dni");
      builder1.append(" ").append(LocalDate.now().plusDays(j));
      aktualnySviatok = builder1.toString();
      return aktualnySviatok;

    }


  }


}
