package cz.edee111.mortgagecalc.payment;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @author Eduard Tomek
 */
public class LendingPaymentImpl implements LendingPayment {

  private int order;
  private LocalDate month;
  private BigDecimal payedAmount;
  private BigDecimal interestAmount;
  private BigDecimal amount;
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
    return amount;
  }

  public void setAmountIfPossible() {
    if (this.payedAmount != null && this.interestAmount != null) {
      this.amount =  payedAmount.add(interestAmount);
    }
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
    setAmountIfPossible();
  }

  @Override
  public BigDecimal getInterestAmount() {
    return interestAmount;
  }

  public void setInterestAmount(BigDecimal interestAmount) {
    this.interestAmount = interestAmount;
    setAmountIfPossible();
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
    final StringBuilder sb = new StringBuilder("LendingPaymentImpl{");
    sb.append("\torder=").append(order);
    sb.append(",\tmonth=").append(month);
    sb.append(",\tmountLeftBefore=").append(amountLeftBefore);
    sb.append(",\tamount=").append(getAmount());
    sb.append(",\tpayedAmount=").append(payedAmount);
    sb.append(",\tinterestAmount=").append(interestAmount);
    sb.append(",\tamountLeftAfter=").append(amountLeftAfter);
    sb.append('}');
    return sb.toString();
  }
}
