package cz.edee111.mortgagecalc;

import cz.edee111.mortgagecalc.model.Mortgage;
import cz.edee111.mortgagecalc.paying.MortgagePayingStrategy;
import cz.edee111.mortgagecalc.payment.LendingPayment;
import cz.edee111.mortgagecalc.service.LendingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Eduard Tomek
 */
public class Main {

  public static void main(String[] args) {
    Mortgage mortgage = new Mortgage();
    mortgage.setAmount(BigDecimal.valueOf(1000000));
    mortgage.setFulfilmentMonths(12 * 15);
    mortgage.setInterestRate(BigDecimal.valueOf(0.05));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());
    mortgage.setStartMonth(LocalDate.of(2018, 1 ,1));

    List<LendingPayment> lendingPayments = new LendingService().calculatePayments(mortgage);
    for (LendingPayment p : lendingPayments) {
      System.out.println(p);
    }
  }

}
