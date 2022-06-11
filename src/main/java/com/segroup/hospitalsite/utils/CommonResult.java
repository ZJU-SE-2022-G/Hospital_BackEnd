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
    private long state;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private T data;

    protected  CommonResult(){}

    protected CommonResult(long state, String message, T data){
        this.state=state;
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
        return new CommonResult<>(ResultCode.SUCCESS.getState(), ResultCode.SUCCESS.getMessage(), data);
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
        return new CommonResult<>(ResultCode.SUCCESS.getState(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode)
    {
        return new CommonResult<>(errorCode.getState(), errorCode.getMessage(), null);
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
        return new CommonResult<>(errorCode.getState(), message, null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> failed(String message)
    {
        return new CommonResult<>(ResultCode.FAILED.getState(), message, null);
    }

    /**
     * 验证失败返回结果
     * @param message 返回信息
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> validateFailed(String message)
    {
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getState(), message, null);
    }


    /**
     * 未登录返回结果
     * @param data 返回数据
     * @param <T> 数据类型
     * @return 通用返回对象
     */
    public static <T> CommonResult<T> unauthorized(T data)
    {
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getState(), ResultCode.UNAUTHORIZED.getMessage(), data);
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
        return new CommonResult<>(ResultCode.FORBIDDEN.getState(), ResultCode.FORBIDDEN.getMessage(), data);
    }
    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
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
