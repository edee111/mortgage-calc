package cz.edee111.mortgagecalc.service;

import cz.edee111.mortgagecalc.model.Mortgage;
import cz.edee111.mortgagecalc.paying.MortgagePayingStrategy;
import cz.edee111.mortgagecalc.payment.LendingPayment;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * @author Eduard Tomek
 */
public class LendingServiceTest {

  private static final Offset<BigDecimal> OFFSET = Offset.offset(BigDecimal.valueOf(0.01));

  @Test
  public void testCalculatePaymentsMortage() {
    Mortgage mortgage = new Mortgage();
    mortgage.setAmount(BigDecimal.valueOf(1000000));
    mortgage.setFulfilmentMonths(12 * 15);
    mortgage.setInterestRate(BigDecimal.valueOf(0.05));
    mortgage.setStartMonth(LocalDate.of(2018, 1, 1));
    mortgage.setPayingStrategy(new MortgagePayingStrategy());

    List<LendingPayment> lendingPayments = new LendingService().calculatePayments(mortgage);

    Assertions.assertThat(180).isEqualTo(lendingPayments.size());
    LendingPayment paym0 = lendingPayments.get(0);
    Assertions.assertThat(paym0.getAmount()).isCloseTo(BigDecimal.valueOf(7907.94), OFFSET);
    Assertions.assertThat(paym0.getAmountLeft()).isCloseTo(BigDecimal.valueOf(996258.73), OFFSET);
    Assertions.assertThat(paym0.getOrder()).isEqualTo(1);
    Assertions.assertThat(paym0.getMonth()).isEqualTo(LocalDate.of(2018, 1,1));

    LendingPayment paym50 = lendingPayments.get(50);
    Assertions.assertThat(paym50.getAmount()).isCloseTo(BigDecimal.valueOf(7907.94), OFFSET);
    Assertions.assertThat(paym50.getAmountLeft()).isCloseTo(BigDecimal.valueOf(787896.75), OFFSET);
    Assertions.assertThat(paym50.getOrder()).isEqualTo(51);
    Assertions.assertThat(paym50.getMonth()).isEqualTo(LocalDate.of(2022, 3,1));

    LendingPayment paym179 = lendingPayments.get(179);
    Assertions.assertThat(paym179.getAmount()).isCloseTo(BigDecimal.valueOf(7907.94), OFFSET);
    Assertions.assertThat(paym179.getAmountLeft()).isCloseTo(BigDecimal.ZERO, OFFSET);
    Assertions.assertThat(paym179.getOrder()).isEqualTo(180);
    Assertions.assertThat(paym179.getMonth()).isEqualTo(LocalDate.of(2032, 12,1));
  }
}
