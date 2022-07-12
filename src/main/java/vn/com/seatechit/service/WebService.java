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
import vn.com.seatechit.domain.Base64Content;
import vn.com.seatechit.util.JxlsHelperUtil;
import vn.com.seatechit.util.ResourceUtil;

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

  public ResponseEntity<Resource> toResource( String excelTemplate, String fileName, Context context) throws IOException {
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(getResource(excelTemplate, context));
  }

  public ResponseEntity<Base64Content> toBase64Content(String excelTemplate, Context context) throws IOException {
    return ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(new Base64Content(
            ResourceUtil.inputStreamToString(getResource(excelTemplate, context).getInputStream())
        ));
  }

  private InputStreamResource getResource(String excelTemplate, Context context) throws IOException {
    String templatePath = resourceConfiguration.getTemplatePath() + "\\" + excelTemplate;
    String outputPath = ResourceUtil
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .orElseThrow(() -> new RuntimeException("Output path not found")) + "/" + UUID.randomUUID() + ".xlsx";

    log.debug("Template path: {}", templatePath);
    log.debug("Output path: {}", outputPath);
    JxlsHelperUtil.report(templatePath, outputPath, context);

    return new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));
  }
}
