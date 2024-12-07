package com.abcbank.abcmorgage.dto.exception;

public class ValidationException extends RuntimeException {
        public ValidationException(String message) {
            super(message);
        }

}