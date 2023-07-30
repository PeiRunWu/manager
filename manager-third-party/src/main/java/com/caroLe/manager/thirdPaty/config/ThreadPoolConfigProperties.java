package com.caroLe.manager.thirdPaty.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author CaroLe
 * @Date 2023/7/24 21:44
 * @Description
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "thread")
public class ThreadPoolConfigProperties {
    private Integer coreSize;

    private Integer maxSize;

    private Integer keepAliveTime;
}
