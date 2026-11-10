package com.example.demo.exception;

public class BookAlreadyOnLoanException extends RuntimeException{
    public BookAlreadyOnLoanException(String exception) {
        super(exception);
    }
}
