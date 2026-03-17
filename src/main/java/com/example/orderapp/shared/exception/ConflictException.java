package com.example.orderapp.shared.exception;

/**
 * Exception used for business conflicts.
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
