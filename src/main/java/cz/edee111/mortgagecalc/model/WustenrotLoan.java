package cz.edee111.mortgagecalc.model;

import cz.edee111.mortgagecalc.util.Utils;

import java.math.BigDecimal;

/**
 * @author Eduard Tomek
 */
@SuppressWarnings("SpellCheckingInspection")
public class WustenrotLoan extends Loan {

  private BigDecimal loanFulfiledPercToEnableInterestRate2;

  public BigDecimal getLoanFulfiledPercToEnableInterestRate2() {
    return loanFulfiledPercToEnableInterestRate2;
  }

  public void setLoanFulfiledPercToEnableInterestRate2(BigDecimal loanFulfiledPercToEnableInterestRate2) {
    this.loanFulfiledPercToEnableInterestRate2 = loanFulfiledPercToEnableInterestRate2;
  }

  @Override
  public boolean isInterestRate2On(BigDecimal fulfilledAmount) {
    if (BigDecimal.ZERO.compareTo(fulfilledAmount) == 0) {
      return false;
    }

    BigDecimal divide = getAmount().divide(fulfilledAmount, Utils.MC);
    BigDecimal fulfiledPerc = BigDecimal.ONE.divide(divide, Utils.MC);

    return fulfiledPerc.compareTo(loanFulfiledPercToEnableInterestRate2) >= 0;
  }
}
