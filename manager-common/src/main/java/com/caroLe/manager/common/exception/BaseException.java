package com.caroLe.manager.common.exception;

import com.caroLe.manager.common.type.StatusType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:41
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;

    private String message;

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(StatusType statusType) {
        super(statusType.getMessage());
        this.message = statusType.getMessage();
        this.code = statusType.getCode();
    }
}

