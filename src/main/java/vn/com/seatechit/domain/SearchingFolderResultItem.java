package vn.com.seatechit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchingFolderResultItem {
  private Integer index;
  private String caseId;
  private String customerId;
  private String processName;
  private String createdBy;
  private String createdBranch;
  private String createdAt;
  private String creator;
  private String branchCreator;
  private String status;
}
