package vn.com.seatechit.schedule;

import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.com.seatechit.util.ResourceUtil;

import java.nio.file.Files;

@Log4j2
@Component
public class FileSchedule {

  @Value("${st.resource.output-path}")
  private String outputExcelPath;

  @Async
  @Scheduled(cron = "${st.resource.remove-directory-cron}")
  public void removeFiles() {
    Try
        .success("START :: Remove files")
        .onSuccess(log::info)
        .map(it -> ResourceUtil.getDirectionFromYearAndMonthOfPass(outputExcelPath))
        .filter(Files::exists)
        .flatMap(p -> Try.of(() -> {
          ResourceUtil.deleteDirection(p);
          return p;
        }))
        .onSuccess(p -> log.info("Remove directory: {} --> successful", p));
        //.onFailure(e -> log.error("Error while remove directory: ", e));
  }
}
