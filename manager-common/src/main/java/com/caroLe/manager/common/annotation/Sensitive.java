package com.caroLe.manager.common.annotation;

import java.lang.annotation.*;

import cn.hutool.core.util.DesensitizedUtil;

/**
 * @author CaroLe
 * @Date 2023/8/5 22:31
 * @Description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Sensitive {

    /**
     * 脱敏数据类型
     */
    DesensitizedUtil.DesensitizedType type() default DesensitizedUtil.DesensitizedType.CHINESE_NAME;
}
