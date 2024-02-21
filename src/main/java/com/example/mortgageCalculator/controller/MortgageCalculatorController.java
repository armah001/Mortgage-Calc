package com.example.mortgageCalculator.controller;

import com.example.mortgageCalculator.models.LoanInformation;
import com.example.mortgageCalculator.models.PaystackTransactionResponse;
import com.example.mortgageCalculator.service.MortgageCalculatorService;
import com.example.mortgageCalculator.service.PaystackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MortgageCalculatorController {
    private final MortgageCalculatorService mortgageCalculatorService;
    private final PaystackService paystackService;


    public MortgageCalculatorController(MortgageCalculatorService mortgageCalculatorService, PaystackService paystackService) {
        this.mortgageCalculatorService = mortgageCalculatorService;
        this.paystackService = paystackService;
    }

    @PostMapping("/calculateMortgageFixed")
    public LoanInformation calculateMortgage(@RequestBody LoanInformation loanInformation) {
        return mortgageCalculatorService.calculateMortgage(loanInformation);
    }

    @PostMapping("/initiatePayment")
    public ResponseEntity<String> initiatePayment(@RequestBody LoanInformation loanInformation, String customerEmail){
        // Assuming loanInformation contains necessary details like amount and customerId
        double monthlyPaymentAmount = loanInformation.getMonthlyPayment();
        // Call Paystack service to initiate payment
        PaystackTransactionResponse response = paystackService.initiatePayment(monthlyPaymentAmount, customerEmail);

        // Handle the response from Paystack
        if (response.isSuccess()) {
            // Payment initiated successfully, update your application state accordingly
            return ResponseEntity.ok("Payment initiated successfully");
        } else {
            // Payment initiation failed, provide appropriate feedback
            return ResponseEntity.badRequest().body("Payment initiation failed: " + response.getMessage());
        }
    }

}
