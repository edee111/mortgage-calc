package cz.edee111.mortgagecalc.paying;

import cz.edee111.mortgagecalc.model.Lending;
import cz.edee111.mortgagecalc.payment.LendingPayment;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public class PayingContext {

  private int order;
  private LocalDate month;
  private Lending lending;
  private BigDecimal amountLeft;

  public static PayingContext of(Lending lending) {
    return new PayingContext(lending, lending.getAmount(), 0, lending.getStartMonth().withDayOfMonth(1));
  }

  private PayingContext(Lending lending, BigDecimal amountLeft, int order, LocalDate month) {
    this.lending = lending;
    this.amountLeft = amountLeft;
    this.order = order;
    this.month = month;
  }

  public Lending getLending() {
    return lending;
  }

  public BigDecimal getAmountLeft() {
    return amountLeft;
  }

  public int getOrder() {
    return order;
  }

  public LocalDate getMonth() {
    return month;
  }

  public BigDecimal pay(BigDecimal amount) {
    BigDecimal actuallyPayedAmount;
    if (amountLeft.compareTo(amount) > 0) {
      actuallyPayedAmount = amount;
      this.amountLeft = this.amountLeft.subtract(amount);
    }
    else {
      actuallyPayedAmount = this.amountLeft;
      this.amountLeft = BigDecimal.ZERO;
    }

    return actuallyPayedAmount;
  }

  public LendingPayment paySinglePayment() {
    order++;
    month = month.plusMonths(1);
    return this.lending.getPayingStrategy().makePayment(this);
  }

  public boolean isFullyPayed() {
    return this.amountLeft.equals(BigDecimal.ZERO);
  }
}
