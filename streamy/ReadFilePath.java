package streamy;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadFilePath {

    public Path enterNewFilePath(){

        Scanner vstup = new Scanner(System.in);
        System.out.print("Enter file location: ");
        Path p = Paths.get(vstup.nextLine().trim());
        return p;

    }

}
