package cz.edee111.mortgagecalc.paying;

import cz.edee111.mortgagecalc.payment.LendingPayment;
import cz.edee111.mortgagecalc.payment.MortgagePayment;

import java.math.BigDecimal;

/**
 * @author Eduard Tomek
 */
public class MortgagePayingStrategy implements PayingStrategy {

  @Override
  public LendingPayment makePayment(PayingContext context) {

    BigDecimal amount = context.getLending().getSinglePaymentAmount();
    BigDecimal actualAmount = context.pay(amount);

    MortgagePayment payment = new MortgagePayment();
    payment.setAmount(actualAmount);
    payment.setAmountLeft(context.getAmountLeft());
    payment.setOrder(context.getOrder());
    payment.setMonth(context.getMonth());

    return payment;
  }
}
