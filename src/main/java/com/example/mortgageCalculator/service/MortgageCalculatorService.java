package com.example.mortgageCalculator.service;


import com.example.mortgageCalculator.models.LoanInformation;

public interface MortgageCalculatorService {

    LoanInformation calculateMortgage(LoanInformation loanInformation);
    LoanInformation calculateFixedMortgage(LoanInformation loanInformation);
    LoanInformation calculateAdjustableRateMortgage(LoanInformation loanInformation);


}
