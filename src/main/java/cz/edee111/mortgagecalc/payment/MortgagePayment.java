package cz.edee111.mortgagecalc.payment;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @author Eduard Tomek
 */
public class MortgagePayment implements LendingPayment {

  private int order;
  private LocalDate month;
  private BigDecimal payedAmount;
  private BigDecimal interestAmount;
  private BigDecimal amountLeftAfter;
  private BigDecimal amountLeftBefore;

  @Override
  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  @Override
  public LocalDate getMonth() {
    return month;
  }

  public void setMonth(LocalDate month) {
    this.month = month;
  }

  @Override
  public BigDecimal getAmount() {
    return payedAmount.add(interestAmount);
  }

  @Override
  public BigDecimal getAmountLeftAfter() {
    return amountLeftAfter;
  }

  public void setAmountLeftAfter(BigDecimal amountLeftAfter) {
    this.amountLeftAfter = amountLeftAfter;
  }

  @Override
  public BigDecimal getPayedAmount() {
    return payedAmount;
  }

  public void setPayedAmount(BigDecimal payedAmount) {
    this.payedAmount = payedAmount;
  }

  @Override
  public BigDecimal getInterestAmount() {
    return interestAmount;
  }

  public void setInterestAmount(BigDecimal interestAmount) {
    this.interestAmount = interestAmount;
  }

  @Override
  public BigDecimal getAmountLeftBefore() {
    return amountLeftBefore;
  }

  public void setAmountLeftBefore(BigDecimal amountLeftBefore) {
    this.amountLeftBefore = amountLeftBefore;
  }

  @Override
  public String toString() {
    return "MortgagePayment{" +
        "order=" + order +
        ",\tmonth=" + month +
        ",\tamountLeftBefore=" + amountLeftBefore +
        ",\tpayedAmount=" + payedAmount +
        ",\tinterestAmount=" + interestAmount +
        ",\tamountLeftAfter=" + amountLeftAfter +
        '}';
  }
}
