package cz.edee111.mortgagecalc.service;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonProperty
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

  public String getAllLendingInfo() {
    StringBuilder sb = new StringBuilder();
    for (String name : payments.keySet()) {
      sb.append(getLendingInfo(name)).append("\n");
    }

    return sb.toString();
  }

  public String getLendingInfo(String name) {
    List<LendingPayment> lendingPayments = this.payments.get(name);

    StringBuilder sb = new StringBuilder(name).append("\n========================\n");
    for (LendingPayment p : lendingPayments) {
      sb.append(p).append("\n");
    }
    sb.append("\n==============\n");

    return sb.toString();
  }

  public String getTotalStatsInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append("=== Summary ===");
    sb.append("\nTotal borrowed amount:\t").append(getTotalBorrowedAmount());
    sb.append("\nTotal interest:       \t").append(getTotalInterest());
    sb.append("\nTotal payed amount:   \t").append(getTotalPayedAmount());
    sb.append("\n=== Summary end ===\n");
    return sb.toString();
  }

}
