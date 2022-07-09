package vn.com.seatechit.service;

import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.springframework.stereotype.Component;
import vn.com.seatechit.domain.SearchingFolderResult;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Log4j2
@Component
public class SearchFolderService {
  public Context toContext(SearchingFolderResult result) {
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Context context = new Context();
    context.putVar("location", result.getLocation());
    context.putVar("day", result.getDay());
    context.putVar("month", result.getMonth());
    context.putVar("year", result.getYear());
    context.putVar("fromDate", Objects.nonNull(result.getFromDate()) ? fmt.format(result.getFromDate()) : "");
    context.putVar("toDate", Objects.nonNull(result.getToDate()) ? fmt.format(result.getToDate()) : "");
    context.putVar("items", result.getItems());

    return context;
  }
}
