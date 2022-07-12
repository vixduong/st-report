package vn.com.seatechit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class JxlsHelperUtil {
  private JxlsHelperUtil() {
  }

  public static void report(
      String templatePath,
      String outputPath,
      Context context) throws IOException {
    try (InputStream is = ResourceUtil.getInputStream(templatePath)) {
      try (OutputStream os = Files.newOutputStream(Paths.get(outputPath))) {
        JxlsHelper.getInstance().processTemplate(is, os, context);
      }
    }
  }

  public static void report_(
      String templateClassPath,
      String outputPath,
      Context context) throws IOException {
    try (InputStream is = ResourceUtil.getInputStreamFromClassPath(templateClassPath)) {
      try (OutputStream os = Files.newOutputStream(Paths.get(outputPath))) {
        JxlsHelper.getInstance().processTemplate(is, os, context);
      }
    }
  }
}
