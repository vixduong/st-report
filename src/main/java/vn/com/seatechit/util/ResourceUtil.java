package vn.com.seatechit.util;

import io.vavr.control.Try;
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

  public static Try<InputStream> getInputStream(Path path){
    return Try.of(() -> Files.newInputStream(path));
  }

  public static Try<InputStream> getInputStreamFromClassPath(String path) {
    return Try.of(() -> new ClassPathResource(path).getInputStream());
  }

  public static Try<Path> createDirection(Path path) {
    return Try.of(() -> Files.createDirectories(path).toAbsolutePath());
  }

  public static Try<Path> createDirectionFromYearAndMonth(String path) {
    return Try.success(LocalDateTime.now())
        .map(now -> getPath(path + "/" + now.getYear() + "/" + now.getMonthValue()))
        .flatMap(ResourceUtil::createDirection);
  }

  public static Path getDirectionFromYearAndMonthOfPass(String path) {
    LocalDateTime passMonth = LocalDateTime.now().minusMonths(1);
    return getPath(path + "/" + passMonth.getYear() + "/" + passMonth.getMonthValue());
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

  public static Try<String> inputStreamToString(InputStream is) {
    return Try.of(() -> {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
      int length;
      while ((length = is.read(buffer)) != -1) {
        result.write(buffer, 0, length);
      }
      return result;
    }).map(bytes -> Base64.getEncoder().encodeToString(bytes.toByteArray()));
  }
}
