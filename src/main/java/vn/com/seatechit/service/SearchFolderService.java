package vn.com.seatechit.service;

import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.springframework.stereotype.Component;
import vn.com.seatechit.domain.SearchingFolderResult;

@Log4j2
@Component
public class SearchFolderService {
  public Context toContext(SearchingFolderResult result) {
    Context context = new Context();
    context.putVar("location", result.getLocation());
    context.putVar("day", result.getDay());
    context.putVar("month", result.getMonth());
    context.putVar("year", result.getYear());
    context.putVar("fromDate", result.getFromDate());
    context.putVar("toDate", result.getToDate());
    context.putVar("items", result.getItems());

    return context;
  }
}
