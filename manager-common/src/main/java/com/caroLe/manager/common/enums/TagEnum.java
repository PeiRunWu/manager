package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;

/**
 * @author CaroLe
 * @Date 2023/5/28 21:35
 * @Description
 */
@AllArgsConstructor
public enum TagEnum {
    /**
     * 父标签
     */
    PARENT_TAG(0, "父标签"),
    /**
     * 子标签
     */
    CHILDREN_TAG(1, "子标签");

    private final Integer code;

    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
