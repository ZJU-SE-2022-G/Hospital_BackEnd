package com.segroup.hospitalsite.UserInfo.service.exception;

// 数据插入时产生的异常
public class InsertionException extends UserInfoBaseException{
    public InsertionException() {
    }

    public InsertionException(String message) {
        super(message);
    }

    public InsertionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertionException(Throwable cause) {
        super(cause);
    }

    public InsertionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
