package vn.com.seatechit;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import vn.com.seatechit.config.ResourceConfiguration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Log4j2
@SpringBootApplication
@EnableConfigurationProperties({ResourceConfiguration.class})
public class StReportApplication  extends SpringBootServletInitializer {
  private static final String SPRING_PROFILE_DEFAULT = "spring.profiles.default";
  private static final String SPRING_PROFILE_PRODUCT = "prod";
  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(StReportApplication.class);
    Environment env = app.run(args).getEnvironment();
    logApplicationStartup(env);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    Map<String, Object> defProperties = new HashMap<>();
    /*
     * The default profile to use when no other profiles are defined
     * This cannot be set in the application.yml file.
     * See https://github.com/spring-projects/spring-boot/issues/1219
     */
    defProperties.put(SPRING_PROFILE_DEFAULT, SPRING_PROFILE_PRODUCT);
    application.application().setDefaultProperties(defProperties);
    return application.sources(StReportApplication.class);
  }

  private static void logApplicationStartup(Environment env) {
    String protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map(key -> "https").orElse("http");
    String serverPort = env.getProperty("server.port");
    String contextPath = Optional
        .ofNullable(env.getProperty("server.servlet.context-path"))
        .filter(StringUtils::isNotBlank)
        .orElse("/");
    String hostAddress = "localhost";
    try {
      hostAddress = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      log.warn("The host name could not be determined, using `localhost` as fallback");
    }
    log.info(
        "\n----------------------------------------------------------\n\t" +
            "Application '{}' is running! Access URLs:\n\t" +
            "Local: \t\t{}://localhost:{}{}\n\t" +
            "External: \t{}://{}:{}{}\n\t" +
            "Profile(s): \t{}\n----------------------------------------------------------",
        env.getProperty("spring.application.name"),
        protocol,
        serverPort,
        contextPath,
        protocol,
        hostAddress,
        serverPort,
        contextPath,
        env.getActiveProfiles().length == 0 ? env.getDefaultProfiles() : env.getActiveProfiles()
    );
  }
}
