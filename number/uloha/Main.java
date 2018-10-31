package number.uloha;

import com.sun.xml.internal.fastinfoset.util.CharArray;

public class Main {

  public static void metoda1(Number vstup){

    if (vstup instanceof Integer) {
      System.out.println("integer");
      int i = vstup.intValue();
      System.out.println(i*10);
    }
    else{
      System.out.println("double");
      double d = vstup.doubleValue();
      System.out.println(d*10);
    }

  }

  public static void main(String[] args) {


    metoda1(10);
    metoda1(2.5);
    metoda1(15);


  }

  }

