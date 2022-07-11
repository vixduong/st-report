package vn.com.seatechit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private String fromDate;
    private String toDate;

    private List<SearchingFolderResultItem> items;
}
