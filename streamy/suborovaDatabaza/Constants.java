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

  public String getHeader() {
    return header;
  }

  private final Path defaultParentPath = Paths.get("c:\\DEV\\Test\\");
  private final String defaultDBname = "default.dbf";
  private final Path defaultDBpath = Paths.get("c:\\DEV\\Test\\default.dbf");
  private final Path defaultConfig = Paths.get("c:\\DEV\\Test\\config.cfg");
  private final String header = "priezvisko;meno;telefon1;telefon2;email";

}
