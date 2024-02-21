package com.example.mortgageCalculator.models;

import com.example.mortgageCalculator.models.enums.LoanType;
import com.example.mortgageCalculator.models.enums.PaymentFrequency;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoanInformation {
        private double loanAmount;
        private double interestRate;
        int loanDuration;
        private double monthlyPayment;
        private LoanType loanType;
        private PaymentFrequency paymentFrequency;

        public LoanInformation(double loanAmount, double interestRate, int loanDuration, LoanType loanType, PaymentFrequency paymentFrequency) {
            this.loanAmount = loanAmount;
            this.interestRate = interestRate;
            this.loanDuration = loanDuration;
            this.loanType = loanType;
            this.paymentFrequency = paymentFrequency;
        }

}
