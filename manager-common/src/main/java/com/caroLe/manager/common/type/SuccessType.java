package com.caroLe.manager.common.type;

/**
 * @author CaroLe
 * @Date 2023/4/20 14:38
 * @Description
 */
public class SuccessType extends StatusType {

    public static final StatusType USER_LOGOUT = new StatusType(200, "用户已登出");

    public static final StatusType USER_REGISTER = new StatusType(200, "用户注册成功");

    public SuccessType(Integer code, String message) {
        super(code, message);
    }
}
