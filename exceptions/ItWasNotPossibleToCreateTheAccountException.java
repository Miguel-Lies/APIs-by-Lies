package com.enterprise.bank_lies.exceptions;

public class ItWasNotPossibleToCreateTheAccountException extends RuntimeException {
    public ItWasNotPossibleToCreateTheAccountException(String message) {
        super(message);
    }
}
