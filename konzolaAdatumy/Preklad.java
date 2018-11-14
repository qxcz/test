package konzolaAdatumy;

public class Preklad {

  public static String preklad (String den){

    String denTyzdna;
    switch(den){

      case "MONDAY"     : denTyzdna = "Pondelok"; break;
      case "TUESDAY"    : denTyzdna =  "Utorok"; break;
      case "WEDNESDAY"  : denTyzdna =  "Streda"; break;
      case "THURSDAY"   : denTyzdna =  "Stvrtok"; break;
      case "FRIDAY"     : denTyzdna =  "Piatok"; break;
      case "SATURDAY"   : denTyzdna =  "Sobota"; break;
      case "SUNDAY"     : denTyzdna =  "Nedela"; break;
      default           : denTyzdna =  "Chyba"; break;
    }
    return denTyzdna;
  }

}
