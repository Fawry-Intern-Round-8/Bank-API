package com.fawry.bank.Exceptions;

public class CardGenerationException extends RuntimeException {
    public CardGenerationException(String message) {
        super(message);
    }
}
