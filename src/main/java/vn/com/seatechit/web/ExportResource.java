package vn.com.seatechit.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.com.seatechit.config.ResourceConfiguration;
import vn.com.seatechit.utilies.JxlsHelperUtility;

@RestController
@RequestMapping("/api/export")
public class ExportResource {
  final ResourceConfiguration resourceConfiguration;

  public ExportResource(ResourceConfiguration resourceConfiguration) {
    this.resourceConfiguration = resourceConfiguration;
  }

  @PostMapping
  public HttpEntity<Resource> export(
      String templateName,
      String fileName,
      @RequestBody Map<String, Object> data
  ) throws IOException {
    String templatePath = resourceConfiguration.getTemplatePath() + "\\" + templateName;
    String outputPath = resourceConfiguration.getOutputPath() + "\\" + UUID.randomUUID() + ".xlsx";

    JxlsHelperUtility.report(templatePath, outputPath, data);

    InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(resource);
  }
}
