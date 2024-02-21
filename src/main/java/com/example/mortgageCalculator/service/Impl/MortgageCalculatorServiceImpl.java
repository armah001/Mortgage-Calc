package com.example.mortgageCalculator.service.Impl;

import com.example.mortgageCalculator.models.LoanInformation;
import com.example.mortgageCalculator.models.enums.LoanType;
import com.example.mortgageCalculator.service.MortgageCalculatorService;
import com.example.mortgageCalculator.service.PaystackService;
import com.example.mortgageCalculator.models.PaystackTransactionResponse;
import com.example.mortgageCalculator.util.ValidateLoanInformation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MortgageCalculatorServiceImpl  implements MortgageCalculatorService {

    private final ValidateLoanInformation validateLoanInformation;
    private final PaystackService paystackService;

    public MortgageCalculatorServiceImpl(ValidateLoanInformation validateLoanInformation, PaystackService paystackService) {
        this.validateLoanInformation = validateLoanInformation;
        this.paystackService = paystackService;
    }


    @Override
    public LoanInformation calculateMortgage(LoanInformation loanInformation) {

        try {
            validateLoanInformation.validateLoanInfo(loanInformation);


            if (LoanType.FIXED.equals(loanInformation.getLoanType())) {
                return calculateFixedMortgage(loanInformation);
            } else if (LoanType.ADJUSTABLE.equals(loanInformation.getLoanType())) {
                return calculateAdjustableRateMortgage(loanInformation);
            }
            return loanInformation;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public LoanInformation calculateFixedMortgage(LoanInformation loanInformation) {
        double loanAmount = loanInformation.getLoanAmount();
        double interestRate = loanInformation.getInterestRate() / 100.0 / 12.0; // Monthly interest rate

        //Dividing the loan duration by 12 to get the loan duration in months
        int loanDurationMonths = loanInformation.getLoanDuration() * 12;

        double monthlyPayment = loanAmount * interestRate / (1 - Math.pow(1 + interestRate, -loanDurationMonths));
        loanInformation.setMonthlyPayment(monthlyPayment);

        return loanInformation;
    }

    @Override
    public LoanInformation calculateAdjustableRateMortgage(LoanInformation loanInformation) {
        double loanAmount = loanInformation.getLoanAmount();
        double initialInterestRate = loanInformation.getInterestRate() / 100.0 / 12.0; // Monthly interest rate for the initial fixed-rate period
        double variableInterestRate = 0.02;
        int fixedRatePeriodMonths = 12;

        double monthlyPayment = calculateFixedRateMonthlyPayment(loanAmount, initialInterestRate, fixedRatePeriodMonths);
        loanInformation.setMonthlyPayment(monthlyPayment);

        return loanInformation;
    }

    private double calculateFixedRateMonthlyPayment(double loanAmount, double interestRate, int loanDurationMonths) {
        return loanAmount * interestRate / (1 - Math.pow(1 + interestRate, -loanDurationMonths));
    }

    // In your backend service or controller
    public ResponseEntity<String> initiatePayment(double monthlyPaymentAmount, String customerId) {
        // Use Paystack API to initiate payment
        PaystackTransactionResponse response = paystackService.initiatePayment(monthlyPaymentAmount, customerId);

        // Handle the response from Paystack
        if (response.isSuccess()) {
            // Update your application state, mark payment as successful, etc.
            return ResponseEntity.ok("Payment initiated successfully");
        } else {
            // Handle payment failure
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment initiation failed: " + response.getMessage());
        }
    }


}
