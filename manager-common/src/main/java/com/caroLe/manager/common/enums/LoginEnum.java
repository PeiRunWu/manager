package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;

/**
 * @author CaroLe
 * @Date 2023/4/25 22:25
 * @Description
 */

@AllArgsConstructor
public enum LoginEnum {

    /**
     * 激活
     */
    ACTIVATE(1, "激活"),
    /**
     * 冻结
     */
    FREEZE(0, "冻结");

    private final Integer code;

    private final String desc;

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }
}
