package cz.edee111.mortgagecalc.service;

import cz.edee111.mortgagecalc.model.Lending;
import cz.edee111.mortgagecalc.paying.PayingContext;
import cz.edee111.mortgagecalc.payment.LendingPayment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduard Tomek
 */
public class LendingService {

  public List<LendingPayment> calculatePayments(Lending lending) {
    PayingContext context = PayingContext.of(lending);

    List<LendingPayment> payments = new ArrayList<>();
    while(!context.isFullyPayed()) {
      payments.add(context.paySinglePayment());
    }

    return payments;
  }

}
