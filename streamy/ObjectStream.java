package streamy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStream {

  public static void main(String[] args) {

    Osoba osoba = new Osoba("Marek","Soltis","40","lj3kcd");


    try {

      FileOutputStream fileOut = new FileOutputStream("resources/object.txt");
      ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
      objOut.writeObject(osoba);
      objOut.close();
      System.out.println("Done!");

    } catch (IOException e) {
      e.printStackTrace();
    }

    try {

      FileInputStream fileIn = new FileInputStream("resources/object.txt");
      ObjectInputStream objIn = new ObjectInputStream(fileIn);
      Osoba osoba2 = (Osoba) objIn.readObject();
      objIn.close();

      System.out.println(osoba2.getMeno());
      System.out.println(osoba2.getPriezvisko());
      System.out.println(osoba2.getVek());
      System.out.println(osoba2.getHeslo());

    } catch (Exception e) {
      e.printStackTrace();
    }


  }

}
