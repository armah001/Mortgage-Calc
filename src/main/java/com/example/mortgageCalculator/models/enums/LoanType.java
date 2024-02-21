package com.example.mortgageCalculator.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum LoanType {
    FIXED,
    ADJUSTABLE
}