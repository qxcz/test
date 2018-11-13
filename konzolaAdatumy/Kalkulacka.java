package konzolaAdatumy;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kalkulacka {

    private String someText;

    public Kalkulacka(String someText) {
        this.someText = someText;
    }

    public void pocitaj(){


        ArrayList<Double> listcisel = new ArrayList<>();
        ArrayList<String> listOperators = new ArrayList<>();
        double suma = 0;

        Pattern cislo = Pattern.compile("\\d+\\.\\d+");
        Pattern operator = Pattern.compile("[\\+\\-\\*/]+");
        Matcher m = cislo.matcher(someText);
        Matcher n = operator.matcher(someText);
        boolean found = false;

        while (m.find()) {
           // System.out.println("cislo: " +m.group());
            listcisel.add(Double.parseDouble(m.group()));
            found = true;
        }
        while (n.find()) {
           // System.out.println("znak: " +n.group());
            listOperators.add(n.group());
            found = true;
        }

        if (!found){
            System.out.println("nic");
        }


        for (int i=0; i <listcisel.size()-1; i++){

            switch(listOperators.get(i)){
                case("+"): suma = listcisel.get(i) + listcisel.get(i+1); break;
                case("-"): suma = listcisel.get(i) - listcisel.get(i+1); break;
                case("*"): suma = listcisel.get(i) * listcisel.get(i+1); break;
                case("/"): suma = listcisel.get(i) / listcisel.get(i+1); break;
            }
            listcisel.remove(i+1);
            listcisel.add(i+1,suma);

        }
        DecimalFormat format = new DecimalFormat("0.###");
        System.out.print("\r = " + format.format(suma));

    }



}


