package com.abcbank.abcmorgage.exception;


public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}