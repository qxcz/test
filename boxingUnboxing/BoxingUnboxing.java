package boxingUnboxing;

public class BoxingUnboxing {


    private static Integer metoda1 (int i){
        i++;
      Integer integ = new Integer(i);
        return integ;
    }

    private static Double metoda2 (double d){
      d++;
      Double doub = new Double(d);
       return doub;
    }
    private static Long metoda3(long l) {
       l++;
       Long lon = new Long(l);
       return lon;
    }
    private static int metoda4 (Integer i){
        int j = i;
        return j+1;
    }
    private static double metoda5 (Double d){
        double db = d;
        return db+1;
    }
    private static long metoda6 (Long ln){
        long lo = ln;
        return lo+1;
    }


    public static void main(String[] args) {

        System.out.println(metoda1(10));
        System.out.println(metoda2(234252534));
        System.out.println(metoda3(1314564123));
        System.out.println(metoda4(468));
        System.out.println(metoda5(2.34252535E8));
        System.out.println(metoda6(1314564123L));
    }


}
