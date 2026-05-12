package com.enterprise.bank_lies.exceptions;

public class TheTransferCouldNotBeCompletedException extends RuntimeException {
    public TheTransferCouldNotBeCompletedException(String message) {
        super(message);
    }
}
