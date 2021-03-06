package vn.com.seatechit.web.errors;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorVM {
  private final String objectName;
  private final String field;
  private final String message;
}
