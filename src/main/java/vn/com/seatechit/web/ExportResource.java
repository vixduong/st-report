package vn.com.seatechit.web;

import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.seatechit.config.ResourceConfiguration;
import vn.com.seatechit.domain.SearchingFolderResult;
import vn.com.seatechit.utilies.JxlsHelperUtility;
import vn.com.seatechit.utilies.ResourceUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/export")
public class ExportResource {
  final static String SEARCHING_FOLDER_RESULT_XLS = "RP_Searching_Folder_Result.xlsx";
  final ResourceConfiguration resourceConfiguration;

  public ExportResource(ResourceConfiguration resourceConfiguration) {
    this.resourceConfiguration = resourceConfiguration;
  }

  @PostMapping("/search-folder")
  public ResponseEntity<Resource> searchFolder(
      String fileName,
      @RequestBody SearchingFolderResult result
  ) throws IOException {
    String templatePath = resourceConfiguration.getTemplateClassPath() + "\\" + SEARCHING_FOLDER_RESULT_XLS;
    String outputPath = ResourceUtility
        .createDirectionFromYearAndMonth(resourceConfiguration.getOutputPath())
        .orElseThrow(() -> new RuntimeException("Output path not found")) + "\\" + UUID.randomUUID() + ".xlsx";

    log.info("Template path: {}", templatePath);
    log.info("Output path: {}", outputPath);

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Context context = new Context();
    context.putVar("location", result.getLocation());
    context.putVar("day", result.getDay());
    context.putVar("month", result.getMonth());
    context.putVar("year", result.getYear());
    context.putVar("fromDate", Objects.nonNull(result.getFromDate()) ? fmt.format(result.getFromDate()): "");
    context.putVar("toDate", Objects.nonNull(result.getToDate()) ? fmt.format(result.getToDate()): "");
    context.putVar("items", result.getItems());
    JxlsHelperUtility.report_(templatePath, outputPath, context);

    InputStreamResource resource = new InputStreamResource(Files.newInputStream(Paths.get(outputPath)));

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + ".xlsx")
        .contentType(MediaType.parseMediaType("application/octet-stream"))
        .body(resource);
  }
}
