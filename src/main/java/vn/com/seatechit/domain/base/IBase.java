package vn.com.seatechit.domain.base;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public abstract class IBase<T> {
  @NotNull
  @NotEmpty
  @Pattern(regexp = "^\\w+\\.xlsx$", message = "File name must be in format: '{fileName}.xlsx'")
  private String templateName;

  private List<T> items;
}
