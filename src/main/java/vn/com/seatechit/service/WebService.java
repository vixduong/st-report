package vn.com.seatechit.service;

import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.com.seatechit.config.ResourceConfiguration;
import vn.com.seatechit.utilies.JxlsHelperUtility;
import vn.com.seatechit.utilies.ResourceUtility;

import java.io.IOException;
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
  ) throws IOException {
    String templatePath = resourceConfiguration.getTemplateClassPath() + "\\" + excelTemplate;
    String outputPath = ResourceUtility
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .orElseThrow(() -> new RuntimeException("Output path not found")) + "/" + UUID.randomUUID() + ".xlsx";

    log.info("Template path: {}", templatePath);
    log.info("Output path: {}", outputPath);
    JxlsHelperUtility.report_(templatePath, outputPath, context);
    InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(resource);
  }
}
