package streamy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class CountSize {

    public int getSize(Path p) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;
        int c =0;

        try {
            in = new FileInputStream(p.toString());

            while ((in.read()) !=-1){ c++;}
        } finally {
            if (in !=null){in.close();}
        }

        return c;

    }

}
