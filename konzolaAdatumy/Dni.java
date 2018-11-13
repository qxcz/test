package konzolaAdatumy;

public enum Dni {

  "1"(1),
  Pondelok(2),
  Utorok(3),
  Streda(4),
  Stvrtok(5),
  Piatok(6),
  Sobota(7);

  private int poradie;

  Dni(int poradie) {
    this.poradie = poradie;
  }

  public int getPoradie() {
    return poradie;
  }

  public String getDen() {return Dni;}
}
