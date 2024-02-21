package com.example.mortgageCalculator.util;

import java.util.Scanner;

public class Prompts {

    public static double promptUser(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }

    public static int promptUserInt(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextInt();
    }
}
