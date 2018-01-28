package cz.edee111.mortgagecalc;

import cz.edee111.mortgagecalc.model.Lending;
import cz.edee111.mortgagecalc.model.Mortgage;
import cz.edee111.mortgagecalc.model.WustenrotLoan;
import cz.edee111.mortgagecalc.paying.MortgagePayingStrategy;
import cz.edee111.mortgagecalc.paying.WustenrotLoanPayingStrategy;
import cz.edee111.mortgagecalc.service.LendingService;
import cz.edee111.mortgagecalc.service.LoanVariantDto;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduard Tomek
 */
public class VariantsTest {

  private LendingService lendingService = new LendingService();

  @Test
  public void testVariants() {
    LoanVariantDto variant1 = lendingService.calculateVariant(getLendings1());
    LoanVariantDto variant2 = lendingService.calculateVariant(getLendings2());

    printVariant("80%", variant1);
    printVariant("90%", variant2);
  }

  private void printVariant(String name, LoanVariantDto variant) {
    System.out.println(name + "=======================");
    System.out.println("Total borrowed amount: " + variant.getTotalBorrowedAmount());
    System.out.println("Total interest: " + variant.getTotalInterest());
    System.out.println("Total payed amount: " + variant.getTotalPayedAmount());
  }

  private List<Lending> getLendings1() {
    Mortgage mortgage = new Mortgage();
    mortgage.setName("Mortgage");
    mortgage.setAmount(BigDecimal.valueOf(3942000));
    mortgage.setFulfilmentMonths(12 * 30);
    mortgage.setInterestRate(BigDecimal.valueOf(0.0229));
    mortgage.setStartMonth(LocalDate.of(2018, 1, 1));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());

    WustenrotLoan loan = new WustenrotLoan();
    loan.setName("Wustenrot loan");
    loan.setAmount(BigDecimal.valueOf(700000));
    loan.setFulfilmentMonths(12 * 6);
    loan.setInterestRate(BigDecimal.valueOf(0.0489));
    loan.setInterestRate2(BigDecimal.valueOf(0.0299));
    loan.setLoanFulfiledPercToEnableInterestRate2(BigDecimal.valueOf(0.25)); //25%
    loan.setPayingStrategy(new WustenrotLoanPayingStrategy());
    loan.setStartMonth(LocalDate.of(2018, 1 ,1));

    List<Lending> lendings = new ArrayList<>();
    lendings.add(mortgage);
    lendings.add(loan);

    return lendings;
  }

  private List<Lending> getLendings2() {
    Mortgage mortgage = new Mortgage();
    mortgage.setName("Mortgage");
    mortgage.setAmount(BigDecimal.valueOf(3504000));
    mortgage.setFulfilmentMonths(12 * 30);
    mortgage.setInterestRate(BigDecimal.valueOf(0.0249));
    mortgage.setStartMonth(LocalDate.of(2018, 1, 1));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());

    WustenrotLoan loan = new WustenrotLoan();
    loan.setName("Wustenrot loan");
    loan.setAmount(BigDecimal.valueOf(300000));
    loan.setFulfilmentMonths(12 * 6);
    loan.setInterestRate(BigDecimal.valueOf(0.0489));
    loan.setInterestRate2(BigDecimal.valueOf(0.0299));
    loan.setLoanFulfiledPercToEnableInterestRate2(BigDecimal.valueOf(0.25)); //25%
    loan.setPayingStrategy(new WustenrotLoanPayingStrategy());
    loan.setStartMonth(LocalDate.of(2018, 1 ,1));

    List<Lending> lendings = new ArrayList<>();
    lendings.add(mortgage);
    lendings.add(loan);

    return lendings;
  }
}
