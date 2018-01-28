package cz.edee111.mortgagecalc.service;

import cz.edee111.mortgagecalc.payment.LendingPayment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eduard Tomek
 */
public class LoanVariantDto {

  private Map<String, List<LendingPayment>> payments = new HashMap<>();

  public void addAll(String loanName, List<LendingPayment> payments) {
    List<LendingPayment> lendingPayments = this.payments.computeIfAbsent(loanName, k -> new ArrayList<>());
    lendingPayments.addAll(payments);
  }

  public BigDecimal getTotalBorrowedAmount() {
    return null;
  }


}
