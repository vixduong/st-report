package vn.com.seatechit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchingFolderResultItem {
  private Integer index;
  private String folderName;
  private String cnPgdManager;
  private String cnPgdSaving;
  private String businessName;
  private String businessCode;
  private String customerType;
  private String customerCode;
  private String customerName;
  private String identification;
  private String mobilePhone;
  private LocalDateTime createdAt;
}
