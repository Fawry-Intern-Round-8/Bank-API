package com.fawry.bank.utils;

import java.util.Random;

public class CardNumberGenerator {
    private static final String BIN = "4";
    private static final int LENGTH = 15;
    private static final Random random = new Random();

    public static String generate() {
        StringBuilder cardNumber = new StringBuilder(BIN);

        for (int i = 1; i < LENGTH; i++) {
            cardNumber.append(random.nextInt(10));
        }

        cardNumber.append(calculateLuhnCheckDigit(cardNumber.toString()));

        return cardNumber.toString();
    }

    private static int calculateLuhnCheckDigit(String partialCardNumber) {
        int sum = 0;
        boolean alternate = false;

        for (int i = partialCardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(partialCardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = (digit % 10) + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }

        return (10 - (sum % 10)) % 10;
    }
}
