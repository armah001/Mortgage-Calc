## Mortgage Calculator
This is a Java eb application that allows users to calculate their monthly mortgage payment. 

## Getting Started
To get started with this project, clone the repository and open the project in your favorite IDE or text editor. This project uses Maven as the build tool, so make sure you have it installed on your system.

## Prerequisites
- Java 11 or higher
- Maven

## Deployed Link
http://16.170.172.146:8080/calculateMortgageFixed

## SamplePost Request Body to Calculate Mortgage
{
"loanAmount": 100000.0,
  "interestRate": 5.0,
  "loanDuration": 30,
  "loanType": "FIXED",
  "paymentFrequency": "MONTHLY"
}
- Notes:
- The allowed loan types are: "FIXED" and "ADJUSTABLE"
-  The allowed payment frequency are "MONTHLY" ,"QUARTERLY", "ANNUALLY"
