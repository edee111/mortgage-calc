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

  public LoanVariantDto calculatePayments(List<Lending> lending) {
    return null;
  }

  public LoanVariantDto calculatePayments(Lending lending) {
    PayingContext context = PayingContext.of(lending);

    List<LendingPayment> payments = new ArrayList<>();
    while(!context.isFullyPayed()) {
      payments.add(context.paySinglePayment());
    }

    LoanVariantDto res = new LoanVariantDto();
    res.addAll(lending.getName(), payments);

    return res;
  }

}
