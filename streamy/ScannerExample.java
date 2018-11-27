package streamy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerExample {

  public static void main(String[] args) throws FileNotFoundException {

    FileInputStream fileInputStream = new FileInputStream("resources/text.txt");
    Scanner scanner = new Scanner(fileInputStream);
    scanner.useDelimiter(";");
    while (scanner.hasNext()){
      System.out.print("Novy token: ");
      System.out.print(scanner.next()+ "\n");

    }

  }

}
