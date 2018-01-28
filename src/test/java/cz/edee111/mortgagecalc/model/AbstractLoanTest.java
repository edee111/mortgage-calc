package cz.edee111.mortgagecalc.model;

import cz.edee111.mortgagecalc.paying.MortgagePayingStrategy;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public class AbstractLoanTest {

  @Test
  public void testAbstractLoan() {
    TestLoan testLoan = new TestLoan();
    testLoan.setName("Hypoteka");
    testLoan.setAmount(BigDecimal.valueOf(1000000));
    testLoan.setFulfilmentMonths(12 * 15);
    testLoan.setInterestRate(BigDecimal.valueOf(0.05));
    testLoan.setStartMonth(LocalDate.of(2018, 1, 1));
    testLoan.setPayingStrategy(null); //ignored

    Assertions.assertThat(testLoan.getFullName()).isEqualTo("Hypoteka, 1000000.00 CZK for 5.00%");
  }

  private class TestLoan extends AbstractLoan {

  }
}
