package com.caroLe.manager.common.exception;

import com.caroLe.manager.common.type.StatusType;

/**
 * @author CaroLe
 * @Date 2023/4/21 15:12
 * @Description
 */
public class UserNameNotFound extends BaseException{

    public UserNameNotFound(Integer code, String message) {
        super(code, message);
    }

    public UserNameNotFound(StatusType statusType) {
        super(statusType);
    }
}
