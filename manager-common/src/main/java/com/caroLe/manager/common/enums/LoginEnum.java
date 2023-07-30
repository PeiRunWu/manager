package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CaroLe
 * @Date 2023/4/25 22:25
 * @Description
 */
@Getter
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
}
