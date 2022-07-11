package vn.com.seatechit.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.com.seatechit.util.ResourceUtil;

import java.util.Optional;

@Log4j2
@Component
public class FileSchedule {

  @Scheduled(cron = "${st.resource.removeDirectoryCron}")
  public void removeFiles() {

    // @formatter:off
    Optional
        .of("Start remove files")
        .map(s -> { log.info(s); return s; })
        .flatMap(ResourceUtil::getDirectionFromYearAndMonthOfPass)
        .flatMap(ResourceUtil::deleteDirection)
        .map((v) -> "Finish remove files")
        .orElseThrow(() -> new RuntimeException("Can not remove files"));
    // @formatter:on
  }
}
