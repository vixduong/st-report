package vn.com.seatechit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "st.resource")
public class ResourceConfiguration {
  private String templatePath;
  private String outputPath;

  private String templateClassPath;
  private String outputClassPath;

  private String removeDirectoryCron;
}
