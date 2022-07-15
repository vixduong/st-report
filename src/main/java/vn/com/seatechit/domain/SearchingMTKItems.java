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
public class SearchingMTKItems {
    private Integer index;
    private String caseId;
    private String taskName;
    private String customerId;
    private String customerName;
    private String customerType;
    private String identification;
    private String identificationType;
    private String gender;
    private String phone;
    private String email;
    private String nv_mgCode;
    private String nv_mgName;
    private String cnPGD;
    private String createAt;
    private String docStatus;
    private String accountType;
    private String accountType2;
    private String cancelReason;
    private String considerPhone;
    private String contactReason;
}
