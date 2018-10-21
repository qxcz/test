package vynimky;

public class Exceptions {

    public static double delenie(double x, double y) throws Exception{

        if (y==0) {throw new Exception("Delenie nulou"); }

        return x/y ;

    }

    public static double delenie2 (double x, double y) {

        if (y==0) {throw new ArithmeticException("delenie nulou");}

        return x/y;
    }


    public static void main(String[] args) {

        try {
            System.out.println(delenie(10,0));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println(delenie2(10,20));

        Osoba o = new Osoba("marek","soltis",40);
       o.setMeno(null);

            try {
                System.out.println(o.getMeno().toLowerCase());
            } catch (Exception e) {
                System.out.println("null value");
            }

    }

}
