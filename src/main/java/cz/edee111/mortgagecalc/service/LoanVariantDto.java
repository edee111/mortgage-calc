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
    return payments.values().stream()
        .map(it -> it.get(0).getAmountLeftBefore())
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getBorrowedAmountOf(String loanName) {
    return payments.get(loanName).get(0).getAmountLeftBefore();
  }

  public BigDecimal getTotalPayedAmount() {
    return payments.values().stream()
        .map(it -> it.stream().map(LendingPayment::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getTotalInterest() {
    return payments.values().stream()
        .map(it -> it.stream().map(LendingPayment::getInterestAmount).reduce(BigDecimal.ZERO, BigDecimal::add))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public List<LendingPayment> getPayments(String loanName) {
    return this.payments.get(loanName);
  }

}
