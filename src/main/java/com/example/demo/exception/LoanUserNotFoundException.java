package com.example.demo.exception;

public class LoanUserNotFoundException extends RuntimeException {
    public LoanUserNotFoundException(String message) {
        super(message);
    }
}
