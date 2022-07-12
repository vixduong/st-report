package vn.com.seatechit.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.com.seatechit.util.ResourceUtil;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Log4j2
@Component
public class FileSchedule {

  @Value("${st.resource.output-path}")
  private String outputExcelPath;

  @Scheduled(cron = "${st.resource.removeDirectoryCron}")
  public void removeFiles() {
    log.info("Remove files");
    ResourceUtil
        .getDirectionFromYearAndMonthOfPass(outputExcelPath)
        .ifPresent(p -> {
          log.info("Remove files in {}", p);
          try {
            ResourceUtil.deleteDirection(p);
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
