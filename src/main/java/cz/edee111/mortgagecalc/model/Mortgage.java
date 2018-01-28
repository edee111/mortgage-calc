package cz.edee111.mortgagecalc.model;

import cz.edee111.mortgagecalc.paying.PayingStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public class Mortgage implements Lending {

  private BigDecimal interestRate;
  private int fulfilmentMonths;
  private BigDecimal amount;
  private PayingStrategy payingStrategy;
  private LocalDate startMonth;

  @Override
  public BigDecimal getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
  }

  @Override
  public int getFulfilmentMonths() {
    return fulfilmentMonths;
  }

  public void setFulfilmentMonths(int fulfilmentMonths) {
    this.fulfilmentMonths = fulfilmentMonths;
  }

  @Override
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public PayingStrategy getPayingStrategy() {
    return payingStrategy;
  }

  public void setPayingStrategy(PayingStrategy payingStrategy) {
    this.payingStrategy = payingStrategy;
  }

  @Override
  public BigDecimal getSinglePaymentAmount() {
    return this.amount.multiply(interestRate).divide(BigDecimal.valueOf(fulfilmentMonths), RoundingMode.HALF_UP);
  }

  public void setStartMonth(LocalDate startMonth) {
    this.startMonth = startMonth;
  }

  @Override
  public LocalDate getStartMonth() {
    return startMonth;
  }

}
