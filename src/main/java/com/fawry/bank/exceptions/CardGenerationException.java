package com.fawry.bank.exceptions;

public class CardGenerationException extends RuntimeException {
    public CardGenerationException(String message) {
        super(message);
    }
}
