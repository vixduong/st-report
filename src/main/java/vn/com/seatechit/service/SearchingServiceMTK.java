package vn.com.seatechit.service;

import lombok.extern.log4j.Log4j2;
import org.jxls.common.Context;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SearchingServiceMTK {
    public Context toContext(vn.com.seatechit.domain.SearchingMTK mtk) {
        Context context = new Context();
        context.putVar("location", mtk.getLocation());
        context.putVar("day", mtk.getDay());
        context.putVar("month", mtk.getMonth());
        context.putVar("year", mtk.getYear());
        context.putVar("fromDate", mtk.getFromDate());
        context.putVar("toDate", mtk.getToDate());
        context.putVar("listMTK", mtk.getListMTK());

        return context;
    }
}
