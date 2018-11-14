package konzolaAdatumy;

import java.time.LocalDate;

public class Uloha8Sviatky {

  public static void main(String[] args) {

    StringBuilder builder = new StringBuilder();
    builder.append(LocalDate.now().getMonth().getValue()).append("-").append(LocalDate.now().getDayOfMonth());

  System.out.println("Dnes je " + Sviatky.sviatkyArray(builder.toString()));



  }

}
