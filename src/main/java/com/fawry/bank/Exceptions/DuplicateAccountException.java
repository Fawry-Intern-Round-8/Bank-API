package com.fawry.bank.Exceptions;

import jakarta.persistence.EntityExistsException;

public class DuplicateAccountException extends EntityExistsException {
    public DuplicateAccountException(String message) {
        super(message);
    }

    public DuplicateAccountException(String message, Throwable cause) {
        super(message);
        this.initCause(cause);
    }
}
