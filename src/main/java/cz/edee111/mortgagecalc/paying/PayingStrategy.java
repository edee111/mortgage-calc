package cz.edee111.mortgagecalc.paying;

import cz.edee111.mortgagecalc.payment.LendingPayment;

/**
 * @author Eduard Tomek
 */
public interface PayingStrategy {

  LendingPayment makePayment(PayingContext context);

}
