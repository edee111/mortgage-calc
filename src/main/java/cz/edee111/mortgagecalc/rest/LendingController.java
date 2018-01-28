package cz.edee111.mortgagecalc.rest;

import cz.edee111.mortgagecalc.model.Lending;
import cz.edee111.mortgagecalc.model.Mortgage;
import cz.edee111.mortgagecalc.model.WustenrotLoan;
import cz.edee111.mortgagecalc.paying.MortgagePayingStrategy;
import cz.edee111.mortgagecalc.paying.WustenrotLoanPayingStrategy;
import cz.edee111.mortgagecalc.service.LendingService;
import cz.edee111.mortgagecalc.service.LoanVariantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduard Tomek
 */
@Controller
@RequestMapping(value = "/lending")
public class LendingController {

  @Autowired
  private LendingService lendingService;

  @RequestMapping(method = RequestMethod.GET)
  @ResponseBody
  public LoanVariantDto index() {
    LoanVariantDto variant = lendingService.calculateVariant(prepareLendings());

    System.out.println(variant.getAllLendingInfo());
    System.out.println(variant.getTotalStatsInfo());

    return variant;
  }

  private List<Lending> prepareLendings() {
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

    return lendings;
  }


}
