package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CaroLe
 * @Date 2023/7/30 22:39
 * @Description
 */
@Getter
@AllArgsConstructor
public enum DriverRealAuthEnum {
    /**
     * 未认证
     */
    NOT_CERTIFIED(1, "未认证"),
    /**
     * 已认证
     */
    VERIFIED(2, "已认证");

    private final Integer code;

    private final String desc;
}
