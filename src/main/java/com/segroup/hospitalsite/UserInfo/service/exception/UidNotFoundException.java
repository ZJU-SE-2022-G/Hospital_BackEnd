package com.segroup.hospitalsite.UserInfo.service.exception;

public class UidNotFoundException extends UserInfoBaseException{
    public UidNotFoundException() {
    }

    public UidNotFoundException(String message) {
        super(message);
    }

    public UidNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UidNotFoundException(Throwable cause) {
        super(cause);
    }

    public UidNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
