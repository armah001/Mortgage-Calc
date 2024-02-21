package com.example.mortgageCalculator.service;

import com.example.mortgageCalculator.models.PaystackTransactionResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaystackService {

    private final String paystackApiUrl = "https://api.paystack.co/transaction/initialize";
    private final String apiKey = "   ";

    public PaystackTransactionResponse initiatePayment(double amount, String customerId) {
        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);


        PaystackInitiatePaymentRequest request = new PaystackInitiatePaymentRequest(amount, customerId);

        //HTTP entity with headers and body
        HttpEntity<PaystackInitiatePaymentRequest> httpEntity = new HttpEntity<>(request, headers);


        ResponseEntity<PaystackTransactionResponse> responseEntity = new RestTemplate()
                .exchange(paystackApiUrl, HttpMethod.POST, httpEntity, PaystackTransactionResponse.class);


        return responseEntity.getBody();
    }
}

// Request and response classes

class PaystackInitiatePaymentRequest {
    private double amount;
    private String email;

    public PaystackInitiatePaymentRequest(double amount, String customerId) {
    }
}


