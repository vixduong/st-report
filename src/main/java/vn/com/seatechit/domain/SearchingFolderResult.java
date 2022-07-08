package vn.com.seatechit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchingFolderResult {
    private String location;
    private String day;
    private String month;
    private String year;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    private List<SearchingFolderResultItem> items;
}
