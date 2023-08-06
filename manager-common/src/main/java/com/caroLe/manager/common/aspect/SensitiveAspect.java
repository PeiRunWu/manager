package com.caroLe.manager.common.aspect;

import java.lang.reflect.Field;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.caroLe.manager.common.annotation.Sensitive;

import cn.hutool.core.util.DesensitizedUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author CaroLe
 * @Date 2023/8/5 22:38
 * @Description
 */
@Slf4j
@Aspect
@Component
public class SensitiveAspect {

    @Around("@annotation(com.caroLe.manager.common.annotation.Sensitive)")
    public Object invoke(ProceedingJoinPoint point) throws Throwable {
        log.info("执行SensitiveAspect");
        Object target = point.getTarget();
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields) {
            Sensitive annotation = field.getAnnotation(Sensitive.class);
            if (annotation != null) {
                Object value = field.get(target);
                DesensitizedUtil.DesensitizedType desensitizedType = annotation.type();
                String desensitized = DesensitizedUtil.desensitized(value.toString(), desensitizedType);
                field.set(target, desensitized);
            }
        }
        return target;
    }
}
