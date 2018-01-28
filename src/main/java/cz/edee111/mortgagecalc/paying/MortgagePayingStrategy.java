package cz.edee111.mortgagecalc.paying;

import cz.edee111.mortgagecalc.payment.LendingPayment;
import cz.edee111.mortgagecalc.payment.MortgagePayment;
import cz.edee111.mortgagecalc.util.Utils;

import java.math.BigDecimal;

/**
 * @author Eduard Tomek
 */
public class MortgagePayingStrategy implements PayingStrategy {

  @Override
  public LendingPayment makePayment(PayingContext context) {
    BigDecimal amountLeftBefore = context.getAmountLeft();
    BigDecimal interestForMonth = (amountLeftBefore.multiply(context.getLending().getInterestRate()))
        .divide(BigDecimal.valueOf(Utils.MONTHS_IN_YEAR), Utils.MC);

    BigDecimal amount = context.getLending().getSinglePaymentAmount();
    BigDecimal actualAmount = context.pay(interestForMonth, amount);

    MortgagePayment payment = new MortgagePayment();
    payment.setInterestAmount(interestForMonth);
    payment.setPayedAmount(actualAmount.subtract(interestForMonth));
    payment.setAmountLeftBefore(amountLeftBefore);
    payment.setAmountLeftAfter(context.getAmountLeft());
    payment.setOrder(context.getOrder());
    payment.setMonth(context.getMonth());

    return payment;
  }
}
