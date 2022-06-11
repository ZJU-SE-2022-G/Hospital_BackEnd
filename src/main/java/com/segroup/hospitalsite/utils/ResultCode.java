package com.segroup.hospitalsite.utils;

/**
 * Description:
 *
 * @author zheng
 * @date 2022-05-26
 */
public enum ResultCode implements IErrorCode{
    // 枚举类
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");

    private final long state;
    private final String message;

    ResultCode(long state, String message)
    {
        this.state=state;
        this.message=message;
    }

    @Override
    public long getState() {
        return this.state;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
