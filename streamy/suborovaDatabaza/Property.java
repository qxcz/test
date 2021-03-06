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

    private String dbName;
    private String dbPath;
    private Integer dbRecordCount;
    private Integer dbColumnCount;
    private final String name = "name=";
    private final String path = "path=";
    private final String recordCount = "recordCount=";
    private final String columnCount = "columnCount=";

    Properties prop = new Properties();
    StringBuilder builder = new StringBuilder();

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath.replace("\\", "\\\\");
    }

    public void setDbRecordCount(Integer dbRecordCount) {
        this.dbRecordCount = dbRecordCount;
    }

    public void setDbColumnCount(Integer dbColumnCount) {
        this.dbColumnCount = dbColumnCount;
    }

    public String getProp(String propertyName) {

        InputStream inputStream = null;
        String fileName = new Constants().getDefaultConfig().toString();

        try {
            inputStream = new FileInputStream(fileName);
            prop.load(inputStream);
            return (prop.getProperty(propertyName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

    public void createPropertyFile(Path path) throws IOException {

        this.dbPath = path.getParent().toString().replace("\\", "\\\\");
        this.dbName = path.getFileName().toString();
        this.dbRecordCount = 0;
        this.dbColumnCount = 0;
        saveProperty();

    }

    public void saveProperty() throws IOException {

        Path configPath = new Constants().getDefaultConfig();
        Files.deleteIfExists(configPath);
        String firsProperty = builder.append(name).append(dbName).toString();
        builder.setLength(0);
        String secondProperty = builder.append(path).append(dbPath).toString();
        builder.setLength(0);
        String thirdProperty = builder.append(recordCount).append(dbRecordCount).toString();
        builder.setLength(0);
        String fourthProperty = builder.append(columnCount).append(dbColumnCount).toString();
        builder.setLength(0);
        List<String> lines = Arrays.asList(firsProperty, secondProperty, thirdProperty, fourthProperty);
        Files.write(configPath, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);

    }

    public void setCount(Integer dbRecordCount) {

        Path configPath = new Constants().getDefaultConfig();
        OutputStream output = null;

        System.out.println(prop.getProperty("name"));


        try {
            output = new FileOutputStream(configPath.toString());
            prop.setProperty("name", prop.getProperty("name"));
            prop.setProperty("recordCount", dbRecordCount.toString());
            prop.setProperty("path", prop.getProperty("path"));
            prop.setProperty("columnCount", prop.getProperty("columnCount"));
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
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

    public void saveColumnCount(Integer dbColumnCountCount) {

        Path configPath = new Constants().getDefaultConfig();
        OutputStream output = null;

        System.out.println(prop.getProperty("name"));


        try {
            output = new FileOutputStream(configPath.toString());
            prop.setProperty("name", prop.getProperty("name"));
            prop.setProperty("recordCount", prop.getProperty("recordCount"));
            prop.setProperty("path", prop.getProperty("path"));
            prop.setProperty("columnCount", dbColumnCountCount.toString());
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
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
