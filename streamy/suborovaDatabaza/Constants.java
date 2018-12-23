package streamy.suborovaDatabaza;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {

  public Path getDefaultParentPath() {
    return defaultParentPath;
  }

  public Path getDefaultConfig() {
    return defaultConfig;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public Integer getHeaderCount() {
    return headerCount;
  }

  public void setHeaderCount(Integer headerCount) {
    this.headerCount = headerCount;
  }

  private final Path defaultParentPath = Paths.get("c:\\DEV\\Test\\");
  private final Path defaultConfig = Paths.get("c:\\DEV\\Test\\config.cfg");
  private String header;
  private Integer headerCount;

}
