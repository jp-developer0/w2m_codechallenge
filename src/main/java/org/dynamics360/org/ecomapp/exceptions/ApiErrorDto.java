package org.dynamics360.org.ecomapp.exceptions;

public record ApiErrorDto(
        String message,
        String backendMessage,
        String method,
        String url
) {
}
