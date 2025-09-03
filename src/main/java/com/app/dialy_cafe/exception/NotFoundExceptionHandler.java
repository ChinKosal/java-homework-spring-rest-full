package com.app.dialy_cafe.exception;

public class NotFoundExceptionHandler extends RuntimeException {
    public NotFoundExceptionHandler(String message) {
        super(message);
    }
}
