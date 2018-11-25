package hra.objekty.utils;

public class Utils {

    public static int calculateHit(int strenght, int critical){
        return (strenght+(critical/100)*strenght);
    }


}


