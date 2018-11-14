package konzolaAdatumy;

public enum Dni {

  SUNDAY("Nedela"),
  MONDAY("Pondelok"),
  TUESDAY("Utorok"),
  WEDNESDAY("Streda"),
  THURSDAY("Stvrtok"),
  FRIDAY("Piatok"),
  SATURDAY("Sobota");

  private String preklad;

  public String getPreklad() {
    return preklad;
  }

  Dni(String s) {
    this.preklad = s;
 }


}
