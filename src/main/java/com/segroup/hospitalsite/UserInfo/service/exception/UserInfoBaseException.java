package com.segroup.hospitalsite.UserInfo.service.exception;


public class UserInfoBaseException extends RuntimeException{
    public UserInfoBaseException() {
        super();
    }

    public UserInfoBaseException(String message) {
        super(message);
    }

    public UserInfoBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInfoBaseException(Throwable cause) {
        super(cause);
    }

    protected UserInfoBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
