package com.caroLe.manager.common.enums;

import lombok.AllArgsConstructor;

/**
 * @author CaroLe
 * @Date 2023/5/3 13:09
 * @Description
 */

@AllArgsConstructor
public enum MenuEnum {
    DIRECTORY(0,"目录"), MENU(1,"菜单"), BUTTON(2,"按钮");

    private final int code;
    
    private final String desc;

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
