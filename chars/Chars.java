package chars;

public class Chars {

  public static void main(String[] args) {


    char[] charArray = {'a','b','c','d','e','f'};

    for (char output: charArray){
      System.out.println(output);
    }

    String test3 = new String(charArray.toString());

    String test2 = new String(charArray);
    String test = String.copyValueOf(charArray);
    System.out.println(test3);
    System.out.println(test2);
    System.out.println(test);


    System.out.println(Character.isWhitespace(charArray[2]));
    System.out.println(Character.toUpperCase(charArray[5]));


  }

}
