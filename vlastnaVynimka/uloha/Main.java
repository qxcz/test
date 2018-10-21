package vlastnaVynimka.uloha;

import vynimky.Osoba;

public class Main {


    public static double delenieNulou(double x, double y) throws Exception{

        if(y==0){throw new Vynimka1("delenie nulou");}
        return x/y;

    }

    public static Osoba osoba(String meno,String priezvysko,int vek){

        Osoba o = new Osoba(meno,priezvysko,vek);

        if (o.getVek()<18) {throw new Vynimka2("Nizky vek");}
        return o;
    }

    public static void metoda3 (double x, double y, String meno, String priezvisko, int vek){

        try {
            System.out.println(delenieNulou(x,y));
        } catch (Exception e) {
            System.err.println(e.getMessage());;
        }

        System.out.println(osoba(meno,priezvisko,vek).getVek());

    }

    public static void main(String[] args) {

        metoda3(70,21,"marek","soltis",20);

    }

}
