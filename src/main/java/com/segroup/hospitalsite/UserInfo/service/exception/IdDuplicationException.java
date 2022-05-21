package com.segroup.hospitalsite.UserInfo.service.exception;

// 用户名被占用异常
public class IdDuplicationException extends UserInfoBaseException{
    public IdDuplicationException() {
        super();
    }

    public IdDuplicationException(String message) {
        super(message);
    }

    public IdDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdDuplicationException(Throwable cause) {
        super(cause);
    }

    public IdDuplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
