package com.example.mortgageCalculator.util;

import com.example.mortgageCalculator.models.LoanInformation;
import com.example.mortgageCalculator.models.enums.LoanType;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class ValidateLoanInformation {
    public void validateLoanInfo(LoanInformation loanInformation) {
        if (loanInformation.getLoanAmount() <= 0 || loanInformation.getInterestRate() < 0 || loanInformation.getLoanDuration() <= 0) {
            throw new IllegalArgumentException("Invalid loan information. Please provide positive values for loan amount, interest rate, and loan duration.");
        }

        //validation for enums
        if (loanInformation.getLoanType() == null || loanInformation.getPaymentFrequency() == null) {
            throw new IllegalArgumentException("Loan type and payment frequency are required.");
        }

        if (!EnumSet.of(LoanType.FIXED, LoanType.ADJUSTABLE).contains(loanInformation.getLoanType())) {
            throw new IllegalArgumentException("Invalid loan type. Supported types are: FIXED, ADJUSTABLE");
        }

    }
}
