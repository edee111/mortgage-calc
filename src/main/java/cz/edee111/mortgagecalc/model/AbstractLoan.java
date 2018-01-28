package cz.edee111.mortgagecalc.model;

import cz.edee111.mortgagecalc.paying.PayingStrategy;
import cz.edee111.mortgagecalc.util.Utils;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public abstract class AbstractLoan implements Lending {

  private String name;
  private BigDecimal interestRate;
  private int fulfilmentMonths;
  private BigDecimal amount;
  private PayingStrategy payingStrategy;
  private LocalDate startMonth;

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getFullName() {
    return String.format("%s, %.2f CZK for %.2f%%", name, amount, interestRate.multiply(BigDecimal.valueOf(100)));
  }

  @Override
  public BigDecimal getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(BigDecimal interestRate) {
    this.interestRate = interestRate;
  }

  @Override
  public int getFulfilmentMonths() {
    return fulfilmentMonths;
  }

  public void setFulfilmentMonths(int fulfilmentMonths) {
    this.fulfilmentMonths = fulfilmentMonths;
  }

  @Override
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  @Override
  public PayingStrategy getPayingStrategy() {
    return payingStrategy;
  }

  public void setPayingStrategy(PayingStrategy payingStrategy) {
    this.payingStrategy = payingStrategy;
  }

  //see https://finance.idnes.cz/jak-si-spocitat-vysi-splatky-hypoteky-dhq-/pujcky.aspx?c=A061207_163959_fi_osobni_jjj
  @SuppressWarnings("Duplicates")
  @Override
  public BigDecimal getSinglePaymentAmount() {
    BigDecimal ipm = getInterestRate().divide(BigDecimal.valueOf(Utils.MONTHS_IN_YEAR), Utils.MC);
    BigDecimal value = BigDecimal.ONE.add(ipm).pow(getFulfilmentMonths());

    BigDecimal upper = getAmount().multiply(ipm).multiply(value);
    BigDecimal lower = value.subtract(BigDecimal.ONE);


    return upper.divide(lower, Utils.MC);
  }

  public void setStartMonth(LocalDate startMonth) {
    this.startMonth = startMonth;
  }

  @Override
  public LocalDate getStartMonth() {
    return startMonth;
  }
}
