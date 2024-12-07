package com.abcbank.abcmorgage.exception;

public class WrongCredentialsException extends RuntimeException {

    public WrongCredentialsException(String message) {
        super(message);
    }
}