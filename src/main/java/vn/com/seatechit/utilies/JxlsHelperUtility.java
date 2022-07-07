package vn.com.seatechit.utilies;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Log4j2
public class JxlsHelperUtility {
  private JxlsHelperUtility() {
  }

  public static void report(
      String templatePath,
      String outputPath,
      Map<String, Object> data
  ) {
    try (InputStream is = ResourceUtility.getInputStream(templatePath)) {
      try (OutputStream os = Files.newOutputStream(Paths.get(outputPath))) {
//        Context context = new Context();
////        if(Objects.nonNull(data)) {
////          data.forEach(context::putVar);
////        }
//        JxlsHelper.getInstance().processTemplate(is, os, context);
      } catch (IOException e) {
        log.error(e);
      }
    } catch (IOException e) {
      log.error(e);
    }
  }
}
