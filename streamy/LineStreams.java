package streamy;

import java.io.*;

public class LineStreams {
    public static void main(String[] args) throws IOException {

        BufferedReader  in = null;
        PrintWriter     out = null;

        try {
            in = new BufferedReader(new FileReader("resources/input.txt"));
            out = new PrintWriter(new FileWriter("resources/output.txt"));

            String s;
            while ((s=in.readLine()) != null) {out.println(s);}

        } finally {
            if (in != null){in.close();}
            if (out !=null){out.close();}
        }


    }

}
