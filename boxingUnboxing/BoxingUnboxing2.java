package boxingUnboxing;

public class BoxingUnboxing2 {

  public static Integer metoda1(int i){
    Integer in = i;
    return in;
  }

  public static Double metoda2 (double d){
    Double db = d;
    return d;
  }

  public static Long metoda3(long l) {
    Long lo =l;
    return lo;
  }

  public static int metoda4(Integer in){
    int i = in;
    return i;
  }

  public static double metoda5(Double db){
    double d = db;
    return db;
  }

  public static long metoda6(Long lo){
    long l = lo;
    return l;
  }

  public static void main(String[] args) {

    System.out.println(metoda1(10));
    System.out.println(metoda2(2.3));
    System.out.println(metoda3(4563245));

    Integer in = 4;
    System.out.println(metoda4(in));

    Double db = 5.47d;
    System.out.println(metoda5(db));

    Long lo = 654L;
    System.out.println(metoda6(lo));

  }


}
