package com.app.dialy_cafe.exception;

public class UserDuplicateExceptionHandler extends RuntimeException {
    public UserDuplicateExceptionHandler(String message) {
        super(message);
    }
}
