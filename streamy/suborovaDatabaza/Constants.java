package streamy.suborovaDatabaza;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {

    public Path getDefaultParentPath() {
        return defaultParentPath;
    }

    public String getDefaultDBname() {
        return defaultDBname;
    }

    public Path getDefaultDBpath() {
        return defaultDBpath;
    }

    public Path getDefaultConfig() {
        return defaultConfig;
    }

    private final Path defaultParentPath = Paths.get("e:\\PROJECTS\\Learn2Code\\test\\");
    private final String defaultDBname = "default.dbf";
    private final Path defaultDBpath = Paths.get("e:\\PROJECTS\\Learn2Code\\test\\default.dbf");
    private final Path defaultConfig = Paths.get("e:\\PROJECTS\\Learn2Code\\test\\config.cfg");

}
