package vn.com.seatechit.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.seatechit.domain.Base64Content;
import vn.com.seatechit.domain.base.*;
import vn.com.seatechit.service.ContextService;
import vn.com.seatechit.service.WebService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/export")
@Tag(name = "Export", description = "Download a excel file")
public class ExportResource {

  final WebService webService;
  final ContextService contextService;

  public ExportResource(WebService webService, ContextService contextService) {
    this.webService = webService;
    this.contextService = contextService;
  }
  @PostMapping("/_5")
  public ResponseEntity<Resource> base_5(
      @Valid @RequestBody Base_5 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_5(base));
  }

  @PostMapping("/_5/base64")
  public ResponseEntity<Base64Content> base_5_(
      @Valid @RequestBody Base_5 base
  ) throws IOException {
    return webService.toBase64Content(base.getTemplateName(), contextService.to_5(base));
  }

  @PostMapping("/_7")
  public ResponseEntity<Resource> base_7(
      @Valid @RequestBody Base_7 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_7(base));
  }

  @PostMapping("/_7/base64")
  public ResponseEntity<Base64Content> base_7_(
      @Valid @RequestBody Base_7 base
  ) throws IOException {
    return webService.toBase64Content(base.getTemplateName(), contextService.to_7(base));
  }

  @PostMapping("/_9")
  public ResponseEntity<Resource> base_9(
      @Valid @RequestBody Base_9 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_9(base));
  }

  @PostMapping("/_9/base64")
  public ResponseEntity<Base64Content> base_9_(
      @Valid @RequestBody Base_9 base
  ) throws IOException {
    return webService.toBase64Content(base.getTemplateName(), contextService.to_9(base));
  }

  @PostMapping("/_15")
  public ResponseEntity<Resource> base_15(
      @Valid @RequestBody Base_15 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_15(base));
  }

  @PostMapping("/_15/base64")
  public ResponseEntity<Base64Content> base_15_(
      @Valid @RequestBody Base_15 base
  ) throws IOException {
    return webService.toBase64Content(base.getTemplateName(), contextService.to_15(base));
  }

  @PostMapping("/_20")
  public ResponseEntity<Resource> base_20(
      @Valid @RequestBody Base_20 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_20(base));
  }

  @PostMapping("/_20/base64")
  public ResponseEntity<Base64Content> base_20_(
      @Valid @RequestBody Base_20 base
  ) throws IOException {
    return webService.toBase64Content(base.getTemplateName(), contextService.to_20(base));
  }

  @PostMapping("/_25")
  public ResponseEntity<Resource> base_25(
      @Valid @RequestBody Base_25 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_25(base));
  }

  @PostMapping("/_25/base64")
  public ResponseEntity<Base64Content> base_25_(
      @Valid @RequestBody Base_25 base
  ) throws IOException {
    return webService.toBase64Content(base.getTemplateName(), contextService.to_25(base));
  }

  @PostMapping("/_30")
  public ResponseEntity<Resource> base_30(
      @Valid @RequestBody Base_30 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_30(base));
  }

  @PostMapping("/_30/base64")
  public ResponseEntity<Base64Content> base_30_(
      @Valid @RequestBody Base_30 base
  ) throws IOException {
    return webService.toBase64Content(base.getTemplateName(), contextService.to_30(base));
  }

  @PostMapping("/_35")
  public ResponseEntity<Resource> base_35(
      @Valid @RequestBody Base_35 base
  ) throws IOException {
    return webService.toResource(base.getTemplateName(), UUID.randomUUID().toString().substring(0, 20), contextService.to_35(base));
  }
}
