package vn.com.seatechit.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.springdoc.core.annotations.RouterOperation;
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
import vn.com.seatechit.service.WebService;
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
@Tag(name = "Export", description = "Download a excel file")
public class ExportResource {
  final static String SEARCHING_FOLDER_RESULT_XLS = "RP_Searching_Folder_Result.xlsx";
  final WebService webService;

  public ExportResource(WebService webService) {
    this.webService = webService;
  }

  @PostMapping("/search-folder")
  @Operation(summary = "Download WH ECM Documents")
  public ResponseEntity<Resource> searchFolder(
      @RequestBody
      SearchingFolderResult result
  ) throws IOException {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Context context = new Context();
    context.putVar("location", result.getLocation());
    context.putVar("day", result.getDay());
    context.putVar("month", result.getMonth());
    context.putVar("year", result.getYear());
    context.putVar("fromDate", Objects.nonNull(result.getFromDate()) ? fmt.format(result.getFromDate()) : "");
    context.putVar("toDate", Objects.nonNull(result.getToDate()) ? fmt.format(result.getToDate()) : "");
    context.putVar("items", result.getItems());
    return webService.toResource(SEARCHING_FOLDER_RESULT_XLS, result.getFileName(), context);
  }
}
