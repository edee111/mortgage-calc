package cz.edee111.mortgagecalc.service;

import cz.edee111.mortgagecalc.model.Lending;
import cz.edee111.mortgagecalc.model.Mortgage;
import cz.edee111.mortgagecalc.model.WustenrotLoan;
import cz.edee111.mortgagecalc.paying.MortgagePayingStrategy;
import cz.edee111.mortgagecalc.paying.WustenrotLoanPayingStrategy;
import cz.edee111.mortgagecalc.payment.LendingPayment;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Eduard Tomek
 */
public class LendingServiceTest {

  private static final Offset<BigDecimal> OFFSET = Offset.offset(BigDecimal.valueOf(0.01));

  @Test
  public void testCalculatePaymentsMortage1() {
    Mortgage mortgage = new Mortgage();
    mortgage.setName("Mortgage");
    mortgage.setAmount(BigDecimal.valueOf(1000000));
    mortgage.setFulfilmentMonths(12 * 15);
    mortgage.setInterestRate(BigDecimal.valueOf(0.05));
    mortgage.setStartMonth(LocalDate.of(2018, 1, 1));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());

    LoanVariantDto variant = new LendingService().calculateVariant(mortgage);

    Assertions.assertThat(variant.getBorrowedAmountOf(mortgage.getName())).isEqualTo(BigDecimal.valueOf(1000000));
    Assertions.assertThat(variant.getTotalBorrowedAmount()).isEqualTo(BigDecimal.valueOf(1000000));
    Assertions.assertThat(variant.getTotalPayedAmount()).isCloseTo(BigDecimal.valueOf(1423428.52), OFFSET);
    Assertions.assertThat(variant.getTotalInterest()).isCloseTo(BigDecimal.valueOf(423428.52), OFFSET);

    List<LendingPayment> lendingPayments = variant.getPayments(mortgage.getName());
    Assertions.assertThat(180).isEqualTo(lendingPayments.size());

    LendingPayment paym0 = lendingPayments.get(0);
    Assertions.assertThat(paym0.getAmount()).isCloseTo(BigDecimal.valueOf(7907.94), OFFSET);
    Assertions.assertThat(paym0.getPayedAmount()).isCloseTo(BigDecimal.valueOf(3741.27), OFFSET);
    Assertions.assertThat(paym0.getInterestAmount()).isCloseTo(BigDecimal.valueOf(4166.67), OFFSET);
    Assertions.assertThat(paym0.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(1000000), OFFSET);
    Assertions.assertThat(paym0.getAmountLeftAfter()).isCloseTo(BigDecimal.valueOf(996258.73), OFFSET);
    Assertions.assertThat(paym0.getOrder()).isEqualTo(1);
    Assertions.assertThat(paym0.getMonth()).isEqualTo(LocalDate.of(2018, 1,1));

    LendingPayment paym50 = lendingPayments.get(50);
    Assertions.assertThat(paym50.getAmount()).isCloseTo(BigDecimal.valueOf(7907.94), OFFSET);
    Assertions.assertThat(paym50.getPayedAmount()).isCloseTo(BigDecimal.valueOf(4605.84), OFFSET);
    Assertions.assertThat(paym50.getInterestAmount()).isCloseTo(BigDecimal.valueOf(3302.09), OFFSET);
    Assertions.assertThat(paym50.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(792502.59), OFFSET);
    Assertions.assertThat(paym50.getAmountLeftAfter()).isCloseTo(BigDecimal.valueOf(787896.75), OFFSET);
    Assertions.assertThat(paym50.getOrder()).isEqualTo(51);
    Assertions.assertThat(paym50.getMonth()).isEqualTo(LocalDate.of(2022, 3,1));

    LendingPayment paym179 = lendingPayments.get(179);
    Assertions.assertThat(paym179.getAmount()).isCloseTo(BigDecimal.valueOf(7907.94), OFFSET);
    Assertions.assertThat(paym179.getPayedAmount()).isCloseTo(BigDecimal.valueOf(7875.12), OFFSET);
    Assertions.assertThat(paym179.getInterestAmount()).isCloseTo(BigDecimal.valueOf(32.81), OFFSET);
    Assertions.assertThat(paym179.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(7875.12), OFFSET);
    Assertions.assertThat(paym179.getAmountLeftAfter()).isCloseTo(BigDecimal.ZERO, OFFSET);
    Assertions.assertThat(paym179.getOrder()).isEqualTo(180);
    Assertions.assertThat(paym179.getMonth()).isEqualTo(LocalDate.of(2032, 12,1));
  }

  @Test
  public void testCalculatePaymentsMortage2() {
    Mortgage mortgage = new Mortgage();
    mortgage.setName("Mortgage");
    mortgage.setAmount(BigDecimal.valueOf(1805900));
    mortgage.setFulfilmentMonths(12 * 25);
    mortgage.setInterestRate(BigDecimal.valueOf(0.0269));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());
    mortgage.setStartMonth(LocalDate.of(2018, 1 ,1));

    LoanVariantDto variant = new LendingService().calculateVariant(mortgage);
    List<LendingPayment> lendingPayments = variant.getPayments(mortgage.getName());

    Assertions.assertThat(300).isEqualTo(lendingPayments.size());
    LendingPayment paym0 = lendingPayments.get(0);
    Assertions.assertThat(paym0.getAmount()).isCloseTo(BigDecimal.valueOf(8275.45), OFFSET);
    Assertions.assertThat(paym0.getPayedAmount()).isCloseTo(BigDecimal.valueOf(4227.22), OFFSET);
    Assertions.assertThat(paym0.getInterestAmount()).isCloseTo(BigDecimal.valueOf(4048.22), OFFSET);
    Assertions.assertThat(paym0.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(1805900), OFFSET);
    Assertions.assertThat(paym0.getAmountLeftAfter()).isCloseTo(BigDecimal.valueOf(1801672.77), OFFSET);
    Assertions.assertThat(paym0.getOrder()).isEqualTo(1);
    Assertions.assertThat(paym0.getMonth()).isEqualTo(LocalDate.of(2018, 1,1));

    LendingPayment paym50 = lendingPayments.get(50);
    Assertions.assertThat(paym50.getAmount()).isCloseTo(BigDecimal.valueOf(8275.45), OFFSET);
    Assertions.assertThat(paym50.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(1582502.94), OFFSET);
    Assertions.assertThat(paym50.getAmountLeftAfter()).isCloseTo(BigDecimal.valueOf(1577774.93), OFFSET);
    Assertions.assertThat(paym50.getOrder()).isEqualTo(51);
    Assertions.assertThat(paym50.getMonth()).isEqualTo(LocalDate.of(2022, 3,1));

    LendingPayment paym179 = lendingPayments.get(179);
    Assertions.assertThat(paym179.getAmount()).isCloseTo(BigDecimal.valueOf(8275.45), OFFSET);
    Assertions.assertThat(paym179.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(876163.03), OFFSET);
    Assertions.assertThat(paym179.getAmountLeftAfter()).isCloseTo(BigDecimal.valueOf(869851.64), OFFSET);
    Assertions.assertThat(paym179.getOrder()).isEqualTo(180);
    Assertions.assertThat(paym179.getMonth()).isEqualTo(LocalDate.of(2032, 12,1));

    LendingPayment paym299 = lendingPayments.get(299);
    Assertions.assertThat(paym299.getAmount()).isCloseTo(BigDecimal.valueOf(8275.45), OFFSET);
    Assertions.assertThat(paym299.getPayedAmount()).isCloseTo(BigDecimal.valueOf(8256.94), OFFSET);
    Assertions.assertThat(paym299.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(8256.94), OFFSET);
    Assertions.assertThat(paym299.getAmountLeftAfter()).isCloseTo(BigDecimal.ZERO, OFFSET);
    Assertions.assertThat(paym299.getOrder()).isEqualTo(300);
    Assertions.assertThat(paym299.getMonth()).isEqualTo(LocalDate.of(2042, 12,1));
  }

  @Test
  public void testCalculatePaymentsWustenrotLoan() {
    WustenrotLoan loan = new WustenrotLoan();
    loan.setName("Wustenrot loan");
    loan.setAmount(BigDecimal.valueOf(500000));
    loan.setFulfilmentMonths(12 * 5);
    loan.setInterestRate(BigDecimal.valueOf(0.0489));
    loan.setInterestRate2(BigDecimal.valueOf(0.0299));
    loan.setLoanFulfiledPercToEnableInterestRate2(BigDecimal.valueOf(0.25)); //25%
    loan.setPayingStrategy(new WustenrotLoanPayingStrategy());
    loan.setStartMonth(LocalDate.of(2018, 1 ,1));

    LoanVariantDto variant = new LendingService().calculateVariant(loan);
    Assertions.assertThat(variant.getTotalPayedAmount()).isCloseTo(BigDecimal.valueOf(550410.77), OFFSET);
    Assertions.assertThat(variant.getTotalBorrowedAmount()).isCloseTo(BigDecimal.valueOf(500000), OFFSET);
    Assertions.assertThat(variant.getTotalInterest()).isCloseTo(BigDecimal.valueOf(50410.77), OFFSET);

    List<LendingPayment> lendingPayments = variant.getPayments(loan.getName());

    Assertions.assertThat(59).isEqualTo(lendingPayments.size());

    LendingPayment paym0 = lendingPayments.get(0);
    Assertions.assertThat(paym0.getAmount()).isCloseTo(BigDecimal.valueOf(9410.44), OFFSET);
    Assertions.assertThat(paym0.getPayedAmount()).isCloseTo(BigDecimal.valueOf(7372.94), OFFSET);
    Assertions.assertThat(paym0.getInterestAmount()).isCloseTo(BigDecimal.valueOf(2037.50), OFFSET);
    Assertions.assertThat(paym0.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(500000), OFFSET);
    Assertions.assertThat(paym0.getAmountLeftAfter()).isCloseTo(BigDecimal.valueOf(492627.06), OFFSET);
    Assertions.assertThat(paym0.getOrder()).isEqualTo(1);
    Assertions.assertThat(paym0.getMonth()).isEqualTo(LocalDate.of(2018, 1,1));

    LendingPayment paym58 = lendingPayments.get(58);
    Assertions.assertThat(paym58.getAmount()).isCloseTo(BigDecimal.valueOf(4605.28), OFFSET);
    Assertions.assertThat(paym58.getPayedAmount()).isCloseTo(BigDecimal.valueOf(4593.83), OFFSET);
    Assertions.assertThat(paym58.getInterestAmount()).isCloseTo(BigDecimal.valueOf(11.44), OFFSET);
    Assertions.assertThat(paym58.getAmountLeftBefore()).isCloseTo(BigDecimal.valueOf(4593.82), OFFSET);
    Assertions.assertThat(paym58.getAmountLeftAfter()).isCloseTo(BigDecimal.ZERO, OFFSET);
    Assertions.assertThat(paym58.getOrder()).isEqualTo(59);
    Assertions.assertThat(paym58.getMonth()).isEqualTo(LocalDate.of(2022, 11,1));
  }

  @Test
  public void testCalculatePaymentsCombination() {
    Mortgage mortgage = new Mortgage();
    mortgage.setName("Mortgage");
    mortgage.setAmount(BigDecimal.valueOf(3800000));
    mortgage.setFulfilmentMonths(12 * 30);
    mortgage.setInterestRate(BigDecimal.valueOf(0.0249));
    mortgage.setStartMonth(LocalDate.of(2018, 1, 1));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());

    WustenrotLoan loan = new WustenrotLoan();
    loan.setName("Wustenrot loan");
    loan.setAmount(BigDecimal.valueOf(500000));
    loan.setFulfilmentMonths(12 * 5);
    loan.setInterestRate(BigDecimal.valueOf(0.0489));
    loan.setInterestRate2(BigDecimal.valueOf(0.0299));
    loan.setLoanFulfiledPercToEnableInterestRate2(BigDecimal.valueOf(0.25)); //25%
    loan.setPayingStrategy(new WustenrotLoanPayingStrategy());
    loan.setStartMonth(LocalDate.of(2018, 1 ,1));

    List<Lending> lendings = new ArrayList<>();
    lendings.add(mortgage);
    lendings.add(loan);

    LoanVariantDto variant = new LendingService().calculateVariant(lendings);

    System.out.println(variant.getAllLendingInfo());
    System.out.println(variant.getTotalStatsInfo());
  }

}
