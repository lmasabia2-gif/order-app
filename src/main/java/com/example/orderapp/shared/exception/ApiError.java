package com.example.orderapp.shared.exception;

import java.time.Instant;

/**
 * Standard error payload returned by the API.
 */
public record ApiError(
        Instant timestamp,
        int status,
        String error,
        String code,
        String message,
        String path
) {
}
