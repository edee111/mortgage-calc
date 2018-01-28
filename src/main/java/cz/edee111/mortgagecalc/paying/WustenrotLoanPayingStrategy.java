package cz.edee111.mortgagecalc.paying;

import cz.edee111.mortgagecalc.payment.LendingPayment;
import cz.edee111.mortgagecalc.payment.LendingPaymentImpl;
import cz.edee111.mortgagecalc.util.Utils;

import java.math.BigDecimal;

/**
 * @author Eduard Tomek
 */
@SuppressWarnings("SpellCheckingInspection")
public class WustenrotLoanPayingStrategy implements PayingStrategy {

  @Override
  public LendingPayment makePayment(PayingContext context) {
    boolean interestRate2On = context.getLending().isInterestRate2On(context.getFulfilledAmount());
    BigDecimal interestRate = interestRate2On ? context.getLending().getInterestRate2() : context.getLending().getInterestRate();
    BigDecimal amountLeftBefore = context.getAmountLeft();
    BigDecimal interestForMonth = amountLeftBefore
        .multiply(interestRate)
        .divide(BigDecimal.valueOf(Utils.MONTHS_IN_YEAR), Utils.MC);

    BigDecimal amount = context.getLending().getSinglePaymentAmount();
    BigDecimal actualAmount = context.pay(interestForMonth, amount);

    LendingPaymentImpl payment = new LendingPaymentImpl();
    payment.setInterestAmount(interestForMonth);
    payment.setPayedAmount(actualAmount.subtract(interestForMonth));
    payment.setAmountLeftBefore(amountLeftBefore);
    payment.setAmountLeftAfter(context.getAmountLeft());
    payment.setOrder(context.getOrder());
    payment.setMonth(context.getMonth());

    return payment;
  }

}
