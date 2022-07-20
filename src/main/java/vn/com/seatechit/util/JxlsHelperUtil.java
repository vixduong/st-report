package vn.com.seatechit.util;

import io.vavr.API;
import io.vavr.control.Try;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Log4j2
@UtilityClass
public class JxlsHelperUtil {

  public static Try<JxlsHelper> report_(
      String templateClassPath,
      String outputPath,
      Context context) {
    Try<InputStream> isMaybe = ResourceUtil.getInputStreamFromClassPath(templateClassPath);
    Try<OutputStream> osMaybe = API.Try(() -> Files.newOutputStream(Paths.get(outputPath)));
    return isMaybe
        .flatMap(is -> osMaybe.map(os -> API.Tuple(is, os)))
        .flatMapTry(it -> API.Try(() -> JxlsHelper.getInstance().processTemplate(it._1, it._2, context)))
        .onFailure(log::error)
        .andFinallyTry(() -> {
          isMaybe.get().close();
          osMaybe.get().close();
        });
  }
}
