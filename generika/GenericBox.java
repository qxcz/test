package generika;


import konzolaAdatumy.Osoba;

public class GenericBox <P>{

    private P object;

  public P getObject() {
    return object;
  }

  public void setObject(P object) {
    this.object = object;
  }

  public static void main(String[] args) {

    GenericBox<String> stringBox = new GenericBox<>();
    GenericBox<Osoba> osobaBox = new GenericBox<>();

    stringBox.setObject("jskfhsfjh");
    osobaBox.setObject(new Osoba("Marek","Soltis"));

    System.out.println(stringBox.getObject());
    System.out.println(osobaBox.getObject());
  }

}
