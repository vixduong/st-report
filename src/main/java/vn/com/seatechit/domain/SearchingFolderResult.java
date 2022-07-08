package vn.com.seatechit.domain;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchingFolderResult {
    @NotNull
    @NotEmpty
    private String fileName;
    private String location;
    private String day;
    private String month;
    private String year;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    private List<SearchingFolderResultItem> items;
}
