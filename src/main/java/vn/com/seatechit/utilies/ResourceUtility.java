package vn.com.seatechit.utilies;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

public class ResourceUtility {
  private ResourceUtility() {
  }

  public static Path getPath(String path) {
    return Paths.get(path).toAbsolutePath();
  }

  public static InputStream getInputStream(Path path) throws IOException {
    return Files.newInputStream(path);
  }

  public static InputStream getInputStream(String path) throws IOException {
    return getInputStream(getPath(path));
  }

  public static Optional<InputStream> getInputStreamFromClassPath(String path) {
    try {
      return Optional.of(new ClassPathResource(path).getInputStream());
    } catch (IOException ignored) {
    }
    return Optional.empty();
  }

  public static Optional<Path> createDirection(Path path) {
    try {
      return Optional.of(Files.createDirectories(path).toAbsolutePath());
    } catch (IOException ignored) {
    }
    return Optional.empty();
  }

  public static Optional<Path> createDirectionFromYearAndMonth(String path) {
    return Optional
        .of(LocalDateTime.now())
        .map(now -> getPath(path + "/" + now.getYear() + "/" + now.getDayOfMonth()))
        .flatMap(ResourceUtility::createDirection);
  }

  public static Optional<Path> createDirectionFromYearAndMonth(Path path) {
    return Optional
        .of(LocalDateTime.now())
        .map(now -> getPath(path.toString() + "\\" + now.getYear() + "\\" + now.getDayOfMonth()))
        .flatMap(ResourceUtility::createDirection);
  }

  public static Optional<Boolean> deleteDirection(Path path) {
    try {
      return Optional.of(Files.deleteIfExists(path));
    } catch (IOException ignored) {
    }
    return Optional.empty();
  }
}
