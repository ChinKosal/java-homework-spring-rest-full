package com.app.dialy_cafe.exception;

public class DuplicateFieldExceptionHandler extends RuntimeException {
    public DuplicateFieldExceptionHandler(String message) {
        super(message);
    }
}
