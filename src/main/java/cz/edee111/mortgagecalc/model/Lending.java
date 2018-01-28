package cz.edee111.mortgagecalc.model;

import cz.edee111.mortgagecalc.paying.PayingStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public interface Lending {

  String getName();

  String getFullName();

  BigDecimal getInterestRate();

  BigDecimal getInterestRate2();

  boolean isInterestRate2On(BigDecimal fulfilledAmount);

  int getFulfilmentMonths();

  BigDecimal getAmount();

  PayingStrategy getPayingStrategy();

  BigDecimal getSinglePaymentAmount();

  LocalDate getStartMonth();

}
