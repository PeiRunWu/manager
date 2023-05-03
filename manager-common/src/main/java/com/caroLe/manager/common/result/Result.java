package com.caroLe.manager.common.result;

import com.caroLe.manager.common.type.StatusType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:37
 * @Description
 */
@Data
public class Result<T> {

    @ApiModelProperty(value = "返回数据")
    private T data;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "结果code")
    private Integer code;

    public Result() {}

    /**
     * 成功
     */
    public Result(T data, Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 失败
     */
    public Result(String message, Integer code) {
        this.code = code;
        this.message = message;
    }

    /**
     * 失败
     */
    public Result(String message) {
        this.message = message;
    }

    /**
     * 方便静态调用(成功)
     */
    public static <T> Result<T> success(T data, Integer code, String message) {
        return new Result<T>(data, code, message);
    }

    /**
     * 方便静态调用(成功)
     */

    public static <T> Result<T> success(T data, StatusType statusType) {
        return new Result<T>(data, statusType.getCode(), statusType.getMessage());
    }

    /**
     * 方便静态调用(失败)
     */
    public static <T> Result<T> failed(String message, Integer code) {
        return new Result<T>(message, code);
    }

    /**
     * 方便静态调用(失败)
     */
    public static <T> Result<T> failed(String message) {
        return new Result<T>(message, 201);
    }

    /**
     * 方便静态调用(失败)
     */
    public static <T> Result<T> failed(StatusType statusType) {
        return new Result<T>(statusType.getMessage(), statusType.getCode());
    }
}
