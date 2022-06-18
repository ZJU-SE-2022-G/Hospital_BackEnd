package com.segroup.hospitalsite.UserInfo.service.exception;

public class PhoneDuplicationException extends UserInfoBaseException{
    public PhoneDuplicationException() {
    }

    public PhoneDuplicationException(String message) {
        super(message);
    }

    public PhoneDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneDuplicationException(Throwable cause) {
        super(cause);
    }

    public PhoneDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
