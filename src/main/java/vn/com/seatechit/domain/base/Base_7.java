package vn.com.seatechit.domain.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Base_7 extends IBase<Base_7_I> {
  private String k1;
  private String k2;
  private String k3;
  private String k4;
  private String k5;
  private String k6;
  private String k7;
}
