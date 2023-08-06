package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author CaroLe
 * @Date 2023/7/30 22:33
 * @Description
 */
@Getter
@AllArgsConstructor
public enum DriverStatusEnum {
    /**
     * 正常
     */
    NORMAL(1, "正常"),
    /**
     * 禁止
     */
    PROHIBIT(2, "禁止"),
    /**
     * 降低接单量
     */
    REDUCE_ORDER_VOLUME(3, "降低接单量");

    private final Integer code;

    private final String desc;
}
