package vn.com.seatechit.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.seatechit.domain.Base64Content;
import vn.com.seatechit.domain.SearchingFolderResult;
import vn.com.seatechit.service.SearchFolderService;
import vn.com.seatechit.service.WebService;

import javax.validation.Valid;
import java.io.IOException;

@Log4j2
@RestController
@RequestMapping("/api/export")
@Tag(name = "Export", description = "Download a excel file")
public class ExportResource {
  final static String SEARCHING_FOLDER_RESULT_XLS = "RP_Searching_Folder_Result.xlsx";
  final WebService webService;
  final SearchFolderService searchFolderService;

  public ExportResource(WebService webService, SearchFolderService searchFolderService) {
    this.webService = webService;
    this.searchFolderService = searchFolderService;
  }

  @PostMapping("/search-folder")
  @Operation(summary = "Download WH ECM Documents")
  public ResponseEntity<Resource> searchFolder(
      @Valid
      @RequestBody
      SearchingFolderResult result
  ) throws IOException {
    return webService.toResource(SEARCHING_FOLDER_RESULT_XLS, result.getFileName(), searchFolderService.toContext(result));
  }

  @PostMapping("/search-folder/base64")
  @Operation(summary = "Download WH ECM Documents as base 64 content")
  public ResponseEntity<Base64Content> searchFolder_(
      @Valid
      @RequestBody
      SearchingFolderResult result
  ) throws IOException {
    return webService.toBase64Content(SEARCHING_FOLDER_RESULT_XLS, searchFolderService.toContext(result));
  }
}
