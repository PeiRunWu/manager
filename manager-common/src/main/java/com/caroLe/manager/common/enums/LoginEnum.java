package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;

/**
 * @author CaroLe
 * @Date 2023/4/25 22:25
 * @Description
 */

@AllArgsConstructor
public enum LoginEnum {
    ACTIVATE(1, "激活"), FREEZE(0, "冻结");

    private Integer code;

    private String desc;

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }
}
