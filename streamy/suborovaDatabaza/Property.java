package streamy.suborovaDatabaza;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Property {


    public String getProperty(String propertyName) {

        Properties properties = new Properties();
        InputStream inputStream = null;
        String fileName = new Constants().getDefaultConfig().toString();

        try {
            inputStream = new FileInputStream(fileName);
            properties.load(inputStream);
            return (properties.getProperty(propertyName));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void createPropertyFile(String name) throws IOException{

        Path configPath = new Constants().getDefaultConfig();
        Files.deleteIfExists(configPath);
        StringBuilder builder = new StringBuilder();
        builder.append("name=").append(name).append(".dbf");
        List<String> lines = Arrays.asList(builder.toString(),"recordCount=0");
        Files.write(configPath, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);

    }

    public void setProperty(String name, String recordCount){

        Path configPath = new Constants().getDefaultConfig();
        Properties prop = new Properties();
        OutputStream output = null;

       try {
           output = new FileOutputStream(configPath.toString());
           prop.setProperty("name", name);
           prop.setProperty("recordCount", recordCount);
           prop.store(output, null);

       }catch (IOException io){io.printStackTrace();
       } finally {
           if (output != null) {
               try {
                   output.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
    }

}
