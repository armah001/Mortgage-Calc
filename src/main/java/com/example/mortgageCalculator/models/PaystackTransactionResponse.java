package com.example.mortgageCalculator.models;

import lombok.Data;
@Data
public class PaystackTransactionResponse {
    private boolean status;
    private String message;

    public boolean isSuccess(){
        return "success".equalsIgnoreCase(message);
    }

}