package cz.edee111.mortgagecalc.payment;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public interface LendingPayment {

  int getOrder();
  LocalDate getMonth();

  BigDecimal getAmount();
  BigDecimal getPayedAmount();
  BigDecimal getInterestAmount();

  BigDecimal getAmountLeftBefore();
  BigDecimal getAmountLeftAfter();
}
