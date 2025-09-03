package com.app.dialy_cafe.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message, BadCredentialsException e) {
        super(message);
    }
}
