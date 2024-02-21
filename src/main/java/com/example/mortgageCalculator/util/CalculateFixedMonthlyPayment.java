package com.example.mortgageCalculator.util;

public class CalculateFixedMonthlyPayment {

    // Helper method for calculating monthly payment during a fixed-rate period
    private double calculateFixedRateMonthlyPayment(double loanAmount, double interestRate, int loanDurationMonths) {
        return loanAmount * interestRate / (1 - Math.pow(1 + interestRate, -loanDurationMonths));
    }
}
