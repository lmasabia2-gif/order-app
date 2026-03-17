package com.example.orderapp.shared.exception;

/**
 * Exception used when a requested resource cannot be found.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
