package vn.com.seatechit.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.com.seatechit.util.ResourceUtil;

import java.io.IOException;
import java.nio.file.Files;

@Log4j2
@Component
public class FileSchedule {

  @Value("${st.resource.output-path}")
  private String outputExcelPath;

  @Async
  @Scheduled(cron = "${st.resource.remove-directory-cron}")
  public void removeFiles() {
    log.info("START :: Remove files");
    ResourceUtil
        .getDirectionFromYearAndMonthOfPass(outputExcelPath)
        .filter(Files::isDirectory)
        .ifPresent(p -> {
          log.info("Remove files in {}", p);
          try {
            ResourceUtil.deleteDirection(p);
          } catch (IOException e) {
            log.error("Error remove files in {}", p, e);
          }
        });
  }
}
