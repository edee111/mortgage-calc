package cz.edee111.mortgagecalc.model;

import cz.edee111.mortgagecalc.paying.PayingStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Eduard Tomek
 */
public interface Lending {

  String getFullName();

  BigDecimal getInterestRate();

  int getFulfilmentMonths();

  BigDecimal getAmount();

  PayingStrategy getPayingStrategy();

  BigDecimal getSinglePaymentAmount();

  LocalDate getStartMonth();

}
