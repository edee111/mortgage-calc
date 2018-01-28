package cz.edee111.mortgagecalc;

import cz.edee111.mortgagecalc.model.Mortgage;
import cz.edee111.mortgagecalc.paying.MortgagePayingStrategy;
import cz.edee111.mortgagecalc.payment.LendingPayment;
import cz.edee111.mortgagecalc.service.LendingService;
import cz.edee111.mortgagecalc.service.LoanVariantDto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public class Main {

  public static void main(String[] args) {
    Mortgage mortgage = new Mortgage();
    mortgage.setName("Mortgage");
    mortgage.setAmount(BigDecimal.valueOf(1805900));
    mortgage.setFulfilmentMonths(12 * 25);
    mortgage.setInterestRate(BigDecimal.valueOf(0.0269));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());
    mortgage.setStartMonth(LocalDate.of(2018, 1 ,1));

    LoanVariantDto variant = new LendingService().calculateVariant(mortgage);
    for (LendingPayment p : variant.getPayments(mortgage.getName())) {
      System.out.println(p);
    }
  }

}
