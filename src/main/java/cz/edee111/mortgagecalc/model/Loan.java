package cz.edee111.mortgagecalc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import cz.edee111.mortgagecalc.paying.PayingStrategy;

/**
 * @author Eduard Tomek
 */
public class Loan implements Lending {

  @Override
  public BigDecimal getInterestRate() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getFulfilmentMonths() {
    throw new UnsupportedOperationException();
  }

  @Override
  public BigDecimal getAmount() {
    throw new UnsupportedOperationException();
  }

  @Override
  public PayingStrategy getPayingStrategy() {
    throw new UnsupportedOperationException();
  }

  @Override
  public BigDecimal getSinglePaymentAmount() {
    return null;
  }

  @Override
  public LocalDate getStartMonth() {
    return null;
  }
}
