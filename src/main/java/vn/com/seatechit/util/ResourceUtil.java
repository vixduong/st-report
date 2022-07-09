package vn.com.seatechit.util;

import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

public class ResourceUtil {
  public static final int DEFAULT_BUFFER_SIZE = 8192;
  private ResourceUtil() {
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

  public static InputStream getInputStreamFromClassPath(String path) throws IOException {
    return new ClassPathResource(path).getInputStream();
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
        .flatMap(ResourceUtil::createDirection);
  }

  public static Optional<Path> createDirectionFromYearAndMonth(Path path) {
    return Optional
        .of(LocalDateTime.now())
        .map(now -> getPath(path.toString() + "\\" + now.getYear() + "\\" + now.getDayOfMonth()))
        .flatMap(ResourceUtil::createDirection);
  }

  public static Optional<Boolean> deleteDirection(Path path) {
    try {
      return Optional.of(Files.deleteIfExists(path));
    } catch (IOException ignored) {
    }
    return Optional.empty();
  }

  public static String inputStreamToString(InputStream is) throws IOException {
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
    int length;
    while ((length = is.read(buffer)) != -1) {
      result.write(buffer, 0, length);
    }

    return Base64.getEncoder().encodeToString(result.toByteArray());
  }
}
