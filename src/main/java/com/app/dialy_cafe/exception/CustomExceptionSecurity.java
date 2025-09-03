package com.app.dialy_cafe.exception;

import com.app.dialy_cafe.enums.IResponseMessage;

public class CustomExceptionSecurity extends RuntimeException {

    public CustomExceptionSecurity() {
        super("Custom Exception");
    }

    public CustomExceptionSecurity(String message) {
        super(message);
    }

    public CustomExceptionSecurity(IResponseMessage resMsgInterface) {
        super(resMsgInterface.getMessage(), new Throwable(resMsgInterface.getCode()));
    }

    public CustomExceptionSecurity(String msgCode, String message) {
        super(msgCode + " : " + message);
    }

}
