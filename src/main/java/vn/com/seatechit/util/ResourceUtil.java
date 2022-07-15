package vn.com.seatechit.util;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@UtilityClass
public class ResourceUtil {
  public static final int DEFAULT_BUFFER_SIZE = 8192;

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

  public static Optional<Path> getDirectionFromYearAndMonthOfPass(String path) {
    return Optional
        .of(LocalDateTime.now().plusMonths(-1))
        .map(now -> getPath(path + "/" + now.getYear() + "/" + now.getDayOfMonth()));
  }

  public static Optional<Path> createDirectionFromYearAndMonth(Path path) {
    return Optional
        .of(LocalDateTime.now())
        .map(now -> getPath(path.toString() + "\\" + now.getYear() + "\\" + now.getDayOfMonth()))
        .flatMap(ResourceUtil::createDirection);
  }

  public static void deleteDirection(Path path) throws IOException {
    Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        Files.delete(dir);
        return FileVisitResult.CONTINUE;
      }
    });
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
