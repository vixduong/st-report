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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
  ) throws IOException {
    String templatePath = resourceConfiguration.getTemplateClassPath() + "\\" + excelTemplate;
    String outputPath = ResourceUtil
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .orElseThrow(() -> new RuntimeException("Output path not found")) + "/" + UUID.randomUUID() + ".xlsx";

    log.info("Template path: {}", templatePath);
    log.info("Output path: {}", outputPath);
    JxlsHelperUtil.report_(templatePath, outputPath, context);
    InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(resource);
  }

  public ResponseEntity<String> toString(String excelTemplate, Context context) throws IOException {
    String templatePath = resourceConfiguration.getTemplateClassPath() + "\\" + excelTemplate;
    String outputPath = ResourceUtil
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .orElseThrow(() -> new RuntimeException("Output path not found")) + "/" + UUID.randomUUID() + ".xlsx";

    log.info("Template path: {}", templatePath);
    log.info("Output path: {}", outputPath);
    JxlsHelperUtil.report_(templatePath, outputPath, context);
    InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));
    String body = ResourceUtil.inputStreamToString(resource.getInputStream());

    return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(body);
  }

  public ResponseEntity<Base64Content> toBase64Content(String excelTemplate, Context context) throws IOException {
    String templatePath = resourceConfiguration.getTemplateClassPath() + "\\" + excelTemplate;
    String outputPath = ResourceUtil
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .orElseThrow(() -> new RuntimeException("Output path not found")) + "/" + UUID.randomUUID() + ".xlsx";

    log.info("Template path: {}", templatePath);
    log.info("Output path: {}", outputPath);
    JxlsHelperUtil.report_(templatePath, outputPath, context);
    InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));
    String body = ResourceUtil.inputStreamToString(resource.getInputStream());

    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new Base64Content(body));
  }

  private void createResource(String excelTemplate, String fileName, Context context) throws IOException {
    ResourceUtil
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .onSuccess(path -> log.info("Output path: {}", path))
        .flatMap(outputPath -> {
          String templatePath = String.join("\\", resourceConfiguration.getTemplateClassPath(), excelTemplate);
          log.info("Template path: {}", templatePath);
          return JxlsHelperUtil.report_(templatePath, outputPath.toString(), context);
        })
        .getOrElseThrow(() -> new RuntimeException("Output path not found"));

    String templatePath = resourceConfiguration.getTemplateClassPath() + "\\" + excelTemplate;
    String outputPath = outputPath
        .orElseThrow(() -> new RuntimeException("Output path not found")) + "/" + UUID.randomUUID() + ".xlsx";

    log.info("Template path: {}", templatePath);
    log.info("Output path: {}", outputPath);
    JxlsHelperUtil.report_(templatePath, outputPath, context);
    InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));
  }
}
