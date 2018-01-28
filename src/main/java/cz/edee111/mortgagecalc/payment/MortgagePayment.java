package cz.edee111.mortgagecalc.payment;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @author Eduard Tomek
 */
public class MortgagePayment implements LendingPayment {

  private int order;
  private LocalDate month;
  private BigDecimal amount;
  private BigDecimal amountLeft;

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
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public BigDecimal getAmountLeft() {
    return amountLeft;
  }

  public void setAmountLeft(BigDecimal amountLeft) {
    this.amountLeft = amountLeft;
  }

  @Override
  public String toString() {
    return "MortgagePayment{" +
        "order=" + order +
        ",\tmonth=" + month +
        ",\tamount=" + amount +
        ",\tamountLeft=" + amountLeft +
        '}';
  }
}
