package com.segroup.hospitalsite.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Description:
 *
 * @author zheng
 * @date 2022-05-26
 */
@ApiModel(value = "通用返回对象")
public class CommonResult<T> {
    @ApiModelProperty("状态码")
    private long code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    protected  CommonResult(){}

    protected CommonResult(long code, String message, T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    /**
     * 成功返回结果
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> success(T data)
    {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     * @param data 返回数据
     * @param message 返回信息
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> success(T data, String message)
    {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode)
    {
        return new CommonResult<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode, String message)
    {
        return new CommonResult<>(errorCode.getCode(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> failed(String message)
    {
        return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 验证失败返回结果
     * @param message 返回信息
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> validateFailed(String message)
    {
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }


    /**
     * 未登录返回结果
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> unauthorized(T data)
    {
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 失败返回结果
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> failed()
    {
        return failed(ResultCode.FAILED);
    }

    /**
     * 权限不足被阻止
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> forbidden(T data)
    {
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
