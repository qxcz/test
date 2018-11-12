package konzolaAdatumy;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Kalkulacka {

    private String someText;

    public Kalkulacka(String someText) {
        this.someText = someText;
    }

    public void pocitaj(){


        ArrayList<Integer> listcisel = new ArrayList<>();
        ArrayList<String> listOperators = new ArrayList<>();
        int suma = 0;

        Pattern cislo = Pattern.compile("\\d+");
        Pattern operator = Pattern.compile("[\\+\\-\\*/]+");
        Matcher m = cislo.matcher(someText);
        Matcher n = operator.matcher(someText);
        boolean found = false;

        while (m.find()) {
            listcisel.add(Integer.parseInt(m.group()));
            found = true;
        }
        while (n.find()) {
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
        System.out.println(suma);

    }



}


