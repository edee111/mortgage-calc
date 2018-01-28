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

  public LoanVariantDto calculateVariant(Lending lending) {
    List<Lending> lendingList = new ArrayList<>();
    lendingList.add(lending);
    return calculateVariant(lendingList);
  }

  public LoanVariantDto calculateVariant(List<Lending> lendings) {
    LoanVariantDto res = new LoanVariantDto();
    for(Lending l : lendings) {
      res.addAll(l.getName(), calculateLending(l));
    }

    return res;
  }

  private List<LendingPayment> calculateLending(Lending lending) {
    PayingContext context = PayingContext.of(lending);

    List<LendingPayment> payments = new ArrayList<>();
    while(!context.isFullyPayed()) {
      payments.add(context.paySinglePayment());
    }

    return payments;
  }

}
