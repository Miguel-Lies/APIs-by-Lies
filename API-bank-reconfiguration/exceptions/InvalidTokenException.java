package com.enterprise.bank_lies.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
    public InvalidTokenException(String message,Throwable throwable){
        super(message,throwable);
    }
}
