package com.caroLe.manager.common.exception;

import com.caroLe.manager.common.type.StatusType;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:41
 * @Description
 */
public class DataException extends BaseException {
    public DataException(Integer code, String message) {
        super(code, message);
    }

    public DataException(StatusType statusType) {
        super(statusType);
    }
}
