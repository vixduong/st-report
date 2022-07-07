package vn.com.seatechit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
  private String name;
  private Date birthDate;
  private BigDecimal payment;
  private BigDecimal bonus;
}
