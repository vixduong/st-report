package vn.com.seatechit.service;

import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.com.seatechit.config.ResourceConfiguration;
import vn.com.seatechit.domain.Base64Content;
import vn.com.seatechit.util.JxlsHelperUtil;
import vn.com.seatechit.util.ResourceUtil;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Log4j2
@Component
public class WebService {
  final ResourceConfiguration resourceConfiguration;

  public WebService(ResourceConfiguration resourceConfiguration) {
    this.resourceConfiguration = resourceConfiguration;
  }

  public ResponseEntity<Resource> toResource(
      String excelTemplate,
      String fileName,
      Context context
  ) {
    return createResource(
        resourceConfiguration,
        excelTemplate,
        fileName + ".xlsx",
        context
    )
        .map((val) -> ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .<Resource>body(new InputStreamResource(val)))
        .getOrElseThrow(() -> new RuntimeException("Error while creating resource"));
  }

  public ResponseEntity<Base64Content> toBase64Content(String excelTemplate, Context context) {
    return createResource(
        resourceConfiguration,
        excelTemplate,
        UUID.randomUUID() + ".xlsx",
        context
    )
        .map(InputStreamResource::new)
        .flatMap(r -> Try.of(r::getInputStream))
        .flatMap(ResourceUtil::inputStreamToString)
        .map((body) -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new Base64Content(body)))
        .getOrElseThrow(() -> new RuntimeException("Can not convert to base64 content"));
  }

  private static Try<InputStream> createResource(
      ResourceConfiguration resourceConfiguration,
      String template, String fileName, Context context
  ) {
    return ResourceUtil
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .onSuccess(path -> log.info("Output path: {}", path))
        .onFailure(throwable -> log.error("Output path not found", throwable))
        .map(path -> path.toString() + "/" + fileName)
        .flatMap(outputPath -> {
          String templatePath = String.join("\\", resourceConfiguration.getTemplateClassPath(), template);
          log.info("Template path: {}", templatePath);
          return JxlsHelperUtil
              .report_(templatePath, outputPath, context)
              .flatMap((it) -> Try.of(() -> Files.newInputStream(Paths.get(outputPath))));
        });
  }
}
